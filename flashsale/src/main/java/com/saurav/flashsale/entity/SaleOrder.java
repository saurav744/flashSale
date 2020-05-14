package com.saurav.flashsale.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.saurav.flashsale.entities.enums.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Getter @Setter @NoArgsConstructor
@AllArgsConstructor
public class SaleOrder {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@OneToOne
	@JoinColumn(name="USER_ID")
	private User user;
	@ManyToOne
	@JoinColumn(name="PRODUCT_ID")
	private Product product;
	private Date createdAt;
	private OrderStatus status;

}
