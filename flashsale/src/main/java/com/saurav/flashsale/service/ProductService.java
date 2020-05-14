package com.saurav.flashsale.service;

import com.saurav.flashsale.entity.Product;
import com.saurav.flashsale.entity.request_response.ProductRequest;

public interface ProductService {
	
	public Product add(ProductRequest prodRequest);
	
	public Product update(Long id, ProductRequest productRequest);
	
	public Product get(Long id);
	
	public void delete(Long id);

}
