package com.saurav.flashsale.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.saurav.flashsale.entity.FlashSale;
import com.saurav.flashsale.entity.request_response.FlashSaleDto;
import com.saurav.flashsale.entity.request_response.FlashSaleRequest;
import com.saurav.flashsale.entity.request_response.FlashSaleResponse;
import com.saurav.flashsale.service.FlashSaleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "FlashSale management APIs")
@RestController
public class FlashSaleController {
	
	@Autowired
	private FlashSaleService flashSaleService;
	
	@ApiOperation(value = "Create flashSale")
	@PostMapping("/sale")
	public ResponseEntity<FlashSaleResponse> create(
			@ApiParam(value = "flash sale request", required = true) @RequestBody FlashSaleRequest request) {
		
		FlashSaleResponse response = flashSaleService.create(request.getProductId());
		return new ResponseEntity<FlashSaleResponse>(response, HttpStatus.CREATED);	
	}
	
	@ApiOperation(value = "Updates the flash sale status")
	@PutMapping("/sale/{id}")
	public ResponseEntity<FlashSaleDto> updateSale(
			@ApiParam(value = "Unique Id of the sale", required = true) @PathVariable Long id,
			@ApiParam(value = "State of the sale", required = true) @RequestBody FlashSaleDto flashSaleDto) {
		FlashSaleDto response = flashSaleService.update(flashSaleDto);
		return new ResponseEntity<FlashSaleDto>(response, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Returns the flash sale by id")
	@GetMapping("/sale/{id}")
	public FlashSale getSaleById(@ApiParam(value = "Unique sale id", required = true) @PathVariable Long id) {
		
		return flashSaleService.get(id);
	}

}
