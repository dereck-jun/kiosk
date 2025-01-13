package com.example.lv4.menu;

public class MenuItem {
    private final String menuName;
    private final double price;
    private final String description;

    public MenuItem(String menuName, double price, String description) {
        this.menuName = menuName;
        this.price = price;
        this.description = description;
    }

    public String getMenuName() {
        return menuName;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return menuName + " | W " + price + " | " + description;
    }
}
