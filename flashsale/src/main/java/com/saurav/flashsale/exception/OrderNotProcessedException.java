package com.saurav.flashsale.exception;

import com.saurav.flashsale.errors.ErrorMessages;

public class OrderNotProcessedException extends RuntimeException {
	
	private static final long serialVersionUID = -9073286490242882296L;

	public OrderNotProcessedException() {
		super(ErrorMessages.ORDER_NOT_PROCESSED);
	}
	
}
