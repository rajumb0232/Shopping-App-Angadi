package com.angadi.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.angadi.entity.Merchant;
import com.angadi.repository.MerchantRepo;

@Repository
public class MerchantDao {

	@Autowired
	private MerchantRepo merchantRepo;

	public Merchant saveMerchant(Merchant merchant) {
		return merchantRepo.save(merchant);
	}

	public Merchant getMerchant(long merchantId) {
		Optional<Merchant> optional = merchantRepo.findById(merchantId);

		if (optional.isEmpty()) {
			return null;
		} else {
			return optional.get();
		}
	}

	public Merchant updateMerchant(Merchant merchant, long id) {
		Optional<Merchant> optional = merchantRepo.findById(id);

		if (optional.isEmpty()) {
			return null;
		} else {
			merchant.setMerchantId(id);
			return merchantRepo.save(merchant);
		}
	}

	public Merchant deleteMerchant(long id) {
		Optional<Merchant> optional = merchantRepo.findById(id);

		if (optional.isEmpty()) {
			return null;
		} else {
			Merchant merchant = optional.get();
			 merchantRepo.delete(merchant);
			 return merchant;
		}
	}

}
