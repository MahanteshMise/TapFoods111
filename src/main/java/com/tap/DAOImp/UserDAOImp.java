package com.tap.DAOImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.tap.DAO.UserDAO;
import com.tap.dbutils.DBUtils;
import com.tap.model.User;

public class UserDAOImp implements UserDAO{
	Connection con;
	private PreparedStatement pstmt;
	private Statement stmt;
	User user;
	ArrayList<User> userList;
	private static final String ADD_USER = "insert into tap_foods.user(`email`,`username`,`password`,`address`,`phone_number`)"
			+"values(?,?,?,?,?)";
	private static final String SELECT_ALL_USER = "select * from tap_foods.user";
	private static final String SELECT_ON_EMAIL = "select * from tap_foods.user where email=?";
	private static final String UPDATE_ON_EMAIL = "update tap_foods.user set username=?, password=?, address=?, phone_number=? where email=?";
	private static final String DELETE_ON_EMAIL = "delete from tap_foods.user where email=?";
	int status = 0;
	private ResultSet resultSet;
	public UserDAOImp() {
		try {
			con = DBUtils.myConnect();
		}catch(Exception e){
			e.printStackTrace();
			}
	}
	
	@Override
	public int addUser(User u) {
		try {
			pstmt = con.prepareStatement(ADD_USER);
			pstmt.setString(1, u.getEmail());
			pstmt.setString(2, u.getUsername());
			pstmt.setString(3, u.getPassword());
			pstmt.setString(4, u.getAddress());
			pstmt.setString(5, u.getPhone_number());
			
			status = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	@Override
	public ArrayList<User> getAllUsers() {
		
		try {
			stmt = con.createStatement();
			resultSet = stmt.executeQuery(SELECT_ALL_USER);
			userList = extractUserFromResultSet(resultSet);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return userList;
		
	}
	
	ArrayList<User> extractUserFromResultSet(ResultSet resultSet) {
		try {
			while(resultSet.next()) {
			userList.add(new User(resultSet.getInt("user_id"),
						resultSet.getString("email"),
						resultSet.getString("username"),
						resultSet.getString("password"),
						resultSet.getString("address"),
						resultSet.getString("phone_number")));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return userList;
	}

	@Override
	public User getUser(String email) {
		try {
			pstmt = con.prepareStatement(SELECT_ON_EMAIL);
			pstmt.setString(1, email);
			resultSet = pstmt.executeQuery();
			userList = extractUserFromResultSet(resultSet);
			if (!userList.isEmpty()) {
				user = userList.get(0);
			} else {
				user = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public int updateUser(User u) {
		try {
			pstmt = con.prepareStatement(UPDATE_ON_EMAIL);
			pstmt.setString(1, u.getUsername());
			pstmt.setString(2, u.getPassword() );
			pstmt.setString(3, u.getAddress());
			pstmt.setString(4, u.getPhone_number());
			pstmt.setString(5, u.getEmail());
			status = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
		
	}

	@Override
	public int deleteUser(String email) {
		try {
			pstmt = con.prepareStatement(DELETE_ON_EMAIL);
			pstmt.setString(1, email);
			status = pstmt.executeUpdate();
		}
	catch(Exception e) {
		e.printStackTrace();
		}
		return status;	
	}
}
