package com.example.lv3;

import java.util.List;
import java.util.Scanner;

public class Kiosk {
    private final List<MenuItem> menuItems;

    public Kiosk(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printAllMenu();
            int selectNum = scanner.nextInt();

            if (selectNum == 0) {
                break;
            } else if (selectNum < 0) {
                throw new IllegalArgumentException("잘못된 선택입니다.");
            }
            printSelectedMenu(selectNum);
        }
        System.out.println("프로그램을 종료합니다.");
    }

    private void printAllMenu() {
        System.out.println("[ SHAKESHACK MENU ]");
        for (int i = 0; i < menuItems.size(); i++) {
            System.out.println((i + 1) + ". " + menuItems.get(i).getBurgerName() + " | W " + menuItems.get(i).getPrice() + " | " + menuItems.get(i).getDescription());
        }
        System.out.println("0. 종료");
    }

    private void printSelectedMenu(int selectNum) {
        System.out.println("선택한 메뉴: " + menuItems.get(selectNum - 1).getBurgerName() + ", "
            + menuItems.get(selectNum - 1).getPrice() + ", " + menuItems.get(selectNum - 1).getDescription());
    }
}
