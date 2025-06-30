package com.hexaware.techshop.dao;

import java.util.List;

import com.hexaware.techshop.entity.OrderDetail;
import com.hexaware.techshop.exception.InvalidDataException;

public interface IOrderDetailDAO {
	
	public void insertOrderDetail(OrderDetail item);
	public void updateQuantity(int orderDetailId, int newQuantity);
	public void deleteOrderDetail(int orderDetailId);
	public List<OrderDetail> getOrderDetailByOrderId(int orderId) throws InvalidDataException;

}
