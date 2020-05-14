package com.saurav.flashsale.exception;

import com.saurav.flashsale.errors.ErrorMessages;

public class SaleNotOnException  extends RuntimeException {

	private static final long serialVersionUID = -9073286490242883296L;

	public SaleNotOnException() {
		super(ErrorMessages.SALE_NOT_ON_EXCEPTION);
	}

}
