package com.hexaware.techshop.dao;

import java.util.List;

import com.hexaware.techshop.entity.Customer;
import com.hexaware.techshop.exception.InvalidDataException;

public interface ICustomerDAO {

	public boolean insertCustomer(Customer customer);

	public boolean updateCustomer(Customer customer);

	public boolean deleteCustomer(int customerId);

	List<Customer> getAllCustomer() throws InvalidDataException;

	Customer getCustomerById(int customerId) throws InvalidDataException;

}
