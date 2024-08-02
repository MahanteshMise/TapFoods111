package com.tap.model;

import java.sql.Timestamp;

public class Restaurant {
	private int restaurant_id;
	private String restaurant_name;
	private Timestamp delivery_time;
	private String cuisine_type;
	private String address;
	private float ratings;
	private boolean is_active;
	private String admin_id;
	private String image_path;
	
	
	


	// Default constructor without the restaurant field
	/**
	 * @param restaurant_name
	 * @param delivery_time
	 * @param cuisine_type
	 * @param address
	 * @param ratings
	 * @param is_active
	 * @param admin_id
	 * @param image_path
	 */
	public Restaurant(String restaurant_name, Timestamp delivery_time, String cuisine_type, String address, float ratings,
			boolean is_active, String admin_id, String image_path) {
		super();
		this.restaurant_name = restaurant_name;
		this.delivery_time = delivery_time;
		this.cuisine_type = cuisine_type;
		this.address = address;
		this.ratings = ratings;
		this.is_active = is_active;
		this.admin_id = admin_id;
		this.image_path = image_path;
	}


	/**
	 * @param restaurant_id
	 * @param restaurant_name
	 * @param delivery_time
	 * @param cuisine_type
	 * @param address
	 * @param ratings
	 * @param is_active
	 * @param admin_id
	 * @param image_path
	 */
	// Default constructor
	public Restaurant(int restaurant_id, String restaurant_name, Timestamp delivery_time, String cuisine_type, String address,
			float ratings, boolean is_active, String admin_id, String image_path) {
		super();
		this.restaurant_id = restaurant_id;
		this.restaurant_name = restaurant_name;
		this.delivery_time = delivery_time;
		this.cuisine_type = cuisine_type;
		this.address = address;
		this.ratings = ratings;
		this.is_active = is_active;
		this.admin_id = admin_id;
		this.image_path = image_path;
	}

	// Setters and Getters
	
	public int getRestaurant_id() {
		return restaurant_id;
	}


	public void setRestaurant_id(int restaurant_id) {
		this.restaurant_id = restaurant_id;
	}


	public String getRestaurant_name() {
		return restaurant_name;
	}


	public void setRestaurant_name(String restaurant_name) {
		this.restaurant_name = restaurant_name;
	}


	public Timestamp getDelivery_time() {
		return delivery_time;
	}


	public void setDelivery_time(Timestamp delivery_time) {
		this.delivery_time = delivery_time;
	}


	public String getCuisine_type() {
		return cuisine_type;
	}


	public void setCuisine_type(String cuisine_type) {
		this.cuisine_type = cuisine_type;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public float getRatings() {
		return ratings;
	}


	public void setRatings(float ratings) {
		this.ratings = ratings;
	}


	public boolean getIs_active() {
		return is_active;
	}


	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}


	public String getAdmin_id() {
		return admin_id;
	}


	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}


	public String getImage_path() {
		return image_path;
	}


	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}

	// Generating the to string method.
	@Override
	public String toString() {
		return "restaurant [restaurant_id=" + restaurant_id + ", restaurant_name=" + restaurant_name
				+ ", delivery_time=" + delivery_time + ", cuisine_type=" + cuisine_type + ", address=" + address
				+ ", ratings=" + ratings + ", is_active=" + is_active + ", admin_id=" + admin_id + ", image_path="
				+ image_path + "]";
	}
	
}




