package com.tap.servlets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * Servlet implementation class DAOUserServelet
 */
@WebServlet("/DAOUserServelet")
public class DAOUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DAOUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    // JDBC Database credentials
    private static final String URL = "jdbc:mysql://localhost:3306/JeeRegistration123";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "mahantesh";
    
    
    //Loading the driver
    void loadDriver() throws ClassNotFoundException {
    	Class.forName("com.mysql.cj.jdbc.Driver");
    }
    
    // Establish database connection
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    } 
}