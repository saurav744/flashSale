package com.saurav.flashsale.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saurav.flashsale.entity.SaleOrder;
import com.saurav.flashsale.entity.Product;
import com.saurav.flashsale.entity.User;

@Repository
public interface OrderRepository extends JpaRepository<SaleOrder, Long>{
	
	Optional<SaleOrder> findByUserAndProduct(User user, Product product);

}
