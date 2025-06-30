package com.hexaware.techshop.dao;

import java.util.List;

import com.hexaware.techshop.entity.Product;
import com.hexaware.techshop.exception.InvalidDataException;

public interface IProductDAO {
	
	public void insertProduct(Product product);
	public void updateProduct(Product product);
	public void deleteProduct(int productId);
	List<Product> getAllProduct() throws InvalidDataException;
	public Product getProductById(int productId) throws InvalidDataException;

}
