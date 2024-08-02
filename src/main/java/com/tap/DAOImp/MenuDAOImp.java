package com.tap.DAOImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.tap.DAO.MenuDAO;
import com.tap.dbutils.DBUtils;
import com.tap.model.Menu;

public class MenuDAOImp implements MenuDAO {
    Connection con;
    private PreparedStatement pstmt;
    private Statement stmt;
    Menu item;
    ArrayList<Menu> menuList;
    private static final String ADD_ITEM = "insert into tap_foods.menu(`restaurant_id`,`menu_name`,`price`,`description`,`is_available`,`image_path`)"
            + "values(?,?,?,?,?,?)";
    private static final String SELECT_ALL_ITEMS = "select * from tap_foods.menu";
    private static final String SELECT_ON_ITEM_NAME = "select * from tap_foods.menu where item_name=?";
    private static final String UPDATE_ON_ITEM_NAME = "update tap_foods.menu set restaurant_id=?, price=?, description=?, is_available=?, image_path=? where item_name=?";
    private static final String DELETE_ON_NAME = "delete from tap_foods.menu where item_name=?";
    int status = 0;
    private ResultSet resultSet;

    public MenuDAOImp() {
        try {
            con = DBUtils.myConnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public int addItem(Menu m) {
        try {
            pstmt = con.prepareStatement(ADD_ITEM);
            pstmt.setInt(1, m.getRestaurant_id());
            pstmt.setString(2, m.getItem_name());
            pstmt.setFloat(3, m.getPrice());
            pstmt.setString(4, m.getDescription());
            pstmt.setBoolean(5, m.getIs_available());
            pstmt.setString(6, m.getImage_path());
            status = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public ArrayList<Menu> getAllItems() {
        try {
            stmt = con.createStatement();
            resultSet = stmt.executeQuery(SELECT_ALL_ITEMS);
            menuList = extractItemsFromResultSet(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return menuList;
    }
//  menu_id,restaurant_id,menu_name,price,description,is_available,image_path
    ArrayList<Menu> extractItemsFromResultSet(ResultSet resultSet) {
        menuList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                menuList.add(new Menu(resultSet.getInt("item_id"),
                        resultSet.getInt("restaurant_id"),
                        resultSet.getString("item_name"),
                        resultSet.getFloat("price"),
                        resultSet.getString("description"),
                        resultSet.getBoolean("is_available"),
                        resultSet.getString("image_path")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return menuList;
    }

    @Override
    public Menu getItem(String name) {
        try {
            pstmt = con.prepareStatement(SELECT_ON_ITEM_NAME);
            pstmt.setString(1, name);
            resultSet = pstmt.executeQuery();
            menuList = extractItemsFromResultSet(resultSet);
            if (!menuList.isEmpty()) {
                item = menuList.get(0);
            } else {
                item = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public int updateMenu(Menu m) {
        try {
            pstmt = con.prepareStatement(UPDATE_ON_ITEM_NAME);
            pstmt.setInt(1, m.getRestaurant_id());
            pstmt.setDouble(2, m.getPrice());
            pstmt.setString(3, m.getDescription());
            pstmt.setBoolean(4, m.getIs_available());
            pstmt.setString(5, m.getImage_path());
            status = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public int deleteItem(String name) {
        try {
            pstmt = con.prepareStatement(DELETE_ON_NAME);
            pstmt.setString(1, name);
            status = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
}
