package com.tap.DAO;

import java.util.ArrayList;

import com.tap.model.OrderHistory;

public interface OrderHistoryDAO {
    int addOrder(OrderHistory oh);
    ArrayList<OrderHistory> getAllOrders();
    OrderHistory getOrderHistory(int id);
    int updateOrderHistory(OrderHistory oh);
    int deleteOrderHistory(int id);
}
