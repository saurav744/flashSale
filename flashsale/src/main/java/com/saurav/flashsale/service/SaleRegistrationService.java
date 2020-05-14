package com.saurav.flashsale.service;

import com.saurav.flashsale.entity.SaleRegistration;
import com.saurav.flashsale.entity.request_response.SaleRegistrationRequest;

public interface SaleRegistrationService {
	
	public SaleRegistration register(SaleRegistrationRequest regRequest);

}
