package com.hexaware.techshop.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hexaware.techshop.entity.Customer;
import com.hexaware.techshop.entity.Order;
import com.hexaware.techshop.exception.InvalidDataException;
import com.hexaware.techshop.util.DBConnection;

public class OrderDAOImpl implements IOrderDAO{

	Connection con=DBConnection.getConnection();	
	PreparedStatement pstmt;
	Statement stmt;
	ResultSet rs;
	
	@Override
	public void insertOrder(Order order) {
		// TODO Auto-generated method stub
		
		String query="Insert into Orders values(?,?,?,?,?)";
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, order.getOrderId());
			pstmt.setInt(2, order.getCustomer().getCustomerId());
			pstmt.setDate(3, new Date(order.getOrderDate().getTime()));
			pstmt.setDouble(4, order.getTotalAmount());
			pstmt.setString(5, order.getOrderStatus());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally {
			DBConnection.closePreparedStatement(pstmt);
		}
		
	}

	@Override
	public void updateOrderStatus(int orderId, String newStatus) {
		// TODO Auto-generated method stub
		
		String query="Update Orders set Status=? where OrderId=?";
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, newStatus);
			pstmt.setInt(2, orderId);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			DBConnection.closePreparedStatement(pstmt);
		}
	}

	@Override
	public void deleteOrder(int orderId) {
		// TODO Auto-generated method stub
		
		String query="Delete from Orders where OrderId=?";
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, orderId);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			DBConnection.closePreparedStatement(pstmt);
		}
		
	}

	@Override
	public List<Order> getOrderByCustomerId(int customerId) throws InvalidDataException {
		// TODO Auto-generated method stub
		
		List<Order> list=new ArrayList<>();
		
		String query="Select OrderId,CustomerId,OrderDate,TotalAmount,Status where CustomerId=?";
		
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, customerId);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Customer c=new Customer("","","","","");
				Order o=new Order();
				o.setOrderId(rs.getInt("OrderId"));
				o.setCustomer(c);
				o.setOrderDate(rs.getDate("OrderDate"));
				o.setTotalAmount(rs.getDouble("TotalAmount"));
				o.setOrderStatus(rs.getString("Status"));
				list.add(o);				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			DBConnection.closeResultSet(rs);
			DBConnection.closePreparedStatement(pstmt);
		}
		
		return list;
	}

	@Override
	public Order getOrderById(int orderId) throws InvalidDataException {
		// TODO Auto-generated method stub
		
		String query="Select OrderId,CustomerId,OrderDate,TotalAmount,Status where CustomerId=?";
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, orderId);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Customer c=new Customer("","","","","");
				Order o=new Order(orderId,c,rs.getDate("OrderDate"),rs.getDouble("TotalAmount"),rs.getString("Status"));
				return o;				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		finally {
			DBConnection.closeResultSet(rs);
			DBConnection.closePreparedStatement(pstmt);	
		}
		
		return null;
	}

	@Override
	public List<Order> getAllOrders() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
