package com.saurav.flashsale.entity.request_response;

import com.saurav.flashsale.entities.enums.FlashSaleStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class FlashSaleDto {
	private Long id;
	private FlashSaleStatus status;
	private Boolean isRegistrationOpen;
}
