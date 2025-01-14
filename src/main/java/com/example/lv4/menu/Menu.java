package com.example.lv4.menu;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    public String category;
    public List<MenuItem> menuItems = new ArrayList<>();

    public Menu(String category) {
        this.category = category;
    }

    public void printSelectedItem(int selectedItem) {
        MenuItem menuItem = menuItems.get(selectedItem - 1);
        System.out.println("선택한 메뉴: " + menuItem.menuName.trim() + " | W " + menuItem.price + " | " + menuItem.description);
    }

    public String getCategory() {
        return category;
    }
}
