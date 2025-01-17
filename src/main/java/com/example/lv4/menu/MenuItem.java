package com.example.lv4.menu;

public class MenuItem {
    public final String menuName;
    public final double price;
    public final String description;

    public MenuItem(String menuName, double price, String description) {
        this.menuName = menuName;
        this.price = price;
        this.description = description;
    }

    @Override
    public String toString() {
        return menuName + " | W " + price + " | " + description;
    }
}
