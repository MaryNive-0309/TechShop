package com.hexaware.techshop.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.hexaware.techshop.entity.Product;
import com.hexaware.techshop.exception.InvalidDataException;
import com.hexaware.techshop.util.DBConnection;

public class ProductDAOImpl implements IProductDAO{

	Connection con=DBConnection.getConnection();	
	PreparedStatement pstmt;
	Statement stmt;
	ResultSet rs;
	
	@Override
	public void insertProduct(Product product) {
		// TODO Auto-generated method stub
		
		String query="Insert into Products values(?,?,?,?)";		
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, product.getProductId());
			pstmt.setString(2, product.getProductName());
			pstmt.setString(3, product.getDescription());
			pstmt.setDouble(4, product.getPrice());
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
	public void updateProduct(Product product) {
		// TODO Auto-generated method stub
		
		String query="Update Products set Description=?, Price=? where ProductId=? ";
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, product.getDescription());
			pstmt.setDouble(2, product.getPrice());
			pstmt.setInt(3, product.getProductId());
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
	public void deleteProduct(int productId) {
		// TODO Auto-generated method stub
		
		String query="Delete from Products where ProductId=?";
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, productId);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally {
			DBConnection.closePreparedStatement(pstmt);
			DBConnection.closeConnection();			}
		
	}

	@Override
	public List<Product> getAllProduct() throws InvalidDataException {
		// TODO Auto-generated method stub
		
		List<Product> list=new ArrayList<>();
		
		String query="Select ProductId,ProductName,Description,Price";
		try {
			stmt=con.createStatement();
			rs=stmt.executeQuery(query);
			while(rs.next()) {
				Product p=new Product();
				p.setProductId(rs.getInt("ProductId"));
				p.setProductName(rs.getString("ProductName"));
				p.setDescription(rs.getString("Description"));
				p.setPrice(rs.getDouble("Price"));
				list.add(p);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally {
			DBConnection.closeStatement(stmt);	
			DBConnection.closeResultSet(rs);
			DBConnection.closeConnection();			
		}
		return list;
	}

	@Override
	public Product getProductById(int productId) throws InvalidDataException {
		// TODO Auto-generated method stub
		
		String query="Select ProductId,ProductName,Description,Price";
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, productId);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				Product p=new Product(productId, rs.getString("ProductName"),
						rs.getString("Description"),rs.getDouble("Price"));
				return p;							
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
		return null;
	}

}
