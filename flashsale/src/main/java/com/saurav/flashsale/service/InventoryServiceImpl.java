package com.saurav.flashsale.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.saurav.flashsale.entity.Inventory;
import com.saurav.flashsale.entity.Product;
import com.saurav.flashsale.entity.request_response.InventoryDto;
import com.saurav.flashsale.entity.request_response.InventoryRequest;
import com.saurav.flashsale.entity.request_response.InventoryResponse;
import com.saurav.flashsale.exception.ResourceNotFoundException;
import com.saurav.flashsale.repository.InventoryRepository;
import com.saurav.flashsale.repository.ProductRepository;

@Service("inventoryService")
@Transactional
public class InventoryServiceImpl implements InventoryService{
	
	@Autowired
	private InventoryRepository inventoryRepository;
	@Autowired
	private ProductRepository productRepository;

	@Override
	public InventoryResponse add(InventoryRequest inventoryRequest) {
		Optional<Product> opProduct = productRepository.findById(inventoryRequest.getProductId());
		if(!opProduct.isPresent()) {
			throw new ResourceNotFoundException(Long.toString(inventoryRequest.getProductId()), "Product not found");
		}
		Inventory inventory = new Inventory();
		inventory.setProduct(opProduct.get());
		inventory.setCount(inventoryRequest.getCount());
		inventory = inventoryRepository.save(inventory);
		
		return new InventoryResponse(inventory.getId(), inventory.getProduct().getName(), inventory.getCount());
	}

	@Override
	public InventoryResponse updateCount(Long id, InventoryDto inventoryDto) {
		Inventory inventory = inventoryRepository.getOne(id);
		inventory.setCount(inventoryDto.getCount());
		inventory = inventoryRepository.save(inventory);
		return new InventoryResponse(inventory.getId(), inventory.getProduct().getName(), inventory.getCount());
	}

}
