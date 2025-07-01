package com.hexaware.techshop.dao;

import java.util.List;

import com.hexaware.techshop.entity.Inventory;
import com.hexaware.techshop.exception.InvalidDataException;

public interface IInventoryDAO {

	public boolean insertInventory(Inventory inventory);

	public boolean updateStock(int productId, int newQuantity);

	public boolean deleteInventoryByProductId(int productId);

	public Inventory getInventoryByProductId(int productId) throws InvalidDataException;

	public List<Inventory> getAllInventory();
}
