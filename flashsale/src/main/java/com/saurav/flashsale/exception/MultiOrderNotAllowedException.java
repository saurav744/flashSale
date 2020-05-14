package com.saurav.flashsale.exception;

import com.saurav.flashsale.errors.ErrorMessages;

public class MultiOrderNotAllowedException extends RuntimeException{
	
	private static final long serialVersionUID = -9073286490242883292L;

	public MultiOrderNotAllowedException() {
		super(ErrorMessages.MULTIPLE_ORDERS_NOT_ALLOWED_EXCEPTION);
	}

}
