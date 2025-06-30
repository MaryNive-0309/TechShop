package com.hexaware.techshop.entity;

import java.util.Date;
import java.util.List;

import com.hexaware.techshop.exception.InvalidDataException;

public class Order {
	
	private int orderId;
    private Customer customer;
    private Date orderDate;
    private double totalAmount;
    private String orderStatus;

	public Order(int orderId, Customer customer, Date orderDate, double totalAmount, String orderStatus) {
		this.orderId = orderId;
		this.customer = customer;
		this.orderDate = orderDate;
		this.totalAmount = totalAmount;
		this.orderStatus = orderStatus;
	}

	public Order() {

	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public void calculateTotalAmount(List<OrderDetail> orderDetails) {
		totalAmount=0;
		for(OrderDetail od: orderDetails) {
			totalAmount +=od.calculateSubtotal();
		}
	}
	 
	public void getOrderDetails(List<OrderDetail> orderDetails) {
		for(OrderDetail od: orderDetails) {
			od.getOrderDetailInfo();
		}
	}
	
	public void updateOrderStatus(String newStatus) {
		this.orderStatus=newStatus;
	}
	
	public void cancelOrder(List<OrderDetail> orderDetails) throws InvalidDataException {
		for(OrderDetail od: orderDetails) {
			od.getProduct().setPrice(od.getProduct().getPrice());
		}
		this.orderStatus="Cancelled";
	}
	
}
