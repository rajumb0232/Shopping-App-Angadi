package com.angadi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.angadi.Configuration.ResponseStructure;
import com.angadi.Exception.CustomerNotFoundByIdException;
import com.angadi.Exception.ProductNotFoundByIdException;
import com.angadi.Exception.SelectedProductNotFoundByIdException;
import com.angadi.dao.CartDao;
import com.angadi.dao.CustomerDao;
import com.angadi.dao.ProductDao;
import com.angadi.dao.SelectedProductDao;
import com.angadi.entity.Cart;
import com.angadi.entity.Customer;
import com.angadi.entity.Product;
import com.angadi.entity.SelectedProduct;

@Service
public class SelectedProductService {
	
	@Autowired
	private SelectedProductDao selectedProductDao;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private CartDao cartDao;
	@Autowired
	private ProductDao productDao;

	public ResponseEntity<ResponseStructure<SelectedProduct>> addSelectedProduct(long productId, long customerId, int productQuantity) {
		SelectedProduct selectedProduct = new SelectedProduct();
		Product product = productDao.findProduct(productId);
		if(product!=null) {

			Customer customer = customerDao.getCustomer(customerId);
			if(customer!=null) {

				ResponseStructure<SelectedProduct> structure = new ResponseStructure<>();
				structure.setStatus(HttpStatus.CREATED.value());
				
				// check if there is a similar product already present in the cart(selectedProductList)
				SelectedProduct existing = selectedProductDao.getselectedProductByProduct(product);
				if(existing!=null) {
					existing.setProductQuantity(productQuantity + existing.getProductQuantity());
					selectedProductDao.updateSelectedProduct(existing);
					structure.setMessage("SelectedProduct quantity updated to cart successfully.");
					structure.setData(existing);
					return new ResponseEntity<ResponseStructure<SelectedProduct>>(structure,HttpStatus.CREATED);
				}
				
				/* if there is no existing product in cart then add requested
				 * product to selectedProduct list.*/
				else {
					selectedProduct.setProduct(product);
					selectedProduct.setTotalPrice(productQuantity*product.getProductPrice());
					selectedProduct.setProductQuantity(productQuantity);
					SelectedProduct selectedProduct2 = selectedProductDao.addSelectedProduct(selectedProduct);
					Cart cart = customer.getCart();
					List<SelectedProduct> selectedProducts = cart.getSelectedProducts();
					selectedProducts.add(selectedProduct2);
					cart.setSelectedProducts(selectedProducts);
					cartDao.updateCart(cart);
					structure.setMessage("SelectedProduct added to cart successfully.");
					structure.setData(selectedProduct2);
					return new ResponseEntity<ResponseStructure<SelectedProduct>>(structure,HttpStatus.CREATED);
				}
			}else {
				throw new CustomerNotFoundByIdException("Failed to Add selectedProduct to cart");
			}	
		}else {
			throw new ProductNotFoundByIdException("Failed to Add selectedProduct to cart");
		}
		

	}

	public ResponseEntity<ResponseStructure<SelectedProduct>> getSelectedProduct(int selectedProductId) {
		SelectedProduct selectedProduct = selectedProductDao.getselectedProduct(selectedProductId);
		if(selectedProduct!=null) {
			ResponseStructure<SelectedProduct> structure = new ResponseStructure<>();
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("SelectedProduct found.");
			structure.setData(selectedProduct);
			return new ResponseEntity<ResponseStructure<SelectedProduct>>(structure,HttpStatus.FOUND);
		}else {
			throw new SelectedProductNotFoundByIdException("Failed to find SelectedProduct!");
		}
	}

	public ResponseEntity<ResponseStructure<SelectedProduct>> updateSelectedProduct(int selectedProductQuantity, long selectedProductId) {
		SelectedProduct selectedProduct = selectedProductDao.getselectedProduct(selectedProductId);
		if(selectedProduct!=null) {
			selectedProduct.setProductQuantity(selectedProductQuantity);
			selectedProduct.setTotalPrice(selectedProductQuantity*selectedProduct.getProduct().getProductPrice());
			selectedProductDao.updateSelectedProduct(selectedProduct);
			ResponseStructure<SelectedProduct> structure = new ResponseStructure<>();
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("SelectedProduct updated successfully.");
			structure.setData(selectedProduct);
			return new ResponseEntity<ResponseStructure<SelectedProduct>>(structure,HttpStatus.OK);
		}else {
			throw new SelectedProductNotFoundByIdException("Failed to update SelecteProduct!");
		}
	}

	public ResponseEntity<ResponseStructure<SelectedProduct>> deleteSelectedProduct(long selectedProductId, long customerId) {
		SelectedProduct selectedProduct = selectedProductDao.getselectedProduct(selectedProductId);
		if(selectedProduct!=null) {
			Customer customer = customerDao.getCustomer(customerId);
			if(customer!=null) {
				Cart cart = customer.getCart();
				List<SelectedProduct> selectedProducts = cart.getSelectedProducts();
				selectedProducts.remove(selectedProduct);
				cartDao.addCart(cart);
				selectedProductDao.deleteSelectedProduct(selectedProductId);
				ResponseStructure<SelectedProduct> structure = new ResponseStructure<>();
				structure.setStatus(HttpStatus.OK.value());
				structure.setMessage("Selected Product deleted successfully.");
				structure.setData(selectedProduct);
				return new ResponseEntity<ResponseStructure<SelectedProduct>>(structure,HttpStatus.OK);
			}else {
				throw new CustomerNotFoundByIdException("Failed to delete SelectedProduct!");
			}

		}else {
			throw new SelectedProductNotFoundByIdException("Failed to delete SelecteProduct!");
		}
	}
	
	
	
	
}
