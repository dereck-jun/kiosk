package com.example.lv6.cart;

import com.example.lv6.menu.MenuItem;

import java.math.BigDecimal;

public class ShoppingCart {
    private MenuItem menuItem;
    private int amount;
    private BigDecimal totalPrice;

    public ShoppingCart() {
    }

    public ShoppingCart(MenuItem menuItem, int amount) {
        this.menuItem = menuItem;
        this.amount = amount;
    }

    public void increaseAmount() {
        this.amount += 1;
    }

    public void decreaseAmount() {
        this.amount -= 1;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public int getAmount() {
        return amount;
    }

    public BigDecimal getTotalPrice() {
        // 정확한 연산을 위해 double -> String -> BigDecimal 변환 후 계산
        BigDecimal menuItemPrice = new BigDecimal(String.valueOf(menuItem.getPrice()));
        return menuItemPrice.multiply(BigDecimal.valueOf(amount));
    }
}
