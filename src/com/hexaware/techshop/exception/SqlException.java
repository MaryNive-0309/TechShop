package com.hexaware.techshop.exception;

public class SqlException extends Exception{

	public SqlException() {
		System.out.println("Database connection not found");		
	}
	
	public SqlException(String msg) {
		super(msg);
	}
}
