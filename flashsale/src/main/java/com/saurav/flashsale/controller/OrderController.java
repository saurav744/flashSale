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

import com.saurav.flashsale.entities.enums.OrderStatus;
import com.saurav.flashsale.entity.request_response.OrderRequest;
import com.saurav.flashsale.entity.request_response.OrderResponse;
import com.saurav.flashsale.service.OrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "FlashSale order APIs")
@RestController
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@ApiOperation(value = "Place order")
	@PostMapping("/order")
	public ResponseEntity<OrderResponse> placeOrder(
			@ApiParam(value = "order request", required = true) @RequestBody OrderRequest orderRequest) {
		
		OrderResponse response = orderService.placeOrder(orderRequest);
		return new ResponseEntity<OrderResponse>(response, HttpStatus.CREATED);	
	}
	
	@ApiOperation(value = "Updates the order status")
	@PutMapping("/order/{id}")
	public ResponseEntity<OrderResponse> updateOrder(
			@ApiParam(value = "Unique Id of the order", required = true) @PathVariable Long id,
			@ApiParam(value = "State of the order", required = true) @RequestParam OrderStatus status) {
		OrderResponse response = orderService.updateStatus(id, status);
		return new ResponseEntity<OrderResponse>(response, HttpStatus.OK);
	}

}
