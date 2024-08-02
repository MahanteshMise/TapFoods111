package com.tap.DAO;

import java.util.ArrayList;

import com.tap.model.OrderItem;

public interface OrderItemDAO {
    int addOrderItem(OrderItem oi);
    ArrayList<OrderItem> getAllOrderItems();
    OrderItem getOrderItem(int id);
    int updateOrderItem(OrderItem oi);
    int deleteOrderItem(int id);
}