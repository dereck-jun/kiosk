package com.example.lv2;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int selectNum;
        List<MenuItem> menuItemList = new ArrayList<>(
            List.of(
                new MenuItem("ShackBurger", 6.9, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"),
                new MenuItem("SmokeShack", 8.9, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"),
                new MenuItem("Cheeseburger", 6.9, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"),
                new MenuItem("Hamburger", 5.4, "비프패티를 기반으로 야채가 들어간 기본버거")
            ));

        while (true) {
            System.out.println("[ SHAKESHACK MENU ]");
            for (int i = 0; i < menuItemList.size(); i++) {
                MenuItem menuItem = menuItemList.get(i);
                System.out.println((i + 1) + ". " + menuItem.getBurgerName() + " | W " + menuItem.getPrice() + " | " + menuItem.getDescription());
            }

            System.out.println("0. 종료");
            try {
                selectNum = scanner.nextInt();
            } catch (InputMismatchException ime) {
                scanner.nextLine();
                System.out.println("숫자를 입력해주세요");
                continue;
            }

            if (selectNum == 0) {
                break;
            }

            if (selectNum > 0 && selectNum <= menuItemList.size()) {
                MenuItem menuItem = menuItemList.get(selectNum - 1);
                System.out.println("선택한 메뉴: " + menuItem.getBurgerName() + ", " + menuItem.getPrice() + ", " + menuItem.getDescription());
            } else {
                System.out.println("메뉴에 있는 숫자를 입력해주세요.");
            }
        }
        System.out.println("프로그램을 종료합니다.");
        scanner.close();
    }
}