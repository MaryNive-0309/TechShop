package com.hexaware.techshop.dao;

import java.util.List;

import com.hexaware.techshop.entity.Payment;
import com.hexaware.techshop.exception.PaymentNotFoundException;

public interface IPaymentDAO {
	
	public boolean insertPayment(Payment payment);
	public List<Payment> getPaymentByOrderId(int orderId) throws PaymentNotFoundException;
	public List<Payment> getAllPayments();
	
}
