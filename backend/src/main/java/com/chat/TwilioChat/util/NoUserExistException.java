package com.chat.TwilioChat.util;

public class NoUserExistException extends Exception {
    	/**
	 * Instantiates a new user email already exist exception.
	 */
	public NoUserExistException() {
		super();
	}

	public NoUserExistException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

	public NoUserExistException(String message, Throwable cause) {
		super(message, cause);

	}

	public NoUserExistException(String message) {
		super(message);

	}

	public NoUserExistException(Throwable cause) {
		super(cause);

	}
}
