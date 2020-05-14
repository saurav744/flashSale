package com.saurav.flashsale.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saurav.flashsale.entity.FlashSale;
import com.saurav.flashsale.entity.SaleRegistration;
import com.saurav.flashsale.entity.User;

@Repository
public interface SaleRegistrationRepository extends JpaRepository<SaleRegistration, Long>{
	
	Optional<SaleRegistration> findByUserAndFlashSale(User user, FlashSale flashSale);
}
