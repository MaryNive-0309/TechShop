package com.hexaware.techshop.service;

import java.util.List;

import com.hexaware.techshop.dao.CustomerDAOImpl;
import com.hexaware.techshop.dao.ICustomerDAO;
import com.hexaware.techshop.entity.Customer;
import com.hexaware.techshop.exception.InvalidDataException;

public class CustomerService {
	
	ICustomerDAO customerDao=new CustomerDAOImpl();
	
	public String registerCustomer(Customer customer) throws InvalidDataException {
		
		if(customer==null) {
			throw new InvalidDataException("Invalid customer data");
		}		
		Customer exists = customerDao.getCustomerById(customer.getCustomerId());
		if(exists!=null) {
			throw new InvalidDataException("CustomerId already exists");
		}
		customerDao.insertCustomer(customer);
		return "Registered successfully";		
	}
	
	public String updateCustomer(int customerId,String email, String phone, String address) throws InvalidDataException {
		
		Customer customer = customerDao.getCustomerById(customerId);
		if(customer==null) {
			throw new InvalidDataException("CustomerId not found");
		}
		
		customer.setEmail(email);
		customer.setPhone(phone);
		customer.setAddress(address);
		boolean success = customerDao.updateCustomer(customer);
		if(!success) {
			throw new InvalidDataException("Failed to update customer detail");
		}
		return "Updated successfully";
	}
	
	public String deleteCustomer(int customerId) throws InvalidDataException {
		Customer customer= customerDao.getCustomerById(customerId);
		if(customer==null) {
			throw new InvalidDataException("CustomerId not found");
		}
		customerDao.deleteCustomer(customerId);
		return "Removed successfully";
	}
	
	public List<Customer> getAllCustomer() throws InvalidDataException{
		return customerDao.getAllCustomer();
	}

	public Customer getCustomerById(int customerId) throws InvalidDataException {
		Customer customer=customerDao.getCustomerById(customerId);
		if(customer==null) {
			throw new InvalidDataException("CustomerId not found");
		}
		return customer;

	}
}
