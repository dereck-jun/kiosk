package com.example.lv4.menu;

public class MenuItem {
    public String menuName;
    public double price;
    public String description;

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
