package com.hexaware.techshop.entity;

public class Payment {

	private int paymentId;
	private Order order;
	private String method;
	private double amount;
	private String paymentStatus;

	public Payment(Order order, String method, double amount, String paymentStatus) {
		this.order = order;
		this.method = method;
		this.amount = amount;
		this.paymentStatus = paymentStatus;
	}

	public Payment() {

	}

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	@Override
	public String toString() {
		return "PaymentId= " + paymentId + ", Method=" + method + ", Amount=" + amount + ", PaymentStatus="
				+ paymentStatus + "]\n";
	}

}
