package com.example.lv6.menu;

import com.example.lv6.exception.BaseException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Menu {
    private final String category;
    private final List<MenuItem> menuItems = new ArrayList<>();

    public Menu(String category) {
        this.category = category;
    }

    public void addMenuItem(MenuItem menuItem) {
        menuItems.add(menuItem);
    }

    public void addMenuItems(MenuItem... menuItemList) {
        menuItems.addAll(List.of(menuItemList));
    }

    public MenuItem getMenuItem(int selectedNum) {
        return menuItems.get(selectedNum);
    }

    public void printMenuItems() {
        // Functional Interface 내부에선 final value 아니면 사용할 수 없기 때문에 AtomicInteger.getAndIncrement() 사용
        AtomicInteger idx = new AtomicInteger(1);  
        menuItems.stream()
            .map(item -> idx.getAndIncrement() + ". " + item.getMenuName().trim() + " | W " + item.getPrice() + " | " + item.getDescription())
            .forEach(System.out::println);
    }

    public void printSelectedItem(int selectedNum) {
        MenuItem menuItem = menuItems.get(selectedNum - 1);
        System.out.println(menuItem.getMenuName().trim() + " | W " + menuItem.getPrice() + " | " + menuItem.getDescription());
    }

    public String getCategory() {
        return category;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }
}
