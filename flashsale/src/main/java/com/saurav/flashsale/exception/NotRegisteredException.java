package com.saurav.flashsale.exception;

import com.saurav.flashsale.errors.ErrorMessages;

public class NotRegisteredException extends RuntimeException{
	
	private static final long serialVersionUID = 65756439077954193L;

	public NotRegisteredException() {
		super(ErrorMessages.NOT_REGISTERED_MESSAGE);
	}
	

}
