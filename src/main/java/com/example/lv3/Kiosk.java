package com.example.lv3;

import java.util.InputMismatchException;
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
            printMainMenu();
            int selectNum;
            try {
                selectNum = scanner.nextInt();
            } catch (InputMismatchException ime) {
                scanner.nextLine();
                System.out.println("숫자만 입력할 수 있습니다.");
                continue;
            }

            if (selectNum == 0) {
                break;
            } else if (selectNum < 0 || selectNum > menuItems.size()) {
                System.out.println("메뉴에 있는 숫자를 입력해주세요.");
                continue;
            }
            printSelectedMenu(selectNum);
        }
        System.out.println("프로그램을 종료합니다.");
    }

    private void printMainMenu() {
        System.out.println("[ SHAKESHACK MENU ]");
        for (int i = 0; i < menuItems.size(); i++) {
            MenuItem menuItem = menuItems.get(i);
            System.out.println((i + 1) + ". " + menuItem.getBurgerName() + " | W " + menuItem.getPrice() + " | " + menuItem.getDescription());
        }
        System.out.println("0. 종료");
    }

    private void printSelectedMenu(int selectNum) {
        MenuItem menuItem = menuItems.get(selectNum - 1);
        System.out.println("선택한 메뉴: " + menuItem.getBurgerName() + ", " + menuItem.getPrice() + ", " + menuItem.getDescription());
    }
}
