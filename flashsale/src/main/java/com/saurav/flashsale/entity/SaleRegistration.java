package com.saurav.flashsale.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Getter @Setter @NoArgsConstructor
@AllArgsConstructor
public class SaleRegistration {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@OneToOne
	@JoinColumn(name="SALE_ID")
	private FlashSale flashSale;
	@OneToOne
	@JoinColumn(name="USER_ID")
	private User user;

}
