package com.saurav.flashsale.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.saurav.flashsale.entity.request_response.SaleRegistrationRequest;
import com.saurav.flashsale.service.SaleRegistrationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "FlashSale registration APIs")
@RestController
public class SaleRegistrationController {
	
	@Autowired
	private SaleRegistrationService saleRegistrationService;
	
	@ApiOperation(value = "Register to the flash sale")
	@PostMapping("/register")
	public ResponseEntity<String> register(
			@ApiParam(value = "register request body", required = true) @RequestBody SaleRegistrationRequest request) {
		
		saleRegistrationService.register(request);
		String msg = "Succesfully registered for flash sale";
		return new ResponseEntity<String>(msg, HttpStatus.CREATED);	
	}

}
