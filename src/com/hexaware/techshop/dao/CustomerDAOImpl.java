package com.hexaware.techshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hexaware.techshop.entity.Customer;
import com.hexaware.techshop.exception.InvalidDataException;
import com.hexaware.techshop.util.DBConnection;

public class CustomerDAOImpl implements ICustomerDAO{
		
	Connection con=DBConnection.getConnection();
	
	@Override
	public void insertCustomer(Customer customer) {
		// TODO Auto-generated method stub
		
		PreparedStatement pstmt =null ;
		
		String query="INSERT INTO Customers (FirstName,LastName,Email,Phone,Address) VALUES(?,?,?,?,?)";
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, customer.getFirstName());
			pstmt.setString(2, customer.getLastName());
			pstmt.setString(3, customer.getEmail());
			pstmt.setString(4, customer.getPhone());
			pstmt.setString(5, customer.getAddress());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error inserting customer");
		}
		finally {
			DBConnection.closePreparedStatement(pstmt);
		}
	}

	@Override
	public void updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		
		PreparedStatement pstmt =null ;
		
		String query="UPDATE Customers SET Email=?, Phone=?, Address=? WHERE CustomerId=?";
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, customer.getEmail());
			pstmt.setString(2, customer.getPhone());
			pstmt.setString(3, customer.getAddress());
			pstmt.setInt(4, customer.getCustomerId());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error updating customer");
		}
		finally {
			DBConnection.closePreparedStatement(pstmt);		}
	}

	@Override
	public void deleteCustomer(int customerId) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt =null ;
		
		String query="DELETE FROM Customers WHERE CustomerId=?";
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, customerId);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error deleting customer");
		}
		finally {
			DBConnection.closePreparedStatement(pstmt);
		}
	}

	@Override
	public List<Customer> getAllCustomer() throws InvalidDataException {
		// TODO Auto-generated method stub
		Statement stmt =null ;
		ResultSet rs =null;
		
		List<Customer> list=new ArrayList<>();
		
		String query="SELECT CustomerId,FirstName,LastName,Email,Phone,Address FROM Customers";
		try {
			stmt = con.createStatement();
			rs=stmt.executeQuery(query);
			while(rs.next()) {
				Customer c=new Customer();
				c.setCustomerId(rs.getInt("CustomerId"));
				c.setFirstName(rs.getString("FirstName"));
				c.setLastName(rs.getString("LastName"));
				c.setEmail(rs.getString("Email"));
				c.setPhone(rs.getString("Phone"));
				c.setAddress(rs.getString("Address"));
				list.add(c);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in retrieving customer");
		}
		finally {
			DBConnection.closeStatement(stmt);
			DBConnection.closeResultSet(rs);
		}
		
		return list;
	}

	@Override
	public Customer getCustomerById(int customerId) throws InvalidDataException {
		// TODO Auto-generated method stub
		PreparedStatement pstmt =null ;
		ResultSet rs =null;
		
		String query="SELECT CustomerId,FirstName,LastName,Email,Phone,Address FROM Customers WHERE CustomerId=?";
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, customerId);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				Customer c=new Customer(rs.getString("FirstName"), rs.getString("LastName"),
						rs.getString("Email"),rs.getString("Phone"),rs.getString("Address"));
				return c;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in retreieving customer");
		}
		
		finally {
			DBConnection.closeResultSet(rs);
			DBConnection.closePreparedStatement(pstmt);
		}
		return null;
		
	}
	
	
}
