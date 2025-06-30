package com.hexaware.techshop.service;

import java.util.ArrayList;
import java.util.List;

import com.hexaware.techshop.dao.IPaymentDAO;
import com.hexaware.techshop.dao.PaymentDAOImpl;
import com.hexaware.techshop.entity.Payment;
import com.hexaware.techshop.exception.PaymentNotFoundException;

public class PaymentService {
	
	List<Payment> paymentList=new ArrayList<>();
	
	IPaymentDAO paymentDao = new PaymentDAOImpl(); 
	
	public void recordPayment(Payment payment) {
		paymentDao.insertPayment(payment);
	}
	
	public List<Payment> getPaymentsByOrderId(int orderId) throws PaymentNotFoundException {
		List<Payment> list = paymentDao.getPaymentByOrderId(orderId);
		if(list.isEmpty()) {
			throw new PaymentNotFoundException("No payment record found for OrderId: "+orderId);
		}
		return list;
	}
	
	public List<Payment> getAllPayments(){
		return paymentDao.getAllPayments();
	}

}
