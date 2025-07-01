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
import com.hexaware.techshop.exception.PaymentNotFoundException;
import com.hexaware.techshop.util.DBConnection;

public class PaymentDAOImpl implements IPaymentDAO {

	Connection con = DBConnection.getConnection();

	@Override
	public boolean insertPayment(Payment payment) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String query = "INSERT INTO Payments(OrderId,Method,Amount,PaymentStatus) VALUES(?,?,?,?)";
		try {
			pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, payment.getOrder().getOrderId());
			pstmt.setString(2, payment.getMethod());
			pstmt.setDouble(3, payment.getAmount());
			pstmt.setString(4, payment.getPaymentStatus());
			int rows = pstmt.executeUpdate();
			if (rows > 0) {
				rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					int generatedId = rs.getInt(1);
					payment.setPaymentId(generatedId);
				}
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in making payments " + e.getMessage());
		} finally {
			DBConnection.closeResultSet(rs);
			DBConnection.closePreparedStatement(pstmt);
		}
		return false;
	}

	@Override
	public List<Payment> getPaymentByOrderId(int orderId) throws PaymentNotFoundException {
		// TODO Auto-generated method stub

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<Payment> list = new ArrayList<>();
		String query = "SELECT PaymentId,OrderId,Method,Amount,PaymentStatus FROM Payments WHERE OrderId=?";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, orderId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Order order = new Order();
				order.setOrderId(orderId);
				Payment payment = new Payment(order, rs.getString("Method"), rs.getDouble("Amount"),
						rs.getString("PaymentStatus"));
				payment.setPaymentId(rs.getInt("PaymentId"));
				list.add(payment);
			}
			if (list.isEmpty()) {
				throw new PaymentNotFoundException("No payment found for OrderId " + orderId);
			}
		} catch (SQLException e) {
			System.out.println("Error in retrieving payment details " + e.getMessage());
		} finally {
			DBConnection.closeResultSet(rs);
			DBConnection.closePreparedStatement(pstmt);
		}
		return list;
	}

	@Override
	public List<Payment> getAllPayments() {
		// TODO Auto-generated method stub

		Statement stmt = null;
		ResultSet rs = null;

		List<Payment> list = new ArrayList<>();

		String query = "SELECT PaymentId,OrderId,Method,Amount,PaymentStatus FROM Payments";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				Order order = new Order();
				order.setOrderId(rs.getInt("OrderId"));
				Payment payment = new Payment(order, rs.getString("Method"), rs.getDouble("Amount"),
						rs.getString("PaymentStatus"));
				payment.setPaymentId(rs.getInt("PaymentId"));
				list.add(payment);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in retrieving payment details " + e.getMessage());
		}

		finally {
			DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(stmt);
		}
		return list;
	}

}
