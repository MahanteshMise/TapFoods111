package com.tap.DAOImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import com.tap.DAO.OrderTableDAO;
import com.tap.dbutils.DBUtils;
import com.tap.model.OrderTable;

public class OrderTableDAOImp implements OrderTableDAO {
    Connection con;
    private PreparedStatement pstmt;
    private Statement stmt;
    OrderTable order;
    ArrayList<OrderTable> orderList;
    private static final String ADD_ORDER = "INSERT INTO tap_foods.order_table(`restaurant_id`, `user_id`, `order_date`, `total_amount`, `status`, `payment_mode`) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_ORDERS = "SELECT * FROM tap_foods.order_table";
    private static final String SELECT_ON_ORDER_ID = "SELECT * FROM tap_foods.order_table WHERE order_id = ?";
    private static final String UPDATE_ON_ORDER_ID = "UPDATE tap_foods.order_table SET restaurant_id = ?, user_id = ?, order_date = ?, total_amount = ?, status = ?, payment_mode = ? WHERE order_id = ?";
    private static final String DELETE_ON_ORDER_ID = "DELETE FROM tap_foods.order_table WHERE order_id = ?";
    int status = 0;
    private ResultSet resultSet;

    public OrderTableDAOImp() {
        try {
            con = DBUtils.myConnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int addOrder(OrderTable o) {
        try {
            pstmt = con.prepareStatement(ADD_ORDER);
            pstmt.setInt(1, o.getRestaurant_id());
            pstmt.setInt(2, o.getUser_id());
            pstmt.setTimestamp(3, new java.sql.Timestamp(o.getOrder_date().getTime()));
            pstmt.setFloat(4, o.getTotal_amount());
            pstmt.setString(5, o.getStatus());
            pstmt.setString(6, o.getPayment_mode());
            status = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public ArrayList<OrderTable> getAllOrders() {
        try {
            stmt = con.createStatement();
            resultSet = stmt.executeQuery(SELECT_ALL_ORDERS);
            orderList = extractOrdersFromResultSet(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderList;
    }

    ArrayList<OrderTable> extractOrdersFromResultSet(ResultSet resultSet) {
        orderList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                orderList.add(new OrderTable(
                    resultSet.getInt("order_id"),
                    resultSet.getInt("restaurant_id"),
                    resultSet.getInt("user_id"),
                    resultSet.getTimestamp("order_date"),
                    resultSet.getFloat("total_amount"),
                    resultSet.getString("status"),
                    resultSet.getString("payment_mode")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderList;
    }

    @Override
    public OrderTable getOrder(int id) {
        try {
            pstmt = con.prepareStatement(SELECT_ON_ORDER_ID);
            pstmt.setInt(1, id);
            resultSet = pstmt.executeQuery();
            orderList = extractOrdersFromResultSet(resultSet);
            if (!orderList.isEmpty()) {
                order = orderList.get(0);
            } else {
                order = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public int updateOrder(OrderTable o) {
        try {
            pstmt = con.prepareStatement(UPDATE_ON_ORDER_ID);
            pstmt.setInt(1, o.getRestaurant_id());
            pstmt.setInt(2, o.getUser_id());
            pstmt.setTimestamp(3, new java.sql.Timestamp(o.getOrder_date().getTime()));
            pstmt.setFloat(4, o.getTotal_amount());
            pstmt.setString(5, o.getStatus());
            pstmt.setString(6, o.getPayment_mode());
            pstmt.setInt(7, o.getOrder_id());
            status = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public int deleteOrder(int id) {
        try {
            pstmt = con.prepareStatement(DELETE_ON_ORDER_ID);
            pstmt.setInt(1, id);
            status = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
}
