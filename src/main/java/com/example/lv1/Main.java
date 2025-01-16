package com.example.lv1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            int selectNum;
            System.out.println("[ SHAKESHACK MENU ]");
            System.out.println("1. ShackBurger   | W 6.9 | 토마토, 양상추, 쉑 소스가 토핑된 치즈버거");
            System.out.println("2. SmokeShack    | W 8.9 | 베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거");
            System.out.println("3. CheeseBurger  | W 6.9 | 포테이토 번과 비프패티, 치즈가 토핑된 치즈버거");
            System.out.println("4. Hamburger     | W 5.4 | 비프패티를 기반으로 야채가 들어간 기본버거");
            System.out.println("0. 종료");

            try {
                selectNum = scanner.nextInt();
            } catch (InputMismatchException ime) {
                scanner.nextLine();
                System.out.println("숫자만 입력할 수 있습니다.");
                continue;
            }

            if (selectNum == 0) {
                break;
            }

            switch (selectNum) {
                case 1 -> System.out.println("1. ShackBurger   | W 6.9 | 토마토, 양상추, 쉑 소스가 토핑된 치즈버거");
                case 2 -> System.out.println("2. SmokeShack    | W 8.9 | 베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거");
                case 3 -> System.out.println("3. CheeseBurger  | W 6.9 | 포테이토 번과 비프패티, 치즈가 토핑된 치즈버거");
                case 4 -> System.out.println("4. Hamburger     | W 5.4 | 비프패티를 기반으로 야채가 들어간 기본버거");
                default -> System.out.println("메뉴에 있는 번호를 입력해주세요.");
            }
        }
        System.out.println("프로그램을 종료합니다.");
        scanner.close();
    }
}