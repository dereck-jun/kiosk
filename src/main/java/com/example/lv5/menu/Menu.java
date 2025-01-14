package com.example.lv5.menu;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private final String category;
    private final List<MenuItem> menuItems = new ArrayList<>();

    public Menu(String category) {
        this.category = category;
    }

    public void addMenuItem(MenuItem menuItem) {
        menuItems.add(menuItem);
    }

    public String getCategory() {
        return category;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void printSelectedItem(int selectedItem) {
        System.out.println("선택한 메뉴: " + menuItems.get(selectedItem - 1));
    }
}
