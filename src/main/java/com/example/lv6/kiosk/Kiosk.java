package com.example.lv6.kiosk;

import com.example.lv6.cart.ShoppingCart;
import com.example.lv6.exception.BaseException;
import com.example.lv6.menu.Menu;
import com.example.lv6.menu.MenuItem;
import com.example.lv6.payment.DiscountRate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static com.example.lv6.payment.DiscountRate.*;

public class Kiosk {
    private final List<Menu> menus;
    private final List<ShoppingCart> carts = new ArrayList<>();

    public Kiosk(List<Menu> menus) {
        this.menus = menus;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int selectNum;
        while (true) {
            printMainMenu();

            // 장바구니의 상태에 따라서 출력을 다르게 하기 위함
            if (carts.isEmpty()) {
                selectNum = scanner.nextInt();
                if (selectNum != 0) {
                    try {
                        handleEmptyCart(scanner, selectNum);
                    } catch (BaseException be) {
                        System.out.println(be.getMessage());
                    }
                } else {
                    break;
                }
            } else {
                printOrderOrCancel();
                selectNum = scanner.nextInt();
                if (selectNum != 0) {
                    try {
                        handleNonEmptyCart(scanner, selectNum);
                    } catch (BaseException be) {
                        System.out.println(be.getMessage());
                    }
                } else {
                    break;
                }
            }
        }
        System.out.println("프로그램이 종료되었습니다.");
        scanner.close();
    }

    // 장바구니가 비었을 경우
    private void handleEmptyCart(Scanner scanner, int selectNum) {
        // TODO: 공통 case 문 합칠 수 있는지 생각
        switch (selectNum) {
            case 1 -> {
                Menu burgers = menus.get(0);

                printMenuInCategory(burgers);
                int selectMenu = scanner.nextInt();

                if (selectMenu == 0) {
                    return;
                } else if (selectMenu > burgers.getMenuItems().size()) {
                    throw new BaseException("메뉴에 있는 번호를 입력해주세요.");
                }

                // TODO: 아래 코드에서 IndexOutOfBoundsException 발생하니까 핸들링 해야 함
                burgers.printSelectedItem(selectMenu);

                System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
                System.out.println("1. 확인              2. 취소");
                int confirmOrNot = scanner.nextInt();

                if (confirm(confirmOrNot) && confirmOrNot == 1) {
                    addToCart(burgers, selectMenu);
                }
            }
            case 2 -> {
                Menu drinks = menus.get(1);

                printMenuInCategory(drinks);
                int selectMenu = scanner.nextInt();

                if (selectMenu == 0) {
                    return;
                } else if (selectMenu > drinks.getMenuItems().size()) {
                    throw new BaseException("메뉴에 있는 번호를 입력해주세요.");
                }

                drinks.printSelectedItem(selectMenu);

                System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
                System.out.println("1. 확인              2. 취소");
                int confirmOrNot = scanner.nextInt();

                if (confirm(confirmOrNot) && confirmOrNot == 1) {
                    addToCart(drinks, selectMenu);
                }
            }
            case 3 -> {
                Menu desserts = menus.get(2);

                printMenuInCategory(desserts);
                int selectMenu = scanner.nextInt();

                if (selectMenu == 0) {
                    return;
                } else if (selectMenu > desserts.getMenuItems().size()) {
                    throw new BaseException("메뉴에 있는 번호를 입력해주세요.");
                }

                desserts.printSelectedItem(selectMenu);

                System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
                System.out.println("1. 확인              2. 취소");
                int confirmOrNot = scanner.nextInt();

                if (confirm(confirmOrNot) && confirmOrNot == 1) {
                    addToCart(desserts, selectMenu);
                }
            }
            default -> throw new BaseException("메뉴에 있는 번호를 입력해주세요");
        }
    }

    // 장바구니에 품목이 1개 이상일 경우
    private void handleNonEmptyCart(Scanner scanner, int selectNum) {
        switch (selectNum) {
            case 1 -> {
                Menu burgers = menus.get(0);

                printMenuInCategory(burgers);
                int selectMenu = scanner.nextInt();

                if (selectMenu == 0) {
                    return;
                } else if (selectMenu > burgers.getMenuItems().size()) {
                    throw new BaseException("메뉴에 있는 번호를 입력해주세요.");
                }

                burgers.printSelectedItem(selectMenu);

                System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
                System.out.println("1. 확인              2. 취소");
                int confirmOrNot = scanner.nextInt();

                if (confirm(confirmOrNot) && confirmOrNot == 1) {
                    addToCart(burgers, selectMenu);
                }
            }
            case 2 -> {
                Menu drinks = menus.get(1);

                printMenuInCategory(drinks);
                int selectMenu = scanner.nextInt();

                if (selectMenu == 0) {
                    return;
                } else if (selectMenu > drinks.getMenuItems().size()) {
                    throw new BaseException("메뉴에 있는 번호를 입력해주세요.");
                }

                drinks.printSelectedItem(selectMenu);

                System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
                System.out.println("1. 확인              2. 취소");
                int confirmOrNot = scanner.nextInt();

                if (confirm(confirmOrNot) && confirmOrNot == 1) {
                    addToCart(drinks, selectMenu);
                }
            }
            case 3 -> {
                Menu desserts = menus.get(2);

                printMenuInCategory(desserts);
                int selectMenu = scanner.nextInt();

                if (selectMenu == 0) {
                    return;
                } else if (selectMenu > desserts.getMenuItems().size()) {
                    throw new BaseException("메뉴에 있는 번호를 입력해주세요.");
                }

                desserts.printSelectedItem(selectMenu);

                System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
                System.out.println("1. 확인              2. 취소");
                int confirmOrNot = scanner.nextInt();

                if (confirm(confirmOrNot) && confirmOrNot == 1) {
                    addToCart(desserts, selectMenu);
                }
            }
            case 4 -> {
                printOrdersAndPrice();
                int selectOrder = scanner.nextInt();

                if (confirm(selectOrder) && selectOrder == 1) {
                    int discountIndex = getDiscountInfo(scanner);
                    payOrGoBack(discountIndex);
                }
            }
            case 5 -> {
                // TODO: 이 위치에 저장된 장바구니 목록을 불러와야 할 것 같음
                System.out.println("삭제할 메뉴의 번호를 입력해주세요. (0 입력 시 뒤로가기)");
                int selectRemoveItem = scanner.nextInt();

                if (selectRemoveItem == 0) {
                    return;
                } else if (selectRemoveItem > carts.size()) {
                    throw new BaseException("카트에 담긴 수보다 더 큰 수를 입력할 수 없습니다.");
                }
                
                removeItem(selectRemoveItem);
            }
            default -> throw new BaseException("메뉴에 있는 번호를 입력해주세요.");
        }
    }

    // 할인 정보 가져오기
    private static int getDiscountInfo(Scanner scanner) {
        System.out.println("할인 정보를 입력해주세요.");
        for (DiscountRate value : values()) {
            System.out.println(value.getIndex() + ". " + value.getType() + " : " + value.getPercentage());
        }
        return scanner.nextInt();
    }

    private void printMainMenu() {
        System.out.println("[ MAIN MENU ]");
        for (int i = 0; i < menus.size(); i++) {
            System.out.println((i + 1) + ". " + menus.get(i).getCategory());
        }
        System.out.println("0. 종료");
    }

    private void printOrderOrCancel() {
        System.out.println();
        System.out.println("[ ORDER MENU ]");
        System.out.println("4. Orders");
        System.out.println("5. Cancel");
    }

    // 주문 지불 또는 뒤로가기
    private void payOrGoBack(int discountIndex) {
        double result = 0;
        try {
            result = getDiscountedTotalPrice(discountIndex);
            System.out.println("주문이 완료되었습니다. 금액은 W " + result + " 입니다.");
            carts.clear();
        } catch (BaseException be) {
            System.out.println(be.getMessage());
        }
    }

    // 주문 제거
    private void removeItem(int selectRemoveItem) {
        ShoppingCart savedItem = carts.get(selectRemoveItem - 1);
        if (savedItem.getAmount() > 0) {
            removeItemInCart(savedItem.getMenuItem().getMenuName());
        }
        carts.removeIf(item -> item.getAmount() == 0);
    }

    // 특정 카테고리 내의 메뉴 출력
    private void printMenuInCategory(Menu menu) {
        System.out.println("[ " + menu.getCategory().toUpperCase() + " MENU ]");
        menu.printMenuItems();
        System.out.println("0. 뒤로가기");
    }

    // 장바구니 담기
    private void addToCart(Menu menu, int selectMenu) {
        MenuItem selectedItem = menu.getMenuItem(selectMenu - 1);
        if (carts.isEmpty()) {
            carts.add(new ShoppingCart(selectedItem, 1));
        } else {
            Optional<ShoppingCart> optionalItem = carts.stream()
                .filter(item ->
                    item.getMenuItem().getMenuName().equalsIgnoreCase(selectedItem.getMenuName()))
                .findFirst();

            if (optionalItem.isPresent()) {
                optionalItem.get().increaseAmount();
            } else {
                carts.add(new ShoppingCart(selectedItem, 1));
            }
        }
        System.out.println(selectedItem.getMenuName().trim() + "이 장바구니에 추가되었습니다.");
    }

    // 장바구니 내 품목 & 총 가격 출력
    private void printOrdersAndPrice() {
        System.out.println("아래와 같이 주문하시겠습니까?");
        System.out.println();

        System.out.println("[ Orders ]");
        for (ShoppingCart cart : carts) {
            MenuItem menuItem = cart.getMenuItem();
            System.out.println(menuItem.getMenuName().trim() + " | W " + menuItem.getPrice() + " | " + menuItem.getDescription() + " | " + cart.getAmount() + "개");
        }
        System.out.println();

        System.out.println("[ Total ]");
        BigDecimal sum = new BigDecimal("0");
        for (ShoppingCart cart : carts) {
            sum = sum.add(cart.getTotalPrice());
        }
        System.out.println("W " + sum);
        System.out.println();

        System.out.println("1. 주문               2. 메뉴판");
    }

    // 할인율 계산 결과 가져오기
    private double getDiscountedTotalPrice(int discountIndex) {
        BigDecimal result;
        switch (discountIndex) {
            case 1 -> result = calculation(carts, NATIONAL_MERIT);
            case 2 -> result = calculation(carts, SOLIDER);
            case 3 -> result = calculation(carts, STUDENT);
            case 4 -> result = calculation(carts, GENERAL);
            default -> throw new BaseException("할인 정보에 있는 번호를 입력해주세요");
        }
        return result.doubleValue();
    }

    // 장바구니 내 품목 제거 메서드
    private void removeItemInCart(String menuName) {
        carts.stream()
            .filter(item -> item.getMenuItem().getMenuName().equalsIgnoreCase(menuName))
            .forEach(ShoppingCart::decreaseAmount);
    }

    // 확인 및 취소에 대한 값 체크
    private boolean confirm(int inputNumber) {
        try {
            if (inputNumber != 1 && inputNumber != 2) {
                throw new BaseException("메뉴에 있는 번호를 입력해주세요");
            }
        } catch (BaseException be) {
            System.out.println(be.getMessage());
            return false;
        }
        return true;
    }
}