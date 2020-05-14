package com.saurav.flashsale.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.saurav.flashsale.entities.enums.FlashSaleStatus;
import com.saurav.flashsale.entities.enums.OrderStatus;
import com.saurav.flashsale.entity.FlashSale;
import com.saurav.flashsale.entity.Inventory;
import com.saurav.flashsale.entity.SaleOrder;
import com.saurav.flashsale.entity.Product;
import com.saurav.flashsale.entity.SaleRegistration;
import com.saurav.flashsale.entity.User;
import com.saurav.flashsale.entity.request_response.OrderRequest;
import com.saurav.flashsale.entity.request_response.OrderResponse;
import com.saurav.flashsale.exception.EmptyStockException;
import com.saurav.flashsale.exception.MultiOrderNotAllowedException;
import com.saurav.flashsale.exception.NotRegisteredException;
import com.saurav.flashsale.exception.OrderNotProcessedException;
import com.saurav.flashsale.exception.ResourceNotFoundException;
import com.saurav.flashsale.exception.SaleNotOnException;
import com.saurav.flashsale.repository.FlashSaleRepository;
import com.saurav.flashsale.repository.InventoryRepository;
import com.saurav.flashsale.repository.OrderRepository;
import com.saurav.flashsale.repository.SaleRegistrationRepository;
import com.saurav.flashsale.repository.UserRepository;

@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private InventoryRepository inventoryRepository;
	@Autowired
	private FlashSaleRepository flashSaleRepository;
	@Autowired
	private SaleRegistrationRepository saleRegistrationRepository;
	@Autowired
	private UserRepository userRepository;

	@Override
	public OrderResponse placeOrder(OrderRequest orderRequest) {
		
		FlashSale flashSale = flashSaleRepository.getOne(orderRequest.getFlashSaleId());
		if(flashSale.getStatus() != FlashSaleStatus.ONGOING) {
			throw new SaleNotOnException();
		}
		
		User user = userRepository.getOne(orderRequest.getUserId());
		Optional<SaleRegistration> opSaleRegistration = saleRegistrationRepository
				.findByUserAndFlashSale(user, flashSale);
		if(!opSaleRegistration.isPresent()) {
			throw new NotRegisteredException();
		}
		
		Product product = flashSale.getProduct();
		Optional<SaleOrder> opOrder = orderRepository.findByUserAndProduct(user, product);
		if(opOrder.isPresent() && opOrder.get().getStatus() != OrderStatus.CANCELLED) {
			throw new MultiOrderNotAllowedException();
		}
		
		boolean purchaseSuccessful = false;
		synchronized (this) {
			Inventory inventory = inventoryRepository.findByProductId(product.getId());
			int count = inventory.getCount();
			if (count > 0) {
				inventory.setCount(count - 1);
				inventoryRepository.save(inventory);
				purchaseSuccessful = true;
			} else {
				throw new EmptyStockException();
			}
		}
		if(purchaseSuccessful) {
			SaleOrder saleOrder = new SaleOrder();
			saleOrder.setProduct(product);
			saleOrder.setUser(user);
			saleOrder.setStatus(OrderStatus.CONFIRMED);
			saleOrder = orderRepository.save(saleOrder);
			
			OrderResponse response = new OrderResponse();
			response.setOrderId(saleOrder.getId());
			response.setProduct(saleOrder.getProduct().getName());
			response.setStatus(saleOrder.getStatus());
			response.setCreatedAt(saleOrder.getCreatedAt());
			
			return response;
			
		} else {
			throw new OrderNotProcessedException();
		}
		
	}

	@Override
	public OrderResponse updateStatus(Long orderId, OrderStatus status) {
		Optional<SaleOrder> opOrder = orderRepository.findById(orderId);
		if(!opOrder.isPresent()) {
			throw new ResourceNotFoundException(Long.toString(orderId), "Order not found");
		}
		SaleOrder saleOrder = opOrder.get();
		saleOrder.setStatus(status);
		saleOrder = orderRepository.save(saleOrder);
		OrderResponse orderResponse = new OrderResponse(saleOrder.getId(), saleOrder.getProduct().getName(),
				saleOrder.getCreatedAt(), saleOrder.getStatus());
		return orderResponse;
	}

}
