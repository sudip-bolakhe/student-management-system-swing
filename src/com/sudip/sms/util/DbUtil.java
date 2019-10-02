package com.sudip.sms.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
	private static  final String URL="jdbc:mysql://localhost:3307/";
	private static  final String databaseNAME="user";
	private static  final String DRIVER="com.mysql.jdbc.Driver";
	private static  final String USERNAME="root";
	private static  final String PASSWORD="root";
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException{
		Class.forName(DRIVER);
		
		Connection con=DriverManager.getConnection(URL+databaseNAME,USERNAME,PASSWORD);
		return con;
	
	}
	
	
}
