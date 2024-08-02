package com.tap.DAO;

import java.util.ArrayList;

import com.tap.model.OrderTable;

public interface OrderTableDAO {
    int addOrder(OrderTable o);
    ArrayList<OrderTable> getAllOrders();
    OrderTable getOrder(int id);
    int updateOrder(OrderTable o);
    int deleteOrder(int id);
}

