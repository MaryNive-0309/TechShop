package com.hexaware.techshop.util;

import java.sql.*;

public class DBConnection {

private static Connection con;
	
	public static Connection getConnection() {
		
		try {
			if(con==null) {
				String str=PropertyUtil.getPropertyString("db.properties");
				con=DriverManager.getConnection(str);			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return con;
		
	}
	
	public static void closeConnection() {
		try {
			if(con!=null)
				con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void closeStatement(Statement stmt) {
		try {
			if(stmt!=null)
				stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void closePreparedStatement(PreparedStatement pstmt) {
		try {
			if(pstmt!=null)
				pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void closeResultSet(ResultSet rs) {
		try {
			if(rs!=null) 
				rs.close();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
