package com.hexaware.techshop.dao;

import java.util.List;

import com.hexaware.techshop.entity.OrderDetail;
import com.hexaware.techshop.exception.InvalidDataException;

public interface IOrderDetailDAO {

	public boolean insertOrderDetail(OrderDetail item);

	public boolean updateQuantity(int orderDetailId, int newQuantity);

	public boolean deleteOrderDetail(int orderDetailId);

	public List<OrderDetail> getOrderDetailByOrderId(int orderId) throws InvalidDataException;

}
