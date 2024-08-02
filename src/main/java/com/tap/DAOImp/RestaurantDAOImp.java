package com.tap.DAOImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.tap.DAO.RestaurantDAO;
import com.tap.dbutils.DBUtils;
import com.tap.model.Restaurant;

public class RestaurantDAOImp implements RestaurantDAO {
    Connection con;
    private PreparedStatement pstmt;
    private Statement stmt;
    Restaurant restaurant;
    ArrayList<Restaurant> restaurantList;
    private static final String ADD_RESTAURANT = "INSERT INTO tap_foods.restaurant(`restaurant_name`, `delivery_time`, `cuisine_type`, `address`, `ratings`, `is_active`, `admin_id`, `image_path`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_RESTAURANTS = "SELECT * FROM tap_foods.restaurant";
    private static final String SELECT_ON_RESTAURANT_NAME = "SELECT * FROM tap_foods.restaurant WHERE restaurant_name = ?";
    private static final String UPDATE_ON_RESTAURANT_NAME = "UPDATE tap_foods.restaurant SET delivery_time = ?, cuisine_type = ?, address = ?, ratings = ?, is_active = ?, admin_id = ?, image_path = ? WHERE restaurant_name = ?";
    private static final String DELETE_ON_RESTAURANT_NAME = "DELETE FROM tap_foods.restaurant WHERE restaurant_name = ?";
    int status = 0;
    private ResultSet resultSet;

    public RestaurantDAOImp() {
        try {
            con = DBUtils.myConnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int addRestaurant(Restaurant r) {
        try {
            pstmt = con.prepareStatement(ADD_RESTAURANT);
            pstmt.setString(1, r.getRestaurant_name());
            pstmt.setTimestamp(2, r.getDelivery_time());
            pstmt.setString(3, r.getCuisine_type());
            pstmt.setString(4, r.getAddress());
            pstmt.setFloat(5, r.getRatings());
            pstmt.setBoolean(6, r.getIs_active());
            pstmt.setString(7, r.getAdmin_id());
            pstmt.setString(8, r.getImage_path());
            status = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public ArrayList<Restaurant> getAllRestaurants() {
        try {
            stmt = con.createStatement();
            resultSet = stmt.executeQuery(SELECT_ALL_RESTAURANTS);
            restaurantList = extractRestaurantsFromResultSet(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return restaurantList;
    }

    ArrayList<Restaurant> extractRestaurantsFromResultSet(ResultSet resultSet) {
        restaurantList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                restaurantList.add(new Restaurant(
                    resultSet.getInt("restaurant_id"),
                    resultSet.getString("restaurant_name"),
                    resultSet.getTimestamp("delivery_time"),
                    resultSet.getString("cuisine_type"),
                    resultSet.getString("address"),
                    resultSet.getFloat("ratings"),
                    resultSet.getBoolean("is_active"),
                    resultSet.getString("admin_id"),
                    resultSet.getString("image_path")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return restaurantList;
    }

    @Override
    public Restaurant getRestaurant(String name) {
        try {
            pstmt = con.prepareStatement(SELECT_ON_RESTAURANT_NAME);
            pstmt.setString(1, name);
            resultSet = pstmt.executeQuery();
            restaurantList = extractRestaurantsFromResultSet(resultSet);
            if (!restaurantList.isEmpty()) {
                restaurant = restaurantList.get(0);
            } else {
                restaurant = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return restaurant;
    }

    @Override
    public int updateRestaurant(Restaurant r) {
        try {
            pstmt = con.prepareStatement(UPDATE_ON_RESTAURANT_NAME);
            pstmt.setTimestamp(1, r.getDelivery_time());
            pstmt.setString(2, r.getCuisine_type());
            pstmt.setString(3, r.getAddress());
            pstmt.setFloat(4, r.getRatings());
            pstmt.setBoolean(5, r.getIs_active());
            pstmt.setString(6, r.getAdmin_id());
            pstmt.setString(7, r.getImage_path());
            pstmt.setString(8, r.getRestaurant_name());
            status = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public int deleteRestaurant(String name) {
        try {
            pstmt = con.prepareStatement(DELETE_ON_RESTAURANT_NAME);
            pstmt.setString(1, name);
            status = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
}
