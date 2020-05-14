package com.saurav.flashsale.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saurav.flashsale.entity.FlashSale;

@Repository
public interface FlashSaleRepository extends JpaRepository<FlashSale, Long>{

}
