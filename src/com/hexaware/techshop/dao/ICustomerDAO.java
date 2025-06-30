package com.hexaware.techshop.dao;

import java.util.List;

import com.hexaware.techshop.entity.Customer;
import com.hexaware.techshop.exception.InvalidDataException;

public interface ICustomerDAO {

	public void insertCustomer(Customer customer);
	public void updateCustomer(Customer customer);
	public void deleteCustomer(int customerId);
	List<Customer> getAllCustomer() throws InvalidDataException;
	Customer getCustomerById(int customerId) throws InvalidDataException;
	
}
