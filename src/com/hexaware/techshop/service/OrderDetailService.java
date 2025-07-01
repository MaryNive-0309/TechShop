package com.hexaware.techshop.service;

import com.hexaware.techshop.entity.OrderDetail;
import com.hexaware.techshop.exception.InvalidDataException;

public class OrderDetailService {
	
	public double calculateSubtotal(OrderDetail orderDetail) {
		double subtotal= orderDetail.getProduct().getPrice() * orderDetail.getQuantity();
		return subtotal;
	}

	public void updateQuantity(OrderDetail orderDetail, int newQuantity) throws InvalidDataException {
	  	orderDetail.setQuantity(newQuantity);
	}

}
