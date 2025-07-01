package com.hexaware.techshop.entity;

import com.hexaware.techshop.exception.InvalidDataException;

public class Product {

	private int productId;
	private String productName;
	private String description;
	private double price;
	private String category;
		

	public Product() {
	}

	public Product(String productName, String description, double price, String category) throws InvalidDataException {
		this.productName = productName;
		this.description = description;
		setPrice(price);
		this.category = category;
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


	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void getProductDetails() {
	    System.out.println("Product ID: " + productId);
	    System.out.println("Product Name: " + productName);
	    System.out.println("Description: " + description);
	    System.out.println("Price: " + price);
	    System.out.println("Category: "+category);
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", description=" + description
				+ ", price=" + price + ", category=" + category + "]\n";
	}
}
