package com.tap.model;

public class Menu {
	private int item_id;
	private int restaurant_id;
	private String item_name;
	private float price;
	private String description;
	private boolean is_available;
	private String image_path;
	
	public Menu() {
		super();
	}
	/**
	 * @param restaurant_id
	 * @param item_name
	 * @param price
	 * @param description
	 * @param is_available
	 * @param image_path
	 */
	
	public Menu(int restaurant_id, String menu_name, float price, String description, boolean is_available,
			String image_path) {
		super();
		this.restaurant_id = restaurant_id;
		this.item_name = menu_name;
		this.price = price;
		this.description = description;
		this.is_available = is_available;
		this.image_path = image_path;
	}
	/**
	 * @param menu_id
	 * @param restaurant_id
	 * @param item_name
	 * @param price
	 * @param description
	 * @param is_available
	 * @param image_path
	 */
	public Menu(int menu_id, int restaurant_id, String menu_name, float price, String description,
			boolean is_available, String image_path) {
		super();
		this.item_id = menu_id;
		this.restaurant_id = restaurant_id;
		this.item_name = menu_name;
		this.price = price;
		this.description = description;
		this.is_available = is_available;
		this.image_path = image_path;
	}
	public int getMenu_id() {
		return item_id;
	}
	public void setMenu_id(int menu_id) {
		this.item_id = menu_id;
	}
	public int getRestaurant_id() {
		return restaurant_id;
	}
	public void setRestaurant_id(int restaurant_id) {
		this.restaurant_id = restaurant_id;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String menu_name) {
		this.item_name = menu_name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean getIs_available() {
		return is_available;
	}
	public void setIs_available(boolean is_available) {
		this.is_available = is_available;
	}
	public String getImage_path() {
		return image_path;
	}
	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}
}

