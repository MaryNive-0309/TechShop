package com.hexaware.techshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hexaware.techshop.entity.Product;
import com.hexaware.techshop.exception.InvalidDataException;
import com.hexaware.techshop.util.DBConnection;

public class ProductDAOImpl implements IProductDAO {

	Connection con = DBConnection.getConnection();

	@Override
	public boolean insertProduct(Product product) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String query = "Insert into Products(ProductName,Description,Price,Category) values(?,?,?,?)";
		try {
			pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, product.getProductName());
			pstmt.setString(2, product.getDescription());
			pstmt.setDouble(3, product.getPrice());
			pstmt.setString(4, product.getCategory());
			int rows = pstmt.executeUpdate();
			if (rows > 0) {
				rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					int generatedId = rs.getInt(1);
					product.setProductId(generatedId);
				}
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in inserting products: " + e.getMessage());
		} finally {
			DBConnection.closeResultSet(rs);
			DBConnection.closePreparedStatement(pstmt);
		}
		return false;
	}

	@Override
	public boolean updateProduct(Product product) {
		// TODO Auto-generated method stub

		PreparedStatement pstmt = null;
		String query = "Update Products set Description=?, Price=?, Category=? where ProductId=? ";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, product.getDescription());
			pstmt.setDouble(2, product.getPrice());
			pstmt.setString(3, product.getCategory());
			pstmt.setInt(4, product.getProductId());
			int rows = pstmt.executeUpdate();
			return rows > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in updating products: " + e.getMessage());
		} finally {
			DBConnection.closePreparedStatement(pstmt);
		}
		return false;
	}

	@Override
	public boolean deleteProduct(int productId) {
		// TODO Auto-generated method stub

		PreparedStatement pstmt = null;

		String query = "Delete from Products where ProductId=?";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, productId);
			int rows = pstmt.executeUpdate();
			return rows > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in deleting products: " + e.getMessage());
		} finally {
			DBConnection.closePreparedStatement(pstmt);
		}
		return false;

	}

	@Override
	public List<Product> getAllProduct() throws InvalidDataException {
		// TODO Auto-generated method stub

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<Product> list = new ArrayList<>();

		String query = "Select ProductId,ProductName,Description,Price,Category from Products";
		try {
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Product p = new Product();
				p.setProductId(rs.getInt("ProductId"));
				p.setProductName(rs.getString("ProductName"));
				p.setDescription(rs.getString("Description"));
				p.setPrice(rs.getDouble("Price"));
				p.setCategory(rs.getString("Category"));
				list.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in retrieving products " + e.getMessage());
		} finally {
			DBConnection.closeResultSet(rs);
			DBConnection.closePreparedStatement(pstmt);
		}
		return list;
	}

	@Override
	public Product getProductById(int productId) throws InvalidDataException {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String query = "Select ProductId,ProductName,Description,Price,Category from Products where ProductId=?";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, productId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				Product p = new Product(rs.getString("ProductName"), rs.getString("Description"), rs.getDouble("Price"),
						rs.getString("Category"));
				p.setProductId(rs.getInt("ProductId"));
				return p;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in fetching products " + e.getMessage());
		} finally {
			DBConnection.closeResultSet(rs);
			DBConnection.closePreparedStatement(pstmt);
		}
		return null;
	}

	@Override
	public List<Product> searchProductByName(String name) throws InvalidDataException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Product> products = new ArrayList<>();

		String query = "Select ProductId,ProductName,Description,Price,Category from Products where ProductName LIKE ?";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "%" + name + "%");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				Product product = new Product();
				product.setProductId(rs.getInt("ProductId"));
				product.setProductName(rs.getString("ProductName"));
				product.setDescription(rs.getString("Description"));
				product.setPrice(rs.getDouble("Price"));
				product.setCategory(rs.getString("Category"));
				products.add(product);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in searching products" + e.getMessage());
		} finally {
			DBConnection.closeResultSet(rs);
			DBConnection.closePreparedStatement(pstmt);
		}
		return products;
	}

}
