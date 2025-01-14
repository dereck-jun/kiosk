package com.example.lv4.kiosk;

import com.example.lv4.menu.Menu;

import java.util.List;
import java.util.Scanner;

public class Kiosk {
    public List<Menu> menus;

    public Kiosk(List<Menu> menus) {
        this.menus = menus;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMainMenu();
            int selectMenu = scanner.nextInt();

            if (inputValidation(selectMenu)) {
                break;
            }

            switch (selectMenu) {
                case 1 -> handleBurgerSelection(scanner);
                case 2 -> handleDrinkSelection(scanner);
                case 3 -> handleDessertSelection(scanner);
                default -> throw new IllegalArgumentException("잘못된 선택입니다.");
            }
        }
        System.out.println("프로그램을 종료합니다.");
    }

    private void printMainMenu() {
        System.out.println("[ MAIN MENU ]");

        for (int i = 0; i < menus.size(); i++) {
            System.out.println((i + 1) + ". " + menus.get(i).getCategory());
        }

        System.out.println("0. 종료");
    }

    private void handleBurgerSelection(Scanner scanner) {
        Menu burgers = menus.get(0);
        printMenuItems(burgers);
        int selectedItem = scanner.nextInt();

        if (inputValidation(selectedItem)) {
            return;
        }
        burgers.printSelectedItem(selectedItem);
    }

    private void handleDrinkSelection(Scanner scanner) {
        Menu drinks = menus.get(1);
        printMenuItems(drinks);
        int selectedItem = scanner.nextInt();

        if (inputValidation(selectedItem)) {
            return;
        }
        drinks.printSelectedItem(selectedItem);
    }

    private void handleDessertSelection(Scanner scanner) {
        Menu desserts = menus.get(2);
        printMenuItems(desserts);
        int selectedItem = scanner.nextInt();

        if (inputValidation(selectedItem)) {
            return;
        }
        desserts.printSelectedItem(selectedItem);
    }

    private static void printMenuItems(Menu menu) {
        System.out.println("[ " + menu.category.toUpperCase() +" MENU ]");
        for (int i = 0; i < menu.menuItems.size(); i++) {
            System.out.println((i + 1) + ". " + menu.menuItems.get(i));
        }
        System.out.println("0. 뒤로가기");
    }

    private static boolean inputValidation(int selectMenu) {
        if (selectMenu == 0) {
            return true;
        } else if (selectMenu < 0) {
            throw new IllegalArgumentException("잘못된 선택입니다.");
        }
        return false;
    }
}
