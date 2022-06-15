package com.chat.TwilioChat.util;

public class AlreadyExistException extends Exception {

	/**
	 * Instantiates a new user email already exist exception.
	 */
	public AlreadyExistException() {
		super();
	}

	public AlreadyExistException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

	public AlreadyExistException(String message, Throwable cause) {
		super(message, cause);

	}

	public AlreadyExistException(String message) {
		super(message);

	}

	public AlreadyExistException(Throwable cause) {
		super(cause);

	}
}
