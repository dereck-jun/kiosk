package com.example.lv5.menu;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private final String category;
    private final List<MenuItem> menuItems = new ArrayList<>();

    public Menu(String category) {
        this.category = category;
    }

    public void printSelectedItem(int selectedItem) {
        MenuItem menuItem = menuItems.get(selectedItem - 1);
        System.out.println("선택한 메뉴: " + menuItem.getMenuName().trim() + " | W " + menuItem.getPrice() + " | " + menuItem.getDescription());
    }

    public void addMenuItems(MenuItem... menuItem) {
        menuItems.addAll(List.of(menuItem));
    }

    public String getCategory() {
        return category;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }
}
