package com.hexaware.techshop.exception;

public class AuthenticationException extends Exception{
	
	public AuthenticationException() {
		System.out.println("Authentication not secure");
	}
	
	public AuthenticationException(String msg) {
		super(msg);
	}

}
