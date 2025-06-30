package com.hexaware.techshop.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.hexaware.techshop.entity.Order;
import com.hexaware.techshop.entity.OrderDetail;
import com.hexaware.techshop.entity.Product;
import com.hexaware.techshop.exception.InvalidDataException;
import com.hexaware.techshop.util.DBConnection;

public class OrderDetailDAOImpl implements IOrderDetailDAO{

	Connection con=DBConnection.getConnection();	
	PreparedStatement pstmt;
	Statement stmt;
	ResultSet rs;
	
	@Override
	public void insertOrderDetail(OrderDetail item) {
		// TODO Auto-generated method stub
		
		String query="Insert into OrderDetails values(?,?,?,?)";
		
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, item.getOrderDetailId());
			pstmt.setInt(2, item.getOrder().getOrderId());
			pstmt.setInt(3, item.getProduct().getProductId());
			pstmt.setInt(4, item.getQuantity());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			DBConnection.closePreparedStatement(pstmt);
			DBConnection.closeConnection();
		}
	}

	@Override
	public void updateQuantity(int orderDetailId, int newQuantity) {
		// TODO Auto-generated method stub
		
		String query="Update OrderDetails set Quantity=? where OrderDetailId=?";
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, newQuantity);
			pstmt.setInt(2, orderDetailId);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			DBConnection.closePreparedStatement(pstmt);
			DBConnection.closeConnection();
		}
		
	}

	@Override
	public void deleteOrderDetail(int orderDetailId) {
		// TODO Auto-generated method stub
		
		String query="Delete from OrderDetails where OrderDetailId=?";
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, orderDetailId);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			DBConnection.closePreparedStatement(pstmt);
			DBConnection.closeConnection();
		}
		
	}

	@Override
	public List<OrderDetail> getOrderDetailByOrderId(int orderId) throws InvalidDataException {
		// TODO Auto-generated method stub
		
		List<OrderDetail> list=new ArrayList<>();
		String query="Select OrderDetailId,OrderId,ProductId,Quantity from OrderDetails where OrderDetailId=?";
	
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, orderId);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Product product=new Product(rs.getInt("ProductId"),"","",0);
				Order order= new Order(orderId,null,null,0,"");
				OrderDetail item=new OrderDetail(rs.getInt("OrderDetailId"),order,product,rs.getInt("Quantity"));
				list.add(item);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally {
			DBConnection.closeResultSet(rs);
			DBConnection.closePreparedStatement(pstmt);
			DBConnection.closeConnection();
		}
		return list;
	}

}
