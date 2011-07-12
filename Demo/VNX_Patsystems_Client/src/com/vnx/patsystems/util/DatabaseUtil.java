package com.vnx.patsystems.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseUtil {
	
	private  String username = "root";
	private String password = "nidnos";
	private String url = "jdbc:mysql://localhost:3306/patsystemsclient";
	private Connection connection = null;
	
	public Connection getConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(url, username, password);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return connection;
	}
	
	public void closeConnection(){
		try {
			connection.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
