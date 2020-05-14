package com.saurav.flashsale.exception;

import com.saurav.flashsale.errors.ErrorMessages;

public class RegistrationClosedException extends RuntimeException {

	private static final long serialVersionUID = 8932285241218100453L;

	public RegistrationClosedException() {
		super(ErrorMessages.REGISTRATION_CLOSED_MESSAGE);
	}

}
