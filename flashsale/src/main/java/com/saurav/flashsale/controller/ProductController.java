package com.saurav.flashsale.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.saurav.flashsale.entities.enums.OrderStatus;
import com.saurav.flashsale.entity.FlashSale;
import com.saurav.flashsale.entity.Product;
import com.saurav.flashsale.entity.request_response.OrderRequest;
import com.saurav.flashsale.entity.request_response.OrderResponse;
import com.saurav.flashsale.entity.request_response.ProductRequest;
import com.saurav.flashsale.service.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "FlashSale Product APIs")
@RestController
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@ApiOperation(value = "Create product")
	@PostMapping("/product")
	public ResponseEntity<Product> addProduct(
			@ApiParam(value = "product request", required = true) @RequestBody ProductRequest productRequest) {
		
		Product response = productService.add(productRequest);
		return new ResponseEntity<Product>(response, HttpStatus.CREATED);	
	}
	
	@ApiOperation(value = "Updates the product")
	@PutMapping("/product/{id}")
	public ResponseEntity<Product> updateProduct(
			@ApiParam(value = "Unique Id of the prdouct", required = true) @PathVariable Long id,
			@ApiParam(value = "updated body of product", required = true) @RequestBody ProductRequest productRequest ) {
		Product response = productService.update(id, productRequest);
		return new ResponseEntity<Product>(response, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Returns the product by id")
	@GetMapping("/product/{id}")
	public Product getProductById(@ApiParam(value = "Unique product id", required = true) @PathVariable Long id) {
		
		return productService.get(id);
	}
	
	@ApiOperation(value = "Delete product by id")
	@DeleteMapping("/product/{id}")
	public ResponseEntity<String> deleteProduct(
			@ApiParam(value = "Unique Id of the product", required = true) @PathVariable Long id) {
		productService.delete(id);
		return new ResponseEntity<String>("Deleted succesfully", HttpStatus.OK);
	}

}
