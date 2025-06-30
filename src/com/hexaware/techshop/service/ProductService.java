package com.hexaware.techshop.service;

import java.util.ArrayList;
import java.util.List;

import com.hexaware.techshop.dao.IProductDAO;
import com.hexaware.techshop.dao.ProductDAOImpl;
import com.hexaware.techshop.entity.Product;
import com.hexaware.techshop.exception.InvalidDataException;

public class ProductService {
	
	IProductDAO productDao=new ProductDAOImpl();
	List<Product> product=new ArrayList<>();
	
	public void addProduct(Product product) throws InvalidDataException {
		if(product==null) {
			throw new InvalidDataException("Invalid product");
		}
		productDao.insertProduct(product);
	}
	
	public void updateProduct(int productId,String description,double price) throws InvalidDataException {
		Product product=productDao.getProductById(productId);
		if(product==null) {
			throw new InvalidDataException("ProductId not found");
		}
		product.setDescription(description);
		product.setPrice(price);
		productDao.updateProduct(product);
	}

	public void removeProduct(int productId) throws InvalidDataException {
		Product product=productDao.getProductById(productId);
		if(product==null) {
			throw new InvalidDataException("ProductId not found");
		}
		productDao.deleteProduct(productId);
	}
	
	
	public List<Product> getAllProducts() throws InvalidDataException{
		return productDao.getAllProduct();
	}
	
	public Product getProductById(int productId) throws InvalidDataException {
		return productDao.getProductById(productId);
	}
	
	public List<Product> searchProductByName(String name){
		List<Product> prod=new ArrayList<>();
		for(Product p: product) {
			if(p.getProductName().contains(name)) {
				prod.add(p);
			}
		}
		return prod;
	}
	
	public boolean isDuplicateProduct(String name) {
		for(Product p:product) {
			if(p.getProductName().equalsIgnoreCase(name)) {
				return true;
			}
		}
		return false;
	}
	
}
