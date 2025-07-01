package com.hexaware.techshop.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {

	public static String getPropertyString(String fileName) {

		Properties property = new Properties();

		try (InputStream input = PropertyUtil.class.getClassLoader().getResourceAsStream("db.properties")) {
			if (input == null) {
				System.out.println("Property file not found");
				return null;
			}
			property.load(input);
			String host = property.getProperty("hostname");
			String port = property.getProperty("port");
			String dbname = property.getProperty("dbname");
			String user = property.getProperty("username");
			String password = property.getProperty("password");
			return "jdbc:mysql://" + host + ":" + port + "/" + dbname + "?user=" + user + "&password=" + password;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error reading the file " + e.getMessage());
		}
		return null;
	}
}
