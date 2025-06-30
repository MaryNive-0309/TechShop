package com.hexaware.techshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hexaware.techshop.entity.Order;
import com.hexaware.techshop.entity.Payment;
import com.hexaware.techshop.util.DBConnection;

public class PaymentDAOImpl implements IPaymentDAO{

	Connection con=DBConnection.getConnection();
	@Override
	public void insertPayment(Payment payment) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt=null;
		
		String query="INSERT INTO Payments VALUES(?,?,?,?,?)";
		
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, payment.getPaymentId());
			pstmt.setInt(2, payment.getOrder().getOrderId());
			pstmt.setString(3, payment.getMethod());
			pstmt.setDouble(4, payment.getAmount());
			pstmt.setString(5, payment.getPaymentStatus());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Payment> getPaymentByOrderId(int orderId) {
		// TODO Auto-generated method stub
		
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		List<Payment> list=new ArrayList<>();
		String query="SELECT PaymentId,OrderId,Method,Amount,PaymentStatus FROM Payments WHERE OrderId=?";
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, orderId);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Order order=new Order();
				order.setOrderId(orderId);
				Payment payment=new Payment(rs.getInt("PaymentId"),order,rs.getString("Method"),
						rs.getDouble("Amount"),rs.getString("PaymentStatus"));
				list.add(payment);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in retrieving payment details");
		}
		
		finally {
			DBConnection.closeResultSet(rs);
			DBConnection.closePreparedStatement(pstmt);
		}
		return list;
	}

	@Override
	public List<Payment> getAllPayments() {
		// TODO Auto-generated method stub
		
		Statement stmt=null;
		ResultSet rs=null;

		List<Payment> list=new ArrayList<>();
		
		String query="SELECT PaymentId,OrderId,Method,Amount,PaymentStatus FROM Payments";
		try {
			stmt=con.createStatement();
			rs=stmt.executeQuery(query);
			while(rs.next()) {
				Order order=new Order();
				order.setOrderId(rs.getInt("OrderId"));
				Payment payment=new Payment(rs.getInt("PaymentId"),order,rs.getString("Method"),
						rs.getDouble("Amount"),rs.getString("PaymentStatus"));
				list.add(payment);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in retrieving payment details");
		}
		
		finally {
			DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(stmt);
		}
		return list;
	}


}
