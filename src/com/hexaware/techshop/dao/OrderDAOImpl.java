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
	
	@Override
	public boolean insertOrder(Order order) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt =null ;
		ResultSet rs =null;		
		String query="Insert into Orders(CustomerId,OrderDate,TotalAmount,Status) values(?,?,?,?)";
		try {
			pstmt=con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, order.getCustomer().getCustomerId());
            pstmt.setDate(2, Date.valueOf(order.getOrderDate()));
			pstmt.setDouble(3, order.getTotalAmount());
			pstmt.setString(4, order.getOrderStatus());
			int rows = pstmt.executeUpdate();
            if (rows > 0) {
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    int generatedId = rs.getInt(1);
                    order.setOrderId(generatedId);
                }
                return true;
            }					
		} catch (SQLException e) {
			System.out.println("Error in inserting orders "+e.getMessage());
		}		
		finally {
			DBConnection.closePreparedStatement(pstmt);
		}
		return false;
			
	}

	@Override
	public boolean updateOrderStatus(int orderId, String newStatus) {
		// TODO Auto-generated method stub
		
		PreparedStatement pstmt =null ;		
		String query="Update Orders set Status=? where OrderId=?";
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, newStatus);
			pstmt.setInt(2, orderId);
			int rows=pstmt.executeUpdate();
			return rows>0;			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in updating order status "+e.getMessage());
		}
		finally {
			DBConnection.closePreparedStatement(pstmt);
		}
		return false;
	}

	@Override
	public boolean deleteOrder(int orderId) {
		// TODO Auto-generated method stub
		
		PreparedStatement pstmt =null ;	
		String query="Delete from Orders where OrderId=?";
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, orderId);
			int rows=pstmt.executeUpdate();
			return rows>0;			
		} catch (SQLException e) {
			System.out.println("Error in deleting orders "+e.getMessage());
		}
		finally {
			DBConnection.closePreparedStatement(pstmt);
		}
		return false;
		
	}

	@Override
	public List<Order> getOrderByCustomerId(int customerId) throws InvalidDataException {
		// TODO Auto-generated method stub

		PreparedStatement pstmt = null ;		
		ResultSet rs = null;
		List<Order> list=new ArrayList<>();
		
		String query="Select OrderId,CustomerId,OrderDate,TotalAmount,Status from Orders where CustomerId=?";		
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, customerId);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Customer c=new Customer();
				c.setCustomerId(rs.getInt("CustomerId"));
				Order o=new Order();
				o.setOrderId(rs.getInt("OrderId"));
				o.setCustomer(c);
				o.setOrderDate(rs.getDate("OrderDate").toLocalDate());
				o.setTotalAmount(rs.getDouble("TotalAmount"));
				o.setOrderStatus(rs.getString("Status"));
				list.add(o);				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in fetching orders "+e.getMessage());
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
		
		PreparedStatement pstmt = null ;		
		ResultSet rs = null;
		
		String query="Select OrderId,CustomerId,OrderDate,TotalAmount,Status from Orders where OrderId=?";
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, orderId);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Customer c=new Customer();
				Order od=new Order(c,
						rs.getDate("OrderDate").toLocalDate(),
						rs.getDouble("TotalAmount"),
						rs.getString("Status"));
				od.setOrderId(rs.getInt("OrderId"));
				return od;				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in retrieving orders "+e.getMessage());
		}		
		
		finally {
			DBConnection.closeResultSet(rs);
			DBConnection.closePreparedStatement(pstmt);	
		}		
		return null;
	}

	@Override
	public List<Order> getAllOrders() {
	    List<Order> list = new ArrayList<>();
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    String query = "Select OrderId,CustomerId,OrderDate,TotalAmount,Status from Orders";
	    try {
	        pstmt = con.prepareStatement(query);
	        rs = pstmt.executeQuery();
	        while (rs.next()) {
	            Customer customer = new Customer();
	            customer.setCustomerId(rs.getInt("CustomerId"));
	            Order o = new Order();
	            o.setOrderId(rs.getInt("OrderId"));
	            o.setCustomer(customer);
	            o.setOrderDate(rs.getDate("OrderDate").toLocalDate());
	            o.setTotalAmount(rs.getDouble("TotalAmount"));
	            o.setOrderStatus(rs.getString("Status"));
	            list.add(o);
	        }
	    } catch (SQLException e) {
	        System.out.println("Error in fetching all orders: " + e.getMessage());
	    } finally {
	        DBConnection.closeResultSet(rs);
	        DBConnection.closePreparedStatement(pstmt);
	    }
	    return list;
	}

	public boolean updateOrderTotalAmount(int orderId, double totalAmount) {
	    PreparedStatement pstmt = null;
	    String query = "Update Orders set TotalAmount = ? where OrderId = ?";
	    try {
	        pstmt = con.prepareStatement(query);
	        pstmt.setDouble(1, totalAmount);
	        pstmt.setInt(2, orderId);
	        int rows = pstmt.executeUpdate();
	        return rows>0;	        
	    } catch (SQLException e) {
	        System.out.println("Error updating total amount: " + e.getMessage());
	    } 
	    finally {
	        DBConnection.closePreparedStatement(pstmt);
	    }
	    return false;
	}

	
}
