package com.example.lv4.kiosk;

import com.example.lv4.menu.Menu;
import com.example.lv4.menu.MenuItem;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Kiosk {
    public final List<Menu> menus;

    public Kiosk(List<Menu> menus) {
        this.menus = menus;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMainMenu();
            int selectNum;
            try {
                selectNum = scanner.nextInt();
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println("숫자만 입력할 수 있습니다.");
                continue;
            }

            if (selectNum == 0) {
                break;
            }

            if (selectNum > 0 && selectNum <= menus.size()) {
                handleMenuSelection(scanner, selectNum);
            } else {
                System.out.println("메뉴에 있는 번호를 입력해주세요.");
            }
        }
        System.out.println("프로그램을 종료합니다.");
        scanner.close();
    }

    private void handleMenuSelection(Scanner scanner, int selectNum) {
        Menu menu = menus.get(selectNum - 1);
        printMenuItems(menu);

        int selectMenu;
        try {
            selectMenu = scanner.nextInt();
        } catch (InputMismatchException ime) {
            scanner.nextLine();
            System.out.println("숫자만 입력할 수 있습니다.");
            return;
        }

        if (selectMenu == 0) {
            return;
        }

        if (selectMenu > 0 && selectMenu <= menu.menuItems.size()) {
            menu.printSelectedItem(selectMenu);
        } else {
            System.out.println("메뉴에 있는 번호를 입력해주세요.");
        }
    }

    private void printMainMenu() {
        System.out.println("[ MAIN MENU ]");

        for (int i = 0; i < menus.size(); i++) {
            System.out.println((i + 1) + ". " + menus.get(i).getCategory());
        }

        System.out.println("0. 종료");
    }

    private void printMenuItems(Menu menu) {
        System.out.println("[ " + menu.getCategory().toUpperCase() +" MENU ]");
        for (int i = 0; i < menu.menuItems.size(); i++) {
            System.out.println((i + 1) + ". " + menu.menuItems.get(i));
        }
        System.out.println("0. 뒤로가기");
    }
}
