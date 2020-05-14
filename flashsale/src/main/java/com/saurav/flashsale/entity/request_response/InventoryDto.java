package com.saurav.flashsale.entity.request_response;

import com.saurav.flashsale.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
@AllArgsConstructor
public class InventoryDto {
	Long id;
	Integer count;

}
