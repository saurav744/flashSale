package com.saurav.flashsale.entity.request_response;

import java.time.LocalDateTime;
import java.util.Date;

import com.saurav.flashsale.entities.enums.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class OrderResponse {
	
	private Long orderId;
	private String product;
	private Date createdAt;
	private OrderStatus status;

}
