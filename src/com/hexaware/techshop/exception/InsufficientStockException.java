package com.hexaware.techshop.exception;

public class InsufficientStockException extends Exception{

	public InsufficientStockException() {
		System.out.println("Insufficient Stock");
	}
	
	public InsufficientStockException(String msg) {
		super(msg);
	}
}
