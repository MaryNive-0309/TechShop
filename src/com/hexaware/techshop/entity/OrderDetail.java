package com.hexaware.techshop.entity;

import com.hexaware.techshop.exception.InvalidDataException;

public class OrderDetail {
	
	private int orderDetailId;
	private Order order;
	private Product product;
	private int quantity;	
    
    public OrderDetail(int orderDetailId, Order order, Product product, int quantity) throws InvalidDataException {
		this.orderDetailId = orderDetailId;
		this.order = order;
		this.product = product;
		setQuantity(quantity);
	}

    public OrderDetail() {
    	
    }


	public int getOrderDetailId() {
		return orderDetailId;
	}



	public void setOrderDetailId(int orderDetailId) {
		this.orderDetailId = orderDetailId;
	}



	public Order getOrder() {
		return order;
	}



	public void setOrder(Order order) {
		this.order = order;
	}



	public Product getProduct() {
		return product;
	}



	public void setProduct(Product product) {
		this.product = product;
	}



	public int getQuantity() {
		return quantity;
	}



	public void setQuantity(int quantity) throws InvalidDataException {
		if(quantity<=0) throw new InvalidDataException("Quantity must be greater than 0");
		this.quantity = quantity;
	}

	public double calculateSubtotal() {
		return product.getPrice()*quantity;
		
	}
	
	public void getOrderDetailInfo() {
		System.out.println("Product: "+ product.getProductName());
		System.out.println("Quantity: "+quantity);
		System.out.println("Subtotal price: "+ calculateSubtotal());
		
	}
	
	public void updateQuantity(int newQuantity) throws InvalidDataException {
		setQuantity(newQuantity);
	}
	
	public void addDiscount(double discount) throws InvalidDataException {
		double discountprice= product.getPrice()*(1-discount/100);
		product.setPrice(discountprice);
	}
	
}
