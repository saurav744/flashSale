package com.saurav.flashsale.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.saurav.flashsale.entities.enums.FlashSaleStatus;
import com.saurav.flashsale.entity.FlashSale;
import com.saurav.flashsale.entity.Product;
import com.saurav.flashsale.entity.request_response.FlashSaleDto;
import com.saurav.flashsale.entity.request_response.FlashSaleResponse;
import com.saurav.flashsale.exception.ResourceNotFoundException;
import com.saurav.flashsale.repository.FlashSaleRepository;
import com.saurav.flashsale.repository.ProductRepository;

@Service("flashSaleService")
@Transactional
public class FlashSaleServiceImpl implements FlashSaleService{
	
	@Autowired
	private FlashSaleRepository flashSaleRepository;
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public FlashSaleResponse create(Long productId) {
		Optional<Product> opProduct = productRepository.findById(productId);
		if(!opProduct.isPresent()) {
			throw new ResourceNotFoundException(Long.toString(productId), "Product not found");
		}
		
		FlashSale flashSale = new FlashSale();
		flashSale.setProduct(opProduct.get());
		flashSale.setIsRegistrationOpen(Boolean.TRUE);
		flashSale.setStatus(FlashSaleStatus.UPCOMING);
		flashSale = flashSaleRepository.save(flashSale);
		
		return new FlashSaleResponse(flashSale.getId(), flashSale.getProduct().getName(),
				flashSale.getStatus(), flashSale.getIsRegistrationOpen());

	}

	@Override
	public FlashSaleDto update(FlashSaleDto flashSaleDto) {
		FlashSale flashSale = flashSaleRepository.getOne(flashSaleDto.getId());
		flashSale.setIsRegistrationOpen(flashSaleDto.getIsRegistrationOpen());
		flashSale.setStatus(flashSaleDto.getStatus());
		flashSaleRepository.save(flashSale);
		return flashSaleDto;
	}

	@Override
	public FlashSale get(Long id) {
		Optional<FlashSale> opFlashSale = flashSaleRepository.findById(id);
		if(!opFlashSale.isPresent()) {
			throw new ResourceNotFoundException(Long.toString(id), "FlashSale not found");
		}
		return opFlashSale.get();
	}

}
