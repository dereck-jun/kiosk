package com.example.lv6.payment;

import com.example.lv6.cart.ShoppingCart;

import java.math.BigDecimal;
import java.util.List;

public enum DiscountRate {
    NATIONAL_MERIT(1, "국가유공자", "10%",  0.9),
    SOLIDER(2, "군인", "5%",  0.95),
    STUDENT(3, "학생", "3%",  0.97),
    GENERAL(4, "일반", "0%",  1);

    private final int index;
    private final String type;
    private final String percentage;
    private final double rate;

    DiscountRate(int index, String type, String percentage, double rate) {
        this.index = index;
        this.type = type;
        this.percentage = percentage;
        this.rate = rate;
    }

    // 할인율 적용 계산
    public static BigDecimal calculation(List<ShoppingCart> carts, DiscountRate discountRate) {
        BigDecimal sum = new BigDecimal("0");
        for (ShoppingCart cart : carts) {
            sum = sum.add(cart.getTotalPrice());
        }
        return sum.multiply(new BigDecimal(Double.toString(discountRate.rate)));
    }

    public int getIndex() {
        return index;
    }

    public String getType() {
        return type;
    }

    public double getRate() {
        return rate;
    }

    public String getPercentage() {
        return percentage;
    }
}
