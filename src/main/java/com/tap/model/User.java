package com.tap.model;

public class User {
	private int user_id;
	private String email;
	private String username;
	private String password;
	private String address;
	private String phone_number;
	/**
	 * 
	 */
	public User() {
		super();
	}
	/**
	 * @param id
	 * @param email
	 * @param username
	 * @param password
	 * @param address
	 * @param phone_number
	 */
	public User(int id, String email, String username, String password, String address, String phone_number) {
		super();
		this.user_id = id;
		this.email = email;
		this.username = username;
		this.password = password;
		this.address = address;
		this.phone_number = phone_number;
	}
	/**
	 * @param email
	 * @param username
	 * @param password
	 * @param address
	 * @param phone_number
	 */
	public User(String email, String username, String password, String address, String phone_number) {
		super();
		this.email = email;
		this.username = username;
		this.password = password;
		this.address = address;
		this.phone_number = phone_number;
	}
	public int getId() {
		return user_id;
	}
	public void setId(int id) {
		this.user_id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	@Override
	public String toString() {
		return "user [id=" + user_id + ", email=" + email + ", username=" + username + ", password=" + password
				+ ", address=" + address + ", phone_number=" + phone_number + "]";
	}
}

