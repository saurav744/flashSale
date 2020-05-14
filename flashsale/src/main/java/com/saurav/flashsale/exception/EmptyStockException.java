package com.saurav.flashsale.exception;

import com.saurav.flashsale.errors.ErrorMessages;

public class EmptyStockException extends RuntimeException {
	
	private static final long serialVersionUID = -9073286490242883296L;

	public EmptyStockException() {
		super(ErrorMessages.EMPTY_STOCK_MESSAGE);
	}
	

}
