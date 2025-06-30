package com.hexaware.techshop.exception;

public class IncompleteOrderException extends Exception{

	public IncompleteOrderException() {
		System.out.println("Incomplete order details");
	}
	
	public IncompleteOrderException(String msg) {
		super(msg);
	}
}
