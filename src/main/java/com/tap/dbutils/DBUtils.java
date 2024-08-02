package com.tap.dbutils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


final public class DBUtils {
	private static final String URL = "jdbc:mysql://localhost:3306/JDBC_FEB_2024";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "mahantesh";
	
	public static Connection myConnect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(URL, USERNAME, PASSWORD);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
}
