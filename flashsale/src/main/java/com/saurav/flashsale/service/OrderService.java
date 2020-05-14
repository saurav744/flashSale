package com.saurav.flashsale.service;

import com.saurav.flashsale.entities.enums.OrderStatus;
import com.saurav.flashsale.entity.request_response.OrderRequest;
import com.saurav.flashsale.entity.request_response.OrderResponse;

public interface OrderService {
	
	public OrderResponse placeOrder(OrderRequest orderRequest);
	
	public OrderResponse updateStatus(Long orderId, OrderStatus status);

}
