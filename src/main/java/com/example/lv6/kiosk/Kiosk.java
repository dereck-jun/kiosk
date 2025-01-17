package com.example.lv6.kiosk;

import com.example.lv6.cart.ShoppingCart;
import com.example.lv6.exception.BaseException;
import com.example.lv6.menu.Menu;
import com.example.lv6.menu.MenuItem;
import com.example.lv6.payment.DiscountRate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

import static com.example.lv6.payment.DiscountRate.*;
import static java.util.logging.Level.INFO;

public class Kiosk {
    private final List<Menu> menus;
    private final List<ShoppingCart> carts = new ArrayList<>();
    private final Logger logger = Logger.getLogger("kiosk");

    public Kiosk(List<Menu> menus) {
        this.menus = menus;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMainMenu();
            int selectNum;

            try {
                if (carts.isEmpty()) {
                    selectNum = scanner.nextInt();
                    if (selectNum == 0) {
                        break;
                    } else if (selectNum >= 1 && selectNum <= 3) {
                        handleCartOperations(scanner, selectNum, true);
                    } else {
                        throw new BaseException("메뉴에 있는 번호를 입력해주세요.");
                    }
                } else {
                    printOrderMenu();
                    selectNum = scanner.nextInt();
                    if (selectNum == 0) {
                        break;
                    } else if (selectNum >= 1 && selectNum <= 5) {
                        handleCartOperations(scanner, selectNum, false);
                    } else {
                        throw new BaseException("메뉴에 있는 번호를 입력해주세요.");
                    }
                }
            } catch (BaseException be) {
                System.out.println(be.getMessage());
            } catch (InputMismatchException ime) {
                logger.log(INFO, "com.example.lv6.kiosk.Kiosk start: ", ime);
                scanner.nextLine(); // 버퍼 정리
            }
        }
        System.out.println("프로그램이 종료되었습니다.");
        scanner.close();
    }


    /* 입력값 핸들링 관련 */
    private void handleCartOperations(Scanner scanner, int selectNum, boolean isCartEmpty) {
        if (selectNum >= 1 && selectNum <= menus.size()) {
            handleMenuSelection(scanner, menus.get(selectNum - 1));
        } else if (!isCartEmpty && (selectNum == 4 || selectNum == 5)) {
            handleOrderOrCancel(scanner, selectNum);
        } else {
            throw new BaseException("메뉴에 있는 번호를 입력해주세요.");
        }
    }

    private void handleMenuSelection(Scanner scanner, Menu menu) {
        printMenuInCategory(menu);
        int selectMenu;
        try {
            selectMenu = scanner.nextInt();
        } catch (InputMismatchException ime) {
            logger.log(INFO, "com.example.lv6.kiosk.Kiosk handleMenuSelection: ", ime);
            scanner.nextLine();
            throw new BaseException("숫자만 입력할 수 있습니다.");
        }

        if (selectMenu == 0) return;

        if (selectMenu > menu.getMenuItems().size()) {
            throw new BaseException("메뉴에 있는 번호를 입력해주세요.");
        }

        menu.printSelectedItem(selectMenu);

        System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
        System.out.println("1. 확인              2. 취소");

        int confirmOrNot;
        try {
            confirmOrNot = scanner.nextInt();
        } catch (InputMismatchException ime) {
            logger.log(INFO, "com.example.lv6.kiosk.Kiosk handleMenuSelection: ", ime);
            scanner.nextLine();
            throw new BaseException("숫자만 입력할 수 있습니다.");
        }

        if (confirm(confirmOrNot) && confirmOrNot == 1) {
            addToCart(menu, selectMenu);
        }
    }

    private void handleOrderOrCancel(Scanner scanner, int selectNum) {
        if (selectNum == 4) {
            printOrdersAndPrice();

            int confirmOrder;
            try {
                confirmOrder = scanner.nextInt();
            } catch (InputMismatchException ime) {
                logger.log(INFO, "com.example.lv6.kiosk.Kiosk handleOrderOrCancel: ", ime);
                scanner.nextLine();
                throw new BaseException("숫자만 입력할 수 있습니다.");
            }

            if (confirm(confirmOrder)) {
                payOrGoBack(getDiscountInfo(scanner));
            }
        } else if (selectNum == 5) {
            printItemsInCart();

            System.out.println("삭제할 메뉴의 번호를 입력해주세요. (0 입력 시 뒤로가기)");
            int removeItemIndex;
            try {
                removeItemIndex = scanner.nextInt();
            } catch (InputMismatchException ime) {
                logger.log(INFO, "com.example.lv6.kiosk.Kiosk handleOrderOrCancel: ", ime);
                scanner.nextLine();
                throw new BaseException("숫자만 입력할 수 있습니다.");
            }

            if (removeItemIndex != 0) {
                removeItemFromCartByIndex(removeItemIndex);
            }
        }
    }

    /* 단순 출력 관련  */
    private void printMainMenu() {
        System.out.println("[ MAIN MENU ]");
        for (int i = 0; i < menus.size(); i++) {
            System.out.println((i + 1) + ". " + menus.get(i).getCategory());
        }
        System.out.println("0. 종료");
    }

    private void printOrderMenu() {
        System.out.println();
        System.out.println("[ ORDER MENU ]");
        System.out.println("4. Orders");
        System.out.println("5. Cancel");
    }

    private void printMenuInCategory(Menu menu) {
        System.out.println("[ " + menu.getCategory().toUpperCase() + " MENU ]");
        menu.printMenuItems();
        System.out.println("0. 뒤로가기");
    }

    private void printOrdersAndPrice() {
        System.out.println("아래와 같이 주문하시겠습니까?");
        System.out.println();

        System.out.println("[ Orders ]");
        printItemsInCart();
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

    private void printItemsInCart() {
        AtomicInteger idx = new AtomicInteger(1);
        for (ShoppingCart cart : carts) {
            MenuItem menuItem = cart.getMenuItem();
            System.out.println("[" + idx.getAndIncrement() + "] " + menuItem.getMenuName().trim() + " | W "
                + menuItem.getPrice() + " | " + menuItem.getDescription() + " | " + cart.getQuantity() + "개");
        }
    }

    /* 장바구니 추가 관련 */
    private void addToCart(Menu menu, int selectMenu) {
        MenuItem selectedItem = menu.getMenuItemByIndex(selectMenu);

        carts.stream()
            .filter(cart ->
                cart.getMenuItem().getMenuName()
                    .equalsIgnoreCase(selectedItem.getMenuName()))
            .findFirst()
            .ifPresentOrElse(ShoppingCart::increaseAmount,
                () -> carts.add(new ShoppingCart(selectedItem, 1)));

        System.out.println(selectedItem.getMenuName().trim() + "이 장바구니에 추가되었습니다.");
    }

    /* 장바구니 제거 관련 */
    private void removeItemFromCartByIndex(int selectRemoveItem) {
        ShoppingCart savedItem;
        try {
            savedItem = carts.get(selectRemoveItem - 1);
        } catch (IndexOutOfBoundsException ioobe) {
            logger.log(INFO, "com.example.lv6.kiosk.Kiosk removeItemFromCartByIndex: ", ioobe);
            throw new BaseException("장바구니에 저장된 개수보다 큰 값을 입력할 수 없습니다.");
        }
        if (savedItem.getQuantity() > 0) {
            decreaseItemQuantity(savedItem.getMenuItem().getMenuName());
        }
        carts.removeIf(item -> item.getQuantity() == 0);
    }

    private void decreaseItemQuantity(String menuName) {
        carts.stream()
            .filter(item -> item.getMenuItem().getMenuName().equalsIgnoreCase(menuName))
            .forEach(ShoppingCart::decreaseAmount);
    }

    /* 결제 관련 */
    private void payOrGoBack(int discountIndex) {
        System.out.println("주문이 완료되었습니다. 금액은 W " + getDiscountedTotalPrice(discountIndex) + " 입니다.");
        carts.clear();
    }

    private int getDiscountInfo(Scanner scanner) {
        System.out.println("할인 정보를 입력해주세요.");
        for (DiscountRate value : values()) {
            System.out.println(value.getIndex() + ". " + value.getType() + " : " + value.getPercentage());
        }

        int discountIndex;
        try {
            discountIndex = scanner.nextInt();
        } catch (InputMismatchException ime) {
            logger.log(INFO, "com.example.lv6.kiosk.Kiosk getDiscountInfo: ", ime);
            scanner.nextLine();
            throw new BaseException("숫자만 입력할 수 있습니다.");
        }
        return discountIndex;
    }

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

    /* 입력 값 확인 관련 */
    private boolean confirm(int inputNumber) {
        if (inputNumber != 1 && inputNumber != 2) {
            throw new BaseException("메뉴에 있는 번호를 입력해주세요");
        }
        return inputNumber == 1;
    }
}