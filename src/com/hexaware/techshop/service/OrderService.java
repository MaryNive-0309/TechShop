package com.hexaware.techshop.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import com.hexaware.techshop.dao.IOrderDAO;
import com.hexaware.techshop.dao.OrderDAOImpl;
import com.hexaware.techshop.entity.Order;
import com.hexaware.techshop.exception.IncompleteOrderException;
import com.hexaware.techshop.exception.InvalidDataException;

public class OrderService {

	IOrderDAO orderDao=new OrderDAOImpl();
	List<Order> order=new ArrayList<>();
	
	public void addOrder(Order o) throws IncompleteOrderException, InvalidDataException {
		if(o.getOrderId() == 0 || o.getCustomer()==null|| o.getOrderDate()==null) {
			throw new IncompleteOrderException("Order is incomplete.Enter required fields");
		}
		for(Order orders:order) {
			if(o.getOrderId()==orders.getOrderId()) {
				throw new InvalidDataException("Duplicate OrderId found: "+o.getOrderId());
			}
		}
		orderDao.insertOrder(o);
	}
	
	public void updateOrderStatus(int orderId,String orderStatus) throws InvalidDataException {
		List<Order> order= orderDao.getAllOrders();
		boolean found= false;
		for(Order o:order) {
			if(o.getOrderId()==orderId) {
				found = true;
				break;
			}
		}
		if(!found) {
			throw new InvalidDataException("OrderID not found: "+orderId);
		}
		orderDao.updateOrderStatus(orderId, orderStatus);
	}
	
	
	public void cancelOrder(int orderId) throws IncompleteOrderException {
		List<Order> order=orderDao.getAllOrders();
		boolean found=false;
		for(Order o:order) {
			if(o.getOrderId()==orderId) {
				found = true;
				break;
			}
		}
		if(!found) {
			throw new IncompleteOrderException("OrderID not found: "+orderId);
		}
		orderDao.deleteOrder(orderId);
	}
	
	public List<Order> getAllOrders(){
		return orderDao.getAllOrders();
	}
	
	public List<Order> sortOrdersByDate(boolean sortedList){
		List<Order> orderSort=orderDao.getAllOrders();
		orderSort.sort(new Comparator<Order>() {
			public int compare(Order o1,Order o2) {
				return o1.getOrderDate().compareTo(o2.getOrderDate());
			}
		});
		if(!sortedList) {
			Collections.reverse(orderSort);
		}
		return orderSort;
	}
	
	
}
