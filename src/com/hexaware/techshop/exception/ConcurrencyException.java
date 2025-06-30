package com.hexaware.techshop.exception;

public class ConcurrencyException extends Exception{
	
	public ConcurrencyException() {
		System.out.println("");
	}

	public ConcurrencyException(String msg) {
		super(msg);
	}
}
