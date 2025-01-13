package com.example.lv2;

public class MenuItem {
    private final String burgerName;
    private final double price;
    private final String description;

    public MenuItem(String burgerName, double price, String description) {
        this.burgerName = burgerName;
        this.price = price;
        this.description = description;
    }

    public String getBurgerName() {
        return burgerName;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}
