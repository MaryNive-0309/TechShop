package com.hexaware.techshop.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Order {
	
	private int orderId;
    private Customer customer;
    private LocalDate orderDate;
    private double totalAmount;
    private String orderStatus;

	public Order(Customer customer, LocalDate orderDate, double totalAmount, String orderStatus) {
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

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
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

	List<OrderDetail> orderDetails = new ArrayList<>();
	
	public void calculateTotalAmount() {
		totalAmount=0;
		for(OrderDetail od: orderDetails) {
			totalAmount += od.calculateSubtotal();
		}
	}
	
	public void addOrderDetail(OrderDetail detail) {
		List<OrderDetail> orderDetails= new ArrayList<>();
		orderDetails.add(detail);
	}
	 
	public void getOrderDetails() {
		System.out.println("OrderId: "+orderId+ " for customer: "+customer.getFirstName());
		for(OrderDetail od: orderDetails) {
			od.getOrderDetailInfo();
		}
	}
	
	public void updateOrderStatus(String newStatus) {
		this.orderStatus=newStatus;
	}
	
	public void cancelOrder() {	
		this.orderStatus="Cancelled";
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", customer=" + (customer != null ? customer.getCustomerId() :"Null") + ", orderDate=" + orderDate + ", totalAmount="
				+ totalAmount + ", orderStatus=" + orderStatus + "]";
	}
	
}
