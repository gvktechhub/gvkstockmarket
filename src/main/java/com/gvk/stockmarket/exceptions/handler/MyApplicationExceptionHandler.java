package com.gvk.stockmarket.exceptions.handler;

import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.gvk.stockmarket.exceptions.StockNameIdNotFoundException;

//@ControllerAdvice
public class MyApplicationExceptionHandler {
	
	//@ExceptionHandler(StockNameIdNotFoundException.class)
	public String handleStockNameIdNotFoundException(HttpRequest request) {
		return "error1";
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<String> handleRuntimeException() {
		return new ResponseEntity<String>("It is runtime error", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException() {
		return new ResponseEntity<String>("It is an exception", HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
