package com.hexaware.techshop.dao;

import java.util.List;

import com.hexaware.techshop.entity.Order;
import com.hexaware.techshop.exception.InvalidDataException;

public interface IOrderDAO {
	
	public void insertOrder(Order order);
	public void updateOrderStatus(int orderId, String newStatus);
	public void deleteOrder(int orderId);
	List<Order> getOrderByCustomerId(int customerId) throws InvalidDataException;
	public Order getOrderById(int orderId) throws InvalidDataException;
	List<Order> getAllOrders();
}
