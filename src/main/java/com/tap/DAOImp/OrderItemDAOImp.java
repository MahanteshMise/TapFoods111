package com.tap.DAOImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.tap.DAO.OrderItemDAO;
import com.tap.dbutils.DBUtils;
import com.tap.model.OrderItem;

public class OrderItemDAOImp implements OrderItemDAO {
    Connection con;
    private PreparedStatement pstmt;
    private Statement stmt;
    OrderItem orderItem;
    ArrayList<OrderItem> orderItemList;
    private static final String ADD_ORDER_ITEM = "insert into tap_foods.order_item(`order_id`, `menu_id`, `quantity`, `subtotal`)"
            + "values(?,?,?,?)";
    private static final String SELECT_ALL_ORDER_ITEMS = "select * from tap_foods.order_item";
    private static final String SELECT_ON_ORDER_ITEM_ID = "select * from tap_foods.order_item where order_item_id=?";
    private static final String UPDATE_ON_ORDER_ITEM_ID = "update tap_foods.order_item set order_id=?, menu_id=?, quantity=?, subtotal=? where order_item_id=?";
    private static final String DELETE_ON_ORDER_ITEM_ID = "delete from tap_foods.order_item where order_item_id=?";
    int status = 0;
    private ResultSet resultSet;

    public OrderItemDAOImp() {
        try {
            con = DBUtils.myConnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int addOrderItem(OrderItem oi) {
        try {
            pstmt = con.prepareStatement(ADD_ORDER_ITEM);
            pstmt.setInt(1, oi.getOrder_id());
            pstmt.setInt(2, oi.getItem_id());
            pstmt.setInt(3, oi.getQuantity());
            pstmt.setFloat(4, oi.getSubtotal());
            status = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public ArrayList<OrderItem> getAllOrderItems() {
        try {
            stmt = con.createStatement();
            resultSet = stmt.executeQuery(SELECT_ALL_ORDER_ITEMS);
            orderItemList = extractOrderItemsFromResultSet(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderItemList;
    }

    ArrayList<OrderItem> extractOrderItemsFromResultSet(ResultSet resultSet) {
        orderItemList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                orderItemList.add(new OrderItem(resultSet.getInt("order_item_id"),
                        resultSet.getInt("order_id"),
                        resultSet.getInt("menu_id"),
                        resultSet.getInt("quantity"),
                        resultSet.getFloat("subtotal")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderItemList;
    }

    @Override
    public OrderItem getOrderItem(int id) {
        try {
            pstmt = con.prepareStatement(SELECT_ON_ORDER_ITEM_ID);
            pstmt.setInt(1, id);
            resultSet = pstmt.executeQuery();
            orderItemList = extractOrderItemsFromResultSet(resultSet);
            if (!orderItemList.isEmpty()) {
                orderItem = orderItemList.get(0);
            } else {
                orderItem = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderItem;
    }

    @Override
    public int updateOrderItem(OrderItem oi) {
        try {
            pstmt = con.prepareStatement(UPDATE_ON_ORDER_ITEM_ID);
            pstmt.setInt(1, oi.getOrder_id());
            pstmt.setInt(2, oi.getItem_id());
            pstmt.setInt(3, oi.getQuantity());
            pstmt.setFloat(4, oi.getSubtotal());
            pstmt.setInt(5, oi.getOrder_item_id());
            status = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public int deleteOrderItem(int id) {
        try {
            pstmt = con.prepareStatement(DELETE_ON_ORDER_ITEM_ID);
            pstmt.setInt(1, id);
            status = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
}
