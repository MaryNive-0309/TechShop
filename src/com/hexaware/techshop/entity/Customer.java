package com.hexaware.techshop.entity;

import com.hexaware.techshop.exception.InvalidDataException;

public class Customer {

	private int customerId;
	private	String firstName;
	private	String lastName;
	private	String email;
	private	String phone;
	private	String address;	
	
	public Customer(String firstName, String lastName, String email, String phone, String address) throws InvalidDataException {
		setFirstName(firstName);
		setLastName(lastName);
		setEmail(email);
		setPhone(phone);
		this.address = address;
	}


	public Customer() {

	}


	public int getCustomerId() {
		return customerId;
	}


	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		String pattern="^[A-Za-z]+";
		if(firstName.matches(pattern))
			this.firstName = firstName;
		else
			System.out.println("Error: Name is not in Alphabets");
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) throws InvalidDataException {
		String pattern="^[A-Za-z]+";
		if(lastName.matches(pattern))
			this.lastName = lastName;
		else
			 throw new InvalidDataException("Error: Name should be in Alphabets");
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) throws InvalidDataException {
		if (email.contains("@") && email.contains(".")) {
            this.email = email;
		}
		else
			 throw new InvalidDataException("Invalid email");
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) throws InvalidDataException {
		if(phone.matches("[0-9]{10}")) {
            this.phone = phone;
        } 
		else 
			throw new InvalidDataException("Invalid phone number.");     
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}

	
	public void getCustomerDetails() {
		System.out.println("Customer ID: " + customerId);
        System.out.println("Name: " + firstName + " " + lastName);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phone);
        System.out.println("Address: " + address);
        
	}

	public void updateCustomerInfo(String email, String phone, String address) throws InvalidDataException {
        setEmail(email);
        setPhone(phone);
        setAddress(address);
    }
	
	public int calculateTotalOrders() {
		
        return 0;
    }

}
