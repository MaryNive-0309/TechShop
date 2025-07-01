package com.hexaware.techshop.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.hexaware.techshop.entity.Order;
import com.hexaware.techshop.entity.OrderDetail;
import com.hexaware.techshop.entity.Product;
import com.hexaware.techshop.exception.InvalidDataException;
import com.hexaware.techshop.util.DBConnection;

public class OrderDetailDAOImpl implements IOrderDetailDAO {

	Connection con = DBConnection.getConnection();

	@Override
	public boolean insertOrderDetail(OrderDetail item) {
		// TODO Auto-generated method stub

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "Insert into OrderDetails (OrderId,ProductId,Quantity) values(?,?,?)";
		try {
			pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, item.getOrder().getOrderId());
			pstmt.setInt(2, item.getProduct().getProductId());
			pstmt.setInt(3, item.getQuantity());
			int rows = pstmt.executeUpdate();
			if (rows > 0) {
				rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					int generatedId = rs.getInt(1);
					item.setOrderDetailId(generatedId);
				}
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in inserting " + e.getMessage());
		} finally {
			DBConnection.closeResultSet(rs);
			DBConnection.closePreparedStatement(pstmt);
		}
		return false;
	}

	@Override
	public boolean updateQuantity(int orderDetailId, int newQuantity) {
		// TODO Auto-generated method stub

		PreparedStatement pstmt = null;
		String query = "Update OrderDetails set Quantity=? where OrderDetailId=?";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, newQuantity);
			pstmt.setInt(2, orderDetailId);
			int rows = pstmt.executeUpdate();
			return rows > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in updating order quantity " + e.getMessage());
		} finally {
			DBConnection.closePreparedStatement(pstmt);
		}
		return false;
	}

	@Override
	public boolean deleteOrderDetail(int orderDetailId) {
		// TODO Auto-generated method stub

		PreparedStatement pstmt = null;

		String query = "Delete from OrderDetails where OrderDetailId=?";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, orderDetailId);
			int rows = pstmt.executeUpdate();
			return rows > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in deleting order detail " + e.getMessage());
		} finally {
			DBConnection.closePreparedStatement(pstmt);
		}
		return false;

	}

	@Override
	public List<OrderDetail> getOrderDetailByOrderId(int orderId) throws InvalidDataException {
		// TODO Auto-generated method stub

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<OrderDetail> list = new ArrayList<>();
		String query = "Select OrderDetailId,OrderId,ProductId,Quantity from OrderDetails where OrderId=?";

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, orderId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setProductId(rs.getInt("ProductId"));
				Order order = new Order();
				order.setOrderId(rs.getInt("OrderId"));
				OrderDetail item = new OrderDetail(order, product, rs.getInt("Quantity"));
				item.setOrderDetailId(rs.getInt("OrderDetailId"));
				list.add(item);
			}
		} catch (SQLException e) {
			System.out.println("Error in retrieving orderdetails " + e.getMessage());
		} finally {
			DBConnection.closeResultSet(rs);
			DBConnection.closePreparedStatement(pstmt);
		}
		return list;
	}

}
