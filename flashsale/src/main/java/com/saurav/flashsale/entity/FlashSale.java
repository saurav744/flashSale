package com.saurav.flashsale.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.saurav.flashsale.entities.enums.FlashSaleStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Getter @Setter @AllArgsConstructor
@NoArgsConstructor
public class FlashSale {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
    @OneToOne
    @JoinColumn(name="PRODUCT_ID", unique = true)
	private Product product;
	private FlashSaleStatus status;
	private Boolean isRegistrationOpen;

}
