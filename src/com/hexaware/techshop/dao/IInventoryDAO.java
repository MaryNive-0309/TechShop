package com.hexaware.techshop.dao;

import com.hexaware.techshop.entity.Inventory;
import com.hexaware.techshop.exception.InvalidDataException;

public interface IInventoryDAO {

	public void insertInventory(Inventory inventory);
	public void updateStock(int productId, int newQuantity);
	public void deleteInventoryByProductId(int productId);
	public Inventory getInventoryByProductId(int productId) throws InvalidDataException;
}
