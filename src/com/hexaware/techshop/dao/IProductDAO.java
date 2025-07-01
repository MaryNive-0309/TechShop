package com.hexaware.techshop.dao;

import java.util.List;

import com.hexaware.techshop.entity.Product;
import com.hexaware.techshop.exception.InvalidDataException;

public interface IProductDAO {
	
	public boolean insertProduct(Product product);
	public boolean updateProduct(Product product);
	public boolean deleteProduct(int productId);
	List<Product> getAllProduct() throws InvalidDataException;
	public Product getProductById(int productId) throws InvalidDataException;
	public List<Product> searchProductByName(String name) throws InvalidDataException;

}
