package com.angadi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.angadi.Configuration.ResponseStructure;
import com.angadi.entity.CustomerOrder;
import com.angadi.enums.OrderStatus;
import com.angadi.service.CustomerOrderService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/order")
public class CustomerOrderController {

	@Autowired
	private CustomerOrderService customerOrderService;

	@ApiOperation(value = "Add CustomerOrder", notes = "API used to Save CustomerOrder to the database,"
			+ " the customer order will be havind the relationship with the Customer and the Shop.")
	@ApiResponses({ @ApiResponse(code = 201, message = "CustomerOrder Saved successfully!"),
			@ApiResponse(code = 404, message = "Failed to save CustomerOrder!")

	})
	@PostMapping
	public ResponseEntity<ResponseStructure<CustomerOrder>> addCustomerOrder(@RequestParam long customerId) {
		return customerOrderService.addCustomerOrder(customerId);
	}

	@ApiOperation(value = "Get CustomerOrder", notes = "API used to fetch CustomerOrders based on the CustomerId and OrderStatus.")
	@ApiResponses({ @ApiResponse(code = 302, message = "CustomerOrders Found."),
			@ApiResponse(code = 404, message = "Failed to Fetch CustomerOrders!")

	})
	@GetMapping("/all/byStatus/byCustomer")
	public ResponseEntity<ResponseStructure<List<CustomerOrder>>> getCustomerOrderByOrderStatusByCustomer(
			@RequestParam OrderStatus orderStatus, @RequestParam long customerId) {
		return customerOrderService.getCustomerOrderByOrderStatusByCustomer(orderStatus, customerId);
	}

	@ApiOperation(value = "Get Orders", notes = "API used to fetch CustomerOrders based on the OrderStatus.")
	@ApiResponses({ @ApiResponse(code = 302, message = "Customer Found."),
			@ApiResponse(code = 404, message = "Failed to Fetch Customer!")

	})
	@GetMapping("/all/byStatus")
	public ResponseEntity<ResponseStructure<List<CustomerOrder>>> getCustomerOrderByOrderStatus(
			@RequestParam OrderStatus orderStatus) {
		return customerOrderService.getCustomerOrderByOrderStatus(orderStatus);
	}

	/**
	 * should create an API to get CustomerOrders based on the shopId and
	 * OrderStatus.
	 */

	@ApiOperation(value = "Update Orders", notes = "API used to update OrderStatus based on the orderId Of CustomerOrder.")
	@ApiResponses({ @ApiResponse(code = 200, message = "CustomerOrder Updated successfully."),
			@ApiResponse(code = 404, message = "Failed to Update CustomerOrder!")

	})
	@PutMapping
	public ResponseEntity<ResponseStructure<CustomerOrder>> updateCustomerOrder(@RequestParam OrderStatus orderStatus,
			int orderId) {
		return customerOrderService.updateCustomerOrder(orderStatus, orderId);
	}

	@ApiOperation(value = "Delete Orders", notes = "API used to Delete CustomerOrder based on the orderId (Note: the Orders can only be deleted if the OrderStatus is not Delivered).")
	@ApiResponses({ @ApiResponse(code = 200, message = "CustomerOrder Deleted successfully."),
			@ApiResponse(code = 404, message = "Failed to Delete CustomerOrder!")

	})
	@DeleteMapping
	public ResponseEntity<ResponseStructure<CustomerOrder>> deleteCustomeOrder(@RequestParam int orderId) {
		return customerOrderService.deleteCustomerOrder(orderId);
	}
}
