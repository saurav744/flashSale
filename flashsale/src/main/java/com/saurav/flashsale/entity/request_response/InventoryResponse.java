package com.saurav.flashsale.entity.request_response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class InventoryResponse {
	private Long id;
	private String productName;
	private Integer count;

}
