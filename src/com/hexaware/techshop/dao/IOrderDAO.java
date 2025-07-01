package com.hexaware.techshop.dao;

import java.util.List;

import com.hexaware.techshop.entity.Order;
import com.hexaware.techshop.exception.InvalidDataException;

public interface IOrderDAO {
	
	public boolean insertOrder(Order order);
	public boolean updateOrderStatus(int orderId, String newStatus);
	public boolean deleteOrder(int orderId);
	List<Order> getOrderByCustomerId(int customerId) throws InvalidDataException;
	public Order getOrderById(int orderId) throws InvalidDataException;
	List<Order> getAllOrders();
	public boolean updateOrderTotalAmount(int orderId, double totalAmount); 
}
