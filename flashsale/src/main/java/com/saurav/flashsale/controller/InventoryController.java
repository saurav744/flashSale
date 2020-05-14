package com.saurav.flashsale.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.saurav.flashsale.entity.request_response.InventoryDto;
import com.saurav.flashsale.entity.request_response.InventoryRequest;
import com.saurav.flashsale.entity.request_response.InventoryResponse;
import com.saurav.flashsale.service.InventoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "FlashSale inventory APIs")
@RestController
public class InventoryController {
	
	@Autowired
	private InventoryService inventoryService;
	
	@ApiOperation(value = "Create inventory")
	@PostMapping("/inventory")
	public ResponseEntity<InventoryResponse> createInventory(
			@ApiParam(value = "inventory request body", required = true) @RequestBody InventoryRequest inventoryRequest) {
		
		InventoryResponse response = inventoryService.add(inventoryRequest);
		return new ResponseEntity<InventoryResponse>(response, HttpStatus.CREATED);	
	}
	
	@ApiOperation(value = "Updates the inventory count")
	@PutMapping("/inventory/{id}")
	public ResponseEntity<InventoryResponse> updateCount(
			@ApiParam(value = "Unique Id of the inventory", required = true) @PathVariable Long id,
			@ApiParam(value = "inventory count ", required = true) @RequestParam InventoryDto inventoryDto) {
		InventoryResponse response = inventoryService.updateCount(id, inventoryDto);
		return new ResponseEntity<InventoryResponse>(response, HttpStatus.OK);
	}

}
