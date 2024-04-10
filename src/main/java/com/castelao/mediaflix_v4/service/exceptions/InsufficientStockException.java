package com.castelao.mediaflix_v4.service.exceptions;

public class InsufficientStockException extends RuntimeException {
	public InsufficientStockException() {
		super();
	}

	public InsufficientStockException(String message) {
		super(message);
	}

	public InsufficientStockException(String message, Throwable cause) {
		super(message, cause);
	}
}