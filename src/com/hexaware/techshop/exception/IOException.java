package com.hexaware.techshop.exception;

public class IOException extends Exception{

	public IOException() {
		System.out.println("IOException");
	}
	
	public IOException(String msg) {
		super(msg);
	}
}
