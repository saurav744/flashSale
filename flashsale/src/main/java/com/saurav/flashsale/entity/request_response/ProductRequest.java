package com.saurav.flashsale.entity.request_response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ProductRequest {
	private String name;
	private Double price;
	private String description;

}
