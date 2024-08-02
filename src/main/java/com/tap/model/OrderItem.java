package com.tap.model;

public class OrderItem {
	private int order_item_id;
	private int order_id;
	private int item_id;
	private int quantity;
	private float subtotal;
	

	public OrderItem() {
		super();
	}
	/**
	 * @param order_id
	 * @param item_id
	 * @param quantity
	 * @param subtotal
	 */
	//Constructor without order_id	
	public OrderItem(int order_id, int menu_id, int quantity, float subtotal) {
		super();
		this.order_id = order_id;
		this.item_id = menu_id;
		this.quantity = quantity;
		this.subtotal = subtotal;
	}
	/**
	 * @param order_item_id
	 * @param order_id
	 * @param item_id
	 * @param quantity
	 * @param subtotal
	 */
	//Constructor with order_id
	public OrderItem(int order_item_id, int order_id, int menu_id, int quantity, float subtotal) {
		super();
		this.order_item_id = order_item_id;
		this.order_id = order_id;
		this.item_id = menu_id;
		this.quantity = quantity;
		this.subtotal = subtotal;
	}
	public int getOrder_item_id() {
		return order_item_id;
	}
	public void setOrder_item_id(int order_item_id) {
		this.order_item_id = order_item_id;
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public int getItem_id() {
		return item_id;
	}
	public void setItem_id(int menu_id) {
		this.item_id = menu_id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(float subtotal) {
		this.subtotal = subtotal;
	}
	@Override
	public String toString() {
		return "OrderItem [order_item_id=" + order_item_id + ", order_id=" + order_id + ", item_id=" + item_id
				+ ", quantity=" + quantity + ", subtotal=" + subtotal + "]";
	}
	
}


