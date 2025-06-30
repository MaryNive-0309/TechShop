package com.hexaware.techshop.exception;

public class InvalidDataException extends Exception{

	public InvalidDataException() {
		System.out.println("Invalid Data");
	}
	
	public InvalidDataException(String msg) {
		super(msg);
	}
}
