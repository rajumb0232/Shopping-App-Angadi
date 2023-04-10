package com.angadi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.angadi.Configuration.ResponseStructure;
import com.angadi.Exception.MerchantNotFoundByIdException;
import com.angadi.dao.MerchantDao;
import com.angadi.dto.MerchantDto;
import com.angadi.entity.Merchant;

@Service
public class MerchantService {
	
	@Autowired
	private MerchantDao dao;
	
	@Autowired
	private MerchantDto dto;

	public ResponseEntity<ResponseStructure<MerchantDto>> addMerchant(Merchant merchant) {
		ResponseStructure<MerchantDto> structure = new ResponseStructure<>();
		Merchant merchant2 = dao.saveMerchant(merchant);
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setMessage("Merchant successfully added.");
		dto.setMerchantId(merchant2.getMerchantId());
		dto.setMerchantName(merchant2.getMerchantName());
		structure.setData(dto);
		return new ResponseEntity<ResponseStructure<MerchantDto>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<MerchantDto>> getmerchant(long id) {
		ResponseStructure<MerchantDto> structure = new ResponseStructure<>();
		Merchant merchant = dao.getMerchant(id);
		
		if(merchant!=null) {
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Merchant found.");
			dto.setMerchantId(merchant.getMerchantId());
			dto.setMerchantName(merchant.getMerchantName());
			structure.setData(dto);
			return new ResponseEntity<ResponseStructure<MerchantDto>>(structure, HttpStatus.FOUND);
		}else 
			throw new MerchantNotFoundByIdException("Failed to find Merchant!");
	}

	public ResponseEntity<ResponseStructure<MerchantDto>> updateMerchant(Merchant merchant, long id) {
		ResponseStructure<MerchantDto> structure = new ResponseStructure<>();
		Merchant merchant2 = dao.updateMerchant(merchant, id);
		
		if(merchant2 != null) {
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("Merchant updated successfully!");
			dto.setMerchantId(merchant2.getMerchantId());
			dto.setMerchantName(merchant2.getMerchantName());
			structure.setData(dto);
			return new ResponseEntity<ResponseStructure<MerchantDto>>(structure, HttpStatus.OK);
		}else {
			throw new MerchantNotFoundByIdException("Failed to update Merchant!");
		}
	}

	public ResponseEntity<ResponseStructure<MerchantDto>> deleteMerchant(long id) {
		ResponseStructure<MerchantDto> structure = new ResponseStructure<>();
		Merchant merchant = dao.deleteMerchant(id);
		
		if(merchant != null) {
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("Merchant deleted successfully!");
			dto.setMerchantId(merchant.getMerchantId());
			dto.setMerchantName(merchant.getMerchantName());
			structure.setData(dto);
			return new ResponseEntity<ResponseStructure<MerchantDto>>(structure, HttpStatus.OK);
		}else {
			throw new MerchantNotFoundByIdException("Failed to delete Merchant!");
		}
	}
}
