package com.tap.DAO;

import java.util.ArrayList;

import com.tap.model.Restaurant;

public interface RestaurantDAO {
    int addRestaurant(Restaurant r);
    ArrayList<Restaurant> getAllRestaurants();
    Restaurant getRestaurant(String name);
    int updateRestaurant(Restaurant r);
    int deleteRestaurant(String name);
}