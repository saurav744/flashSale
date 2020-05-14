package com.saurav.flashsale.service;

import com.saurav.flashsale.entity.request_response.InventoryDto;
import com.saurav.flashsale.entity.request_response.InventoryRequest;
import com.saurav.flashsale.entity.request_response.InventoryResponse;

public interface InventoryService {
	
	public InventoryResponse add(InventoryRequest inventoryRequest);
	
	public InventoryResponse updateCount(Long id, InventoryDto inventoryDto);
	

}
