package com.saurav.flashsale.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saurav.flashsale.entity.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long>{
	
	Inventory findByProductId(Long productId);

}
