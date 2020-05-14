package com.saurav.flashsale.service;

import com.saurav.flashsale.entity.FlashSale;
import com.saurav.flashsale.entity.request_response.FlashSaleDto;
import com.saurav.flashsale.entity.request_response.FlashSaleResponse;

public interface FlashSaleService {
	
	public FlashSaleResponse create(Long productId);
	
	public FlashSaleDto update(FlashSaleDto flashSaleDto);
	
	public FlashSale get(Long id);
	

}
