package com.hexaware.techshop.dao;

import java.util.List;

import com.hexaware.techshop.entity.Payment;

public interface IPaymentDAO {
	
	public void insertPayment(Payment payment);
	public List<Payment> getPaymentByOrderId(int orderId);
	public List<Payment> getAllPayments();
	
}
