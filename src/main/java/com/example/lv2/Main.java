package com.example.lv2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<MenuItem> menuItemList = new ArrayList<>();
        menuItemList.add(new MenuItem("ShackBurger", 6.9, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"));
        menuItemList.add(new MenuItem("SmokeShack", 8.9, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"));
        menuItemList.add(new MenuItem("Cheeseburger", 6.9, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"));
        menuItemList.add(new MenuItem("Hamburger", 5.4, "비프패티를 기반으로 야채가 들어간 기본버거"));

        Scanner scanner = new Scanner(System.in);
        int selectNum;
        while (true) {
            System.out.println("[ SHAKESHACK MENU ]");
            for (int i = 0; i < menuItemList.size(); i++) {
                System.out.println((i + 1) + ". " + menuItemList.get(i).getBurgerName() + " | W " + menuItemList.get(i).getPrice() + " | " + menuItemList.get(i).getDescription());
            }

            System.out.println("0. 종료");
            selectNum = scanner.nextInt();

            if (selectNum == 0) {
                break;
            } else if (selectNum < 0) {
                throw new IllegalArgumentException("잘못된 선택입니다.");
            }

            System.out.println("선택한 메뉴: " + menuItemList.get(selectNum - 1).getBurgerName() + ", " + menuItemList.get(selectNum - 1).getPrice() + ", " + menuItemList.get(selectNum - 1).getDescription());
        }
        System.out.println("프로그램을 종료합니다.");
    }
}