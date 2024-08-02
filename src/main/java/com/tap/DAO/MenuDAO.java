package com.tap.DAO;

import java.util.ArrayList;

import com.tap.model.Menu;

public interface MenuDAO {
    int addItem(Menu m);
    ArrayList<Menu> getAllItems();
    Menu getItem(String name);
    int updateMenu(Menu m);
    int deleteItem(String name);
}
