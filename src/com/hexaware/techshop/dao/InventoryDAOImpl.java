package com.hexaware.techshop.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.hexaware.techshop.entity.Inventory;
import com.hexaware.techshop.entity.Product;
import com.hexaware.techshop.exception.InvalidDataException;
import com.hexaware.techshop.util.DBConnection;

public class InventoryDAOImpl implements IInventoryDAO{
	
	Connection con=DBConnection.getConnection();	
	PreparedStatement pstmt;
	Statement stmt;
	ResultSet rs;

	@Override
	public void insertInventory(Inventory inventory) {
		// TODO Auto-generated method stub
		
		String query="Insert into Inventory values(?,?,?,?)";
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, inventory.getInventoryId());
			pstmt.setInt(2, inventory.getProduct().getProductId());
			pstmt.setInt(3, inventory.getQuantityInStock());
			pstmt.setDate(4, new Date(inventory.getLastStockUpdate().getTime()));
			
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
	public void updateStock(int productId, int newQuantity) {
		// TODO Auto-generated method stub
		
		String query="Update Inventory set QuantityInStock=?, LastStockUpdate=NOW() where ProductId=?";
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, newQuantity);
			pstmt.setInt(2, productId);
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
	public void deleteInventoryByProductId(int productId) {
		// TODO Auto-generated method stub
		
		String query="Delete from Inventory where ProductId=?";
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
			DBConnection.closeConnection();
		}
	}

	@Override
	public Inventory getInventoryByProductId(int productId) throws InvalidDataException {
		// TODO Auto-generated method stub
		
		String query="Select InventoryId,ProductId,QuantityInStock,LastStockUpdate from Inventory where ProductId=?";
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, productId);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				Product product=new Product(productId,"","",0);
				Inventory inv= new Inventory();
				inv.setInventoryId(rs.getInt("InventoryId"));
				inv.setProduct(product);
				inv.setQuantityInStock(rs.getInt("QuantityInStock"));
				inv.setLastStockUpdate(rs.getDate("LastStockUpdate"));
				return inv;

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
