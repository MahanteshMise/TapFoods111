package com.tap.DAOImp;

import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

import com.tap.DAO.OrderHistoryDAO;
import com.tap.dbutils.DBUtils;
import com.tap.model.OrderHistory;

public class OrderHistoryDAOImp implements OrderHistoryDAO {
    Connection con;
    private PreparedStatement pstmt;
    private Statement stmt;
    OrderHistory orderHistory;
    ArrayList<OrderHistory> orderHistoryList;
    Timestamp t = new Timestamp(System.currentTimeMillis());

    private static final String ADD_ORDER_HISTORY = "insert into tap_foods.order_history(`order_id`, `user_id`, `order_date`, `total_amount`, `status`)"
            + "values(?,?,?,?,?)";
    private static final String SELECT_ALL_ORDER_HISTORY = "select * from tap_foods.order_history";
    private static final String SELECT_ON_ORDER_HISTORY_ID = "select * from tap_foods.order_history where order_history_id=?";
    private static final String UPDATE_ON_ORDER_HISTORY_ID = "update tap_foods.order_history set order_id=?, user_id=?, order_date=?, total_amount=?, status=? where order_history_id=?";
    private static final String DELETE_ON_ORDER_HISTORY_ID = "delete from tap_foods.order_history where order_history_id=?";
    int status = 0;
    private ResultSet resultSet;

    public OrderHistoryDAOImp() {
        try {
            con = DBUtils.myConnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int addOrder(OrderHistory oh) {
        try {
            pstmt = con.prepareStatement(ADD_ORDER_HISTORY);
            pstmt.setInt(1, oh.getOrder_id());
            pstmt.setInt(2, oh.getUser_id());
            pstmt.setTimestamp(3, t, Calendar.getInstance(TimeZone.getTimeZone("UTC")));
            pstmt.setFloat(4, oh.getTotal_amount());
            pstmt.setString(5, oh.getStatus());
            status = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public ArrayList<OrderHistory> getAllOrders() {
        try {
            stmt = con.createStatement();
            resultSet = stmt.executeQuery(SELECT_ALL_ORDER_HISTORY);
            orderHistoryList = extractOrderHistoryFromResultSet(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderHistoryList;
    }

    ArrayList<OrderHistory> extractOrderHistoryFromResultSet(ResultSet resultSet) {
        orderHistoryList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                orderHistoryList.add(new OrderHistory(resultSet.getInt("order_history_id"),
                        resultSet.getInt("order_id"),
                        resultSet.getInt("user_id"),
                        resultSet.getTimestamp("order_date"),
                        resultSet.getFloat("total_amount"),
                        resultSet.getString("status")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderHistoryList;
    }

    @Override
    public OrderHistory getOrderHistory(int id) {
        try {
            pstmt = con.prepareStatement(SELECT_ON_ORDER_HISTORY_ID);
            pstmt.setInt(1, id);
            resultSet = pstmt.executeQuery();
            orderHistoryList = extractOrderHistoryFromResultSet(resultSet);
            if (!orderHistoryList.isEmpty()) {
                orderHistory = orderHistoryList.get(0);
            } else {
                orderHistory = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderHistory;
    }

    @Override
    public int updateOrderHistory(OrderHistory oh) {
        try {
            pstmt = con.prepareStatement(UPDATE_ON_ORDER_HISTORY_ID);
            pstmt.setInt(1, oh.getOrder_id());
            pstmt.setInt(2, oh.getUser_id());
            pstmt.setTimestamp(3, new java.sql.Timestamp(oh.getOrder_date().getTime()));
            pstmt.setFloat(4, oh.getTotal_amount());
            pstmt.setString(5, oh.getStatus());
            pstmt.setInt(6, oh.getOrder_history_id());
            status = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public int deleteOrderHistory(int id) {
        try {
            pstmt = con.prepareStatement(DELETE_ON_ORDER_HISTORY_ID);
            pstmt.setInt(1, id);
            status = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
}
