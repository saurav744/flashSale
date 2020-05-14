package com.saurav.flashsale.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.saurav.flashsale.entity.FlashSale;
import com.saurav.flashsale.entity.SaleRegistration;
import com.saurav.flashsale.entity.User;
import com.saurav.flashsale.entity.request_response.SaleRegistrationRequest;
import com.saurav.flashsale.exception.RegistrationClosedException;
import com.saurav.flashsale.exception.ResourceNotFoundException;
import com.saurav.flashsale.repository.FlashSaleRepository;
import com.saurav.flashsale.repository.SaleRegistrationRepository;
import com.saurav.flashsale.repository.UserRepository;

@Service("saleRegistrationService")
@Transactional
public class SaleRegistrationServiceImpl implements SaleRegistrationService{
	
	@Autowired
	private SaleRegistrationRepository saleRegistrationRepository;
	@Autowired
	private FlashSaleRepository flashSaleRepository;
	@Autowired
	private UserRepository userRepository;

	@Override
	public SaleRegistration register(SaleRegistrationRequest regRequest) {
		SaleRegistration saleRegistration = new SaleRegistration();
		
		Optional<FlashSale> opFlashSale = flashSaleRepository.findById(regRequest.getFlashSaleId());
		if(!opFlashSale.isPresent()) {
			throw new ResourceNotFoundException(Long.toString(regRequest.getFlashSaleId()), "FlashSale not found");
		}
		if(opFlashSale.get().getIsRegistrationOpen() == false) {
			throw new RegistrationClosedException();
		}
		
		Optional<User> opUser = userRepository.findById(regRequest.getUserId());
		if(!opUser.isPresent()) {
			throw new ResourceNotFoundException(Long.toString(regRequest.getUserId()), "User not found");
		}
		
		saleRegistration.setFlashSale(opFlashSale.get());
		saleRegistration.setUser(opUser.get());
		saleRegistration = saleRegistrationRepository.save(saleRegistration);
		return saleRegistration;
	}

}
