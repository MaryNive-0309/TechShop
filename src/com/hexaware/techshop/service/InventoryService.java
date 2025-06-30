package com.hexaware.techshop.service;

import java.util.ArrayList;
import java.util.Collection;
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
	
	TreeMap<Integer,Inventory> invMap=new TreeMap<>();
	
	IInventoryDAO invDao=new InventoryDAOImpl();
	
	public void addToInventory(int productId, int quantity) throws InvalidDataException {
		Inventory inv= invMap.get(productId);
		if(inv==null) {
			throw new InvalidDataException("Product not found in inventory");
		}
		inv.addToInventory(quantity);
		inv.setLastStockUpdate(new Date());
	}
	
	
	public void addProductToInventory(Product product, int quantity) throws InvalidDataException {
		
		if(product==null || product.getProductId()<=0) {
			throw new InvalidDataException("Invalid ProductId");
		}
		
		int productId =product.getProductId();
		if(invMap.containsKey(productId)) {
			throw new InvalidDataException("Duplicate Product");
		}
		Inventory inv=new Inventory(invMap.size()+1, product, quantity, new Date());
		invMap.put(productId, inv);
	}

	public void removeProductFromInventory(int productId) throws InvalidDataException {
		if(!invMap.containsKey(productId)) {
			throw new InvalidDataException("PRoduct not found in inventory");
		}
		invMap.remove(productId);
	}
	
	public void updateInventory(List<OrderDetail> orderDetail) throws InsufficientStockException {
		Inventory		
	}
	
	public void updateStock(int productId, int quantity) throws InvalidDataException, InsufficientStockException {
		Inventory inv=invDao.getInventoryByProductId(productId);
		if(inv==null || inv.getQuantityInStock() < quantity) {
			throw new InsufficientStockException("Not enough stock for productId: "+productId);
		}
		invDao.updateStock(productId, quantity);
	}
	
	public Inventory getInventoryByProductId(int productId) throws InvalidDataException {
		return invDao.getInventoryByProductId(productId);
	}
	
	public List<Inventory> listLowStock(int threshold){
		return invDao.listLowStock(threshold);
	}
	
	public List<Inventory> getOutOfStock(){
		return invDao.getOutOfStock();
	}
	
/*	public List<Inventory> listLowStock(int threshold){
		List<Inventory> lowStockList=new ArrayList<>();
		for(Inventory inv:invMap.values()) {
			if(inv.getQuantityInStock() < threshold) {
				lowStockList.add(inv);
			}
		}
		return lowStockList;
	}
	*/
	public List<Inventory> listOutOfStock(){
		List<Inventory> outOfStockList = new ArrayList<>();
		for(Inventory inv:invMap.values()) {
			if(inv.getQuantityInStock()==0) {
				outOfStockList.add(inv);
			}
		}
		return outOfStockList;
	}
	
	
	public void addInventory() {
		return invDao.a
	}
	
	
}
