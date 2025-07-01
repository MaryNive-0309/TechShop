package com.hexaware.techshop.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import com.hexaware.techshop.dao.IInventoryDAO;
import com.hexaware.techshop.dao.InventoryDAOImpl;
import com.hexaware.techshop.entity.Inventory;
import com.hexaware.techshop.entity.OrderDetail;
import com.hexaware.techshop.entity.Product;
import com.hexaware.techshop.exception.InsufficientStockException;
import com.hexaware.techshop.exception.InvalidDataException;

public class InventoryService {
	
	IInventoryDAO invDao=new InventoryDAOImpl();
		
	public void addProductToInventory(Product product, int quantity) throws InvalidDataException {
		
		if(product==null || product.getProductId()<=0) {
			throw new InvalidDataException("Invalid Product");
		}	
		Inventory newInv=new Inventory(product, quantity,LocalDate.now());
		invDao.insertInventory(newInv);
		
	}

	public void removeProductFromInventory(int productId) throws InvalidDataException {
		Inventory inv=invDao.getInventoryByProductId(productId);
		if(inv==null) {
			throw new InvalidDataException("PRoduct not found in inventory");
		}
		invDao.deleteInventoryByProductId(productId);
	}
	
		
	public void updateInventoryQuantity(int productId, int newQuantity) throws InvalidDataException{
		Inventory inv = invDao.getInventoryByProductId(productId);
		if(inv == null) {
			throw new InvalidDataException("Product not found in inventory to update");
		}
	}
	
	
	public void updateStock(int productId, int quantity) throws InvalidDataException, InsufficientStockException {
		Inventory inv=invDao.getInventoryByProductId(productId);
		if(inv==null || inv.getQuantityInStock() < quantity) {
			throw new InsufficientStockException("Not enough stock for productId: "+productId);
		}
		int newQuantity=inv.getQuantityInStock()-quantity;
		invDao.updateStock(productId, newQuantity);
	}
	
	
	public Inventory getInventoryByProductId(int productId) throws InvalidDataException {
		Inventory inv=invDao.getInventoryByProductId(productId);
		if(inv==null) {
			throw new InvalidDataException("Product not found in inventory");
		}
		return inv;
	}
	

	public List<Inventory> listAllInventory(){
		return invDao.getAllInventory();
	}
	
	public List<Inventory> listLowStock(int threshold){

		List<Inventory> lowStock = new ArrayList<>();
		List<Inventory> invList = invDao.getAllInventory();
		for(Inventory inv: invList) {
			if(inv.getQuantityInStock()<threshold) {
				lowStock.add(inv);
			}
		}
		return lowStock;
	}	
	
	public List<Inventory> listOutOfStock(){
		List<Inventory> outOfStockList = new ArrayList<>();
		List<Inventory> invList = invDao.getAllInventory();
		for(Inventory inv : invList) {
			if(inv.getQuantityInStock()==0) {
				outOfStockList.add(inv);
			}
		}
		return outOfStockList;
	}
}
	
