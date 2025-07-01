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
import com.hexaware.techshop.entity.Inventory;
import com.hexaware.techshop.entity.Order;
import com.hexaware.techshop.entity.Product;
import com.hexaware.techshop.exception.InvalidDataException;
import com.hexaware.techshop.util.DBConnection;

public class InventoryDAOImpl implements IInventoryDAO {

	Connection con = DBConnection.getConnection();

	@Override
	public boolean insertInventory(Inventory inventory) {
		// TODO Auto-generated method stub

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "Insert into Inventory(ProductId,QuantityInStock,LastStockUpdate) values(?,?,?)";
		try {
			pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, inventory.getProduct().getProductId());
			pstmt.setInt(2, inventory.getQuantityInStock());
			pstmt.setDate(3, Date.valueOf(inventory.getLastStockUpdate()));
			int rows = pstmt.executeUpdate();
			if (rows > 0) {
				rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					int generatedId = rs.getInt(1);
					inventory.setInventoryId(generatedId);
				}
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Error inserting in inventory: " + e.getMessage());
		} finally {
			DBConnection.closeResultSet(rs);
			DBConnection.closePreparedStatement(pstmt);
		}
		return false;
	}

	@Override
	public boolean updateStock(int productId, int newQuantity) {
		// TODO Auto-generated method stub

		PreparedStatement pstmt = null;
		String query = "Update Inventory set QuantityInStock=?, LastStockUpdate=NOW() where ProductId=?";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, newQuantity);
			pstmt.setInt(2, productId);
			int rows = pstmt.executeUpdate();
			return rows > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in updating: " + e.getMessage());
		} finally {
			DBConnection.closePreparedStatement(pstmt);
		}
		return false;
	}

	@Override
	public boolean deleteInventoryByProductId(int productId) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;

		String query = "Delete from Inventory where ProductId=?";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, productId);
			int rows = pstmt.executeUpdate();
			return rows > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in deleting inventory: " + e.getMessage());
		} finally {
			DBConnection.closePreparedStatement(pstmt);
		}
		return false;
	}

	@Override
	public Inventory getInventoryByProductId(int productId) throws InvalidDataException {
		// TODO Auto-generated method stub

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String query = "Select InventoryId,ProductId,QuantityInStock,LastStockUpdate from Inventory where ProductId=?";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, productId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				Product product = new Product();
				product.setProductId(rs.getInt("ProductId"));
				Inventory inv = new Inventory();
				inv.setInventoryId(rs.getInt("InventoryId"));
				inv.setProduct(product);
				inv.setQuantityInStock(rs.getInt("QuantityInStock"));
				inv.setLastStockUpdate(rs.getDate("LastStockUpdate").toLocalDate());
				return inv;
			}
		} catch (SQLException e) {
			System.out.println("Error in retrieving product from inventory: " + e.getMessage());
		} finally {
			DBConnection.closeResultSet(rs);
			DBConnection.closePreparedStatement(pstmt);
		}
		return null;
	}

	@Override
	public List<Inventory> getAllInventory() {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<Inventory> list = new ArrayList<>();
		String query = "Select InventoryId,ProductId,QuantityInStock,LastStockUpdate from Inventory";
		try {
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setProductId(rs.getInt("ProductId"));
				Inventory inv = new Inventory();
				inv.setInventoryId(rs.getInt("InventoryId"));
				inv.setProduct(product);
				inv.setQuantityInStock(rs.getInt("QuantityInStock"));
				inv.setLastStockUpdate(rs.getDate("LastStockUpdate").toLocalDate());
				list.add(inv);
			}
		} catch (SQLException e) {
			System.out.println("Error in fetching products from inventory: " + e.getMessage());
		} finally {
			DBConnection.closeResultSet(rs);
			DBConnection.closePreparedStatement(pstmt);
		}
		return list;
	}
}
