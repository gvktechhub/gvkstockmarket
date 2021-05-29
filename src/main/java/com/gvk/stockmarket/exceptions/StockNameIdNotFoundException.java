package com.gvk.stockmarket.exceptions;

public class StockNameIdNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public StockNameIdNotFoundException() {
		super();
	}
	
	public StockNameIdNotFoundException(String message) {
		super(message);
	}

}
