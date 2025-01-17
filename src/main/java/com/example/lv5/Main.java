package com.example.lv5;

import com.example.lv5.kiosk.Kiosk;
import com.example.lv5.menu.Menu;
import com.example.lv5.menu.MenuItem;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Menu burgers = new Menu("Burgers");
        Menu drinks = new Menu("Drinks");
        Menu desserts = new Menu("Desserts");

        initBurgers(burgers);
        initDrinks(drinks);
        initDesserts(desserts);

        Kiosk kiosk = new Kiosk(List.of(burgers, drinks, desserts));
        kiosk.start();
    }

    private static void initBurgers(Menu burgers) {
        burgers.addMenuItems(
            new MenuItem("ShackBurger ", 6.9, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"),
            new MenuItem("SmokeShack  ", 8.9, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"),
            new MenuItem("Cheeseburger", 6.9, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"),
            new MenuItem("Hamburger   ", 5.4, "비프패티를 기반으로 야채가 들어간 기본버거"));
    }

    private static void initDrinks(Menu drinks) {
        drinks.addMenuItems(
            new MenuItem("Lemonade             ", 4.5, "매장에서 직접 만드는 상큼한 레몬에이드"),
            new MenuItem("Fountain Soda        ", 2.9, "코카콜라, 코카콜라 제로, 스프라이트, 환타 오렌지, 환타 그레이프, 환타 파인애플"),
            new MenuItem("Fresh Brewed Iced Tea", 3.7, "직접 유기농 홍차를 우려낸 아이스 티"));
    }

    private static void initDesserts(Menu desserts) {
        desserts.addMenuItems(
            new MenuItem("Classic Hand-Spun Shakes", 6.8, "쫀득하고 진한 커스터드가 들어간 클래식 쉐이크"),
            new MenuItem("Floats                  ", 6.8, "부드러운 바닐라 커스터드와 톡톡 터지는 탄산이 만나 탄생한 색다른 음료"),
            new MenuItem("Cup & Cones             ", 5.7, "매일 점포에서 신선하게 제조하는 쫀득하고 진한 아이스크림"));
    }
}