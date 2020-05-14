package com.saurav.flashsale.service;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.Optional;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

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
import com.saurav.flashsale.exception.SaleNotOnException;
import com.saurav.flashsale.repository.FlashSaleRepository;
import com.saurav.flashsale.repository.InventoryRepository;
import com.saurav.flashsale.repository.OrderRepository;
import com.saurav.flashsale.repository.SaleRegistrationRepository;
import com.saurav.flashsale.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {
	
	@Autowired
	private OrderService orderService;
	@MockBean
	private OrderRepository orderRepository;
	@MockBean
	private InventoryRepository inventoryRepository;
	@MockBean
	private FlashSaleRepository flashSaleRepository;
	@MockBean
	private SaleRegistrationRepository saleRegistrationRepository;
	@MockBean
	private UserRepository userRepository;
	
	private static User mockUser = new User((long) 1, "user1", "email1", "adress1", "12345");
	private static Product mockProduct = new Product(1L, "Xiami", 100D, "description");
	private static Inventory mockInventory = new Inventory(1L, mockProduct, 1);
	private static FlashSale mockSale = new FlashSale(1L, mockProduct, FlashSaleStatus.ONGOING, false);
	private static SaleRegistration mockRegistration = new SaleRegistration(1L, mockSale, mockUser);
	private static Optional<SaleRegistration> opMockRegistration = Optional.of(mockRegistration);
	private static Optional<SaleOrder> opMockOrder = Optional.empty();
	private static SaleOrder order = new SaleOrder(1L, mockUser, mockProduct, new Date(), OrderStatus.CONFIRMED);

	private static SaleOrder mockOrder = new SaleOrder(1L, mockUser, mockProduct, new Date(), OrderStatus.CONFIRMED);
	
	@BeforeClass
	public static void setup() {
		
	}
	
	@Test
	public void testPlaceOrder_001() throws Exception {
		Mockito.when(
				flashSaleRepository.getOne(Mockito.anyLong())
				).thenReturn(mockSale);
		
		Mockito.when(
				userRepository.getOne(Mockito.anyLong())
				).thenReturn(mockUser);
		
		Mockito.when(
				saleRegistrationRepository
				.findByUserAndFlashSale(mockUser, mockSale)
				).thenReturn(opMockRegistration);
		
		
		Mockito.when(
				inventoryRepository.findByProductId(Mockito.anyLong())
				).thenReturn(mockInventory);
		
		Mockito.when(
				orderRepository.findByUserAndProduct(mockUser, mockProduct)
				).thenReturn(opMockOrder);
		
		Mockito.when(
				orderRepository.save(Mockito.any(SaleOrder.class))
				).thenReturn(mockOrder);
		
		OrderRequest request = new OrderRequest(1L, 1L);
		OrderResponse response = orderService.placeOrder(request);
		
		assertEquals("Product name does not match", mockOrder.getProduct().getName(), response.getProduct());
		assertEquals("Product name does not match", OrderStatus.CONFIRMED, response.getStatus());
		assertEquals("Count does not match", (Integer)0, mockInventory.getCount());
		
	}
	
	@Test(expected = NotRegisteredException.class)
	public void testPlaceOrder_002() throws Exception {
		opMockRegistration = Optional.empty();
		Mockito.when(
				flashSaleRepository.getOne(Mockito.anyLong())
				).thenReturn(mockSale);
		
		Mockito.when(
				userRepository.getOne(Mockito.anyLong())
				).thenReturn(mockUser);
		
		Mockito.when(
				saleRegistrationRepository
				.findByUserAndFlashSale(mockUser, mockSale)
				).thenReturn(opMockRegistration);
		
		
		Mockito.when(
				inventoryRepository.findByProductId(Mockito.anyLong())
				).thenReturn(mockInventory);
		
		Mockito.when(
				orderRepository.findByUserAndProduct(mockUser, mockProduct)
				).thenReturn(opMockOrder);
		
		Mockito.when(
				orderRepository.save(Mockito.any(SaleOrder.class))
				).thenReturn(mockOrder);
		
		OrderRequest request = new OrderRequest(1L, 1L);
		OrderResponse response = orderService.placeOrder(request);
		
	}
	
	@Test(expected = SaleNotOnException.class)
	public void testPlaceOrder_003() throws Exception {
		opMockRegistration = Optional.of(mockRegistration);
		mockSale.setStatus(FlashSaleStatus.CLOSED);
		
		Mockito.when(
				flashSaleRepository.getOne(Mockito.anyLong())
				).thenReturn(mockSale);
		
		Mockito.when(
				userRepository.getOne(Mockito.anyLong())
				).thenReturn(mockUser);
		
		Mockito.when(
				saleRegistrationRepository
				.findByUserAndFlashSale(mockUser, mockSale)
				).thenReturn(opMockRegistration);
		
		
		Mockito.when(
				inventoryRepository.findByProductId(Mockito.anyLong())
				).thenReturn(mockInventory);
		
		Mockito.when(
				orderRepository.findByUserAndProduct(mockUser, mockProduct)
				).thenReturn(opMockOrder);
		
		Mockito.when(
				orderRepository.save(Mockito.any(SaleOrder.class))
				).thenReturn(mockOrder);
		
		OrderRequest request = new OrderRequest(1L, 1L);
		OrderResponse response = orderService.placeOrder(request);
		
	}
	
	@Test(expected = MultiOrderNotAllowedException.class)
	public void testPlaceOrder_004() throws Exception {
		mockSale.setStatus(FlashSaleStatus.ONGOING);
		opMockOrder = Optional.of(order);
		
		Mockito.when(
				flashSaleRepository.getOne(Mockito.anyLong())
				).thenReturn(mockSale);
		
		Mockito.when(
				userRepository.getOne(Mockito.anyLong())
				).thenReturn(mockUser);
		
		Mockito.when(
				saleRegistrationRepository
				.findByUserAndFlashSale(mockUser, mockSale)
				).thenReturn(opMockRegistration);
		
		
		Mockito.when(
				inventoryRepository.findByProductId(Mockito.anyLong())
				).thenReturn(mockInventory);
		
		Mockito.when(
				orderRepository.findByUserAndProduct(mockUser, mockProduct)
				).thenReturn(opMockOrder);
		
		Mockito.when(
				orderRepository.save(Mockito.any(SaleOrder.class))
				).thenReturn(mockOrder);
		
		OrderRequest request = new OrderRequest(1L, 1L);
		OrderResponse response = orderService.placeOrder(request);
		
	}
	
	@Test(expected = EmptyStockException.class)
	public void testPlaceOrder_005() throws Exception {
		opMockOrder = Optional.empty();
		Mockito.when(
				flashSaleRepository.getOne(Mockito.anyLong())
				).thenReturn(mockSale);
		
		Mockito.when(
				userRepository.getOne(Mockito.anyLong())
				).thenReturn(mockUser);
		
		Mockito.when(
				saleRegistrationRepository
				.findByUserAndFlashSale(mockUser, mockSale)
				).thenReturn(opMockRegistration);
		
		
		Mockito.when(
				inventoryRepository.findByProductId(Mockito.anyLong())
				).thenReturn(mockInventory);
		
		Mockito.when(
				orderRepository.findByUserAndProduct(mockUser, mockProduct)
				).thenReturn(opMockOrder);
		
		Mockito.when(
				orderRepository.save(Mockito.any(SaleOrder.class))
				).thenReturn(mockOrder);
		
		OrderRequest request = new OrderRequest(1L, 1L);
		OrderResponse response = orderService.placeOrder(request);
		
	}

}
