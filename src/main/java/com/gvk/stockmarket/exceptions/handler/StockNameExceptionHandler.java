package com.gvk.stockmarket.exceptions.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.gvk.stockmarket.exceptions.StockNameIdNotFoundException;

@RestControllerAdvice
public class StockNameExceptionHandler {
	
	@ExceptionHandler(StockNameIdNotFoundException.class)
	public ResponseEntity<String> handleStockNameIdNotFoundException(Exception e) {
		return new ResponseEntity<String>(
				"Message from handleStockNameIdNotFoundException: "+e.getMessage(), 
				HttpStatus.NOT_FOUND
		 );
	}
	
	/*
	 * @ExceptionHandler(StockNameIdNotFoundException.class) public String
	 * handleStockNameIdNotFoundException(HttpRequest request) { return "error1"; }
	 */

}
