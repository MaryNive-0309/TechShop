package com.hexaware.techshop.entity;

import java.time.LocalDate;
import java.util.Date;

import com.hexaware.techshop.exception.InsufficientStockException;

public class Inventory {
	
	private int inventoryId;
	private Product product;
	private int quantityInStock;
	private LocalDate lastStockUpdate;	

	public Inventory(Product product, int quantityInStock, LocalDate lastStockUpdate) {
		this.product = product;
		this.quantityInStock = quantityInStock;
		this.lastStockUpdate = lastStockUpdate;
	}


	public Inventory() {

	}


	public int getInventoryId() {
		return inventoryId;
	}


	public void setInventoryId(int inventoryId) {
		this.inventoryId = inventoryId;
	}


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}


	public int getQuantityInStock() {
		return quantityInStock;
	}


	public void setQuantityInStock(int quantityInStock) {
		this.quantityInStock = quantityInStock;
	}


	public LocalDate getLastStockUpdate() {
		return lastStockUpdate;
	}


	public void setLastStockUpdate(LocalDate lastStockUpdate) {
		this.lastStockUpdate = lastStockUpdate;
	}

	
	public void addToInventory(int quantity) {
		this.quantityInStock +=quantity;
		lastStockUpdate = LocalDate.now();
		
	}
	
	public void removeFromInventory(int quantity) throws InsufficientStockException {
		if(quantity>quantityInStock) {
			throw new InsufficientStockException("Insufficient stock to remove");
		}
		this.quantityInStock -= quantity;
		lastStockUpdate = LocalDate.now();
	}
	
	public void updateStockQuantity(int newQuantity) throws InsufficientStockException {
		if(newQuantity < 0) {
			throw new InsufficientStockException("Stock cannot ne null");
		}
		this.quantityInStock= newQuantity;
	}
	
	public boolean isProductAvailable(int quantityToCheck) {
		return quantityInStock >=quantityToCheck;
	}
	
	public double getInventoryValue() {
		return product.getPrice()*quantityInStock;
		
		
	}
	
	public void listLowStockProducts(int threshold) {
		if(quantityInStock < threshold) {
			System.out.println("Low stock: "+product.getProductName());
		}
	}
	
	public void listOutOfStockProducts() {
		if(quantityInStock == 0) {
			System.out.println("Out of Stock: "+product.getProductName());
		}
		
	}
	
	public void listAllProducts() {
		System.out.println("Listing products: "+"\nProductId: "+product.getProductId()+"\nProduct: "+product.getProductName()+"\nQuantity in stock: "+quantityInStock);
		
	}
	
}
