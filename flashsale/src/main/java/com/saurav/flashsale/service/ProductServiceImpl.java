package com.saurav.flashsale.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.saurav.flashsale.entity.Product;
import com.saurav.flashsale.entity.request_response.ProductRequest;
import com.saurav.flashsale.exception.ResourceNotFoundException;
import com.saurav.flashsale.repository.ProductRepository;

@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public Product add(ProductRequest prodRequest) {
		Product product = new Product();
		product.setName(prodRequest.getName());
		product.setPrice(prodRequest.getPrice());
		product.setDescription(prodRequest.getDescription());
		product = productRepository.save(product);
		return product;
	}

	@Override
	public Product update(Long id, ProductRequest productRequest) {
		Product product = new Product(id, productRequest.getName(), productRequest.getPrice(),
				productRequest.getDescription());
		product = productRepository.save(product);
		return product;
	}

	@Override
	public Product get(Long id) {
		Optional<Product> opProduct = productRepository.findById(id);
		if(!opProduct.isPresent()) {
			throw new ResourceNotFoundException(Long.toString(id), "Product not found");
		}
		return opProduct.get();
	}

	@Override
	public void delete(Long id) {
		Optional<Product> opProduct = productRepository.findById(id);
		if(!opProduct.isPresent()) {
			throw new ResourceNotFoundException(Long.toString(id), "Product not found");
		}
		productRepository.deleteById(id);	
	}

}
