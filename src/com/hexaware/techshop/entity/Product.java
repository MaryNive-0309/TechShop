package com.hexaware.techshop.entity;

import com.hexaware.techshop.exception.InvalidDataException;

public class Product {
	
	private int productId;
	private String productName;
	private String description;
	private double price;
		

	public Product(int productId, String productName, String description, double price) throws InvalidDataException {
		this.productId = productId;
		this.productName = productName;
		this.description = description;
		setPrice(price);
	}


	public Product() {

	}


	public int getProductId() {
		return productId;
	}


	public void setProductId(int productId) {
		this.productId = productId;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) throws InvalidDataException {
		if(price<0)  throw new InvalidDataException("Price must be non-negative");
		this.price = price;
	}

	public void getProductDetails() {
	    System.out.println("Product ID: " + productId);
	    System.out.println("Product Name: " + productName);
	    System.out.println("Description: " + description);
	    System.out.println("Price: " + price);
	}

	public void updateProductInfo(String description, double price) throws InvalidDataException {
		setDescription(description);
	    setPrice(price);
	}

	public boolean isProductInStock(int quantityInStock) {
		return quantityInStock > 0;
	}
	


}
