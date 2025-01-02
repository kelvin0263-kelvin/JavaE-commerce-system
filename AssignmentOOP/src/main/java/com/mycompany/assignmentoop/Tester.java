package com.mycompany.assignmentoop;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tester {

    public static void main(String[] args) {
        int productAmountTracker = 0;
        List<Order> allOrderHistoryList = new ArrayList<>();
        Order order;

        UserRegistration userRegistration = new UserRegistration();

        Admin admin = null;
        Member member = null;
        User loggedInUser = null;  // Store the logged-in user

        TestPaymentMethod paymentMethod = new TestPaymentMethod();
        String userPaymentOption = null;
        boolean paymentStatus = false;
        String paymentMethodName = null;

        String[] deliveryInfoArray = new String[3];
        List<Product> cartProductList = new ArrayList<>();
        Catalog catalog = new Catalog();
        Product[] selectedItemArray = null;
        Product[] ItemArray = null;

        double totalItemFee = 0.0;
        double taxFee = 0.0;
        double subTotalFee = 0.0;
        double promoFee = 0.0;
        int pointEarn = 0;

        catalog.addProduct(new Furniture("FU001", "TTRacing Gaming Chair", 10, 590.00, 50.0, 30.0, 56.0, 10.0));
        catalog.addProduct(new Furniture("FU002", "TTRacing Titus Smart Desk", 10, 2059.00, 100.0, 100.0, 10.0, 10.0));
        catalog.addProduct(new Furniture("FU003", "IKEA Linnmon Table Black", 10, 119.00, 60.0, 30.0, 100.0, 10.0));
        catalog.addProduct(new Furniture("FU004", "Space Ergonimic Chair SpaceGrey", 10, 599.00, 50.0, 30.0, 56.0, 10.0));

        catalog.addProduct(new Fashion("FA001", "Uniqlo AIRism T-Shirt White XXL", 15, 79.90, 10.0, 10.0, 10.0, 1.0));
        catalog.addProduct(new Fashion("FA002", "Uniqlo Relaxed Ankle Jeans Black M", 15, 109.90, 10.0, 10.0, 10.0, 1.0));
        catalog.addProduct(new Fashion("FA003", "H.M Regular Fit T-Shirt Green M", 15, 59.90, 10.0, 10.0, 10.0, 1.0));
        catalog.addProduct(new Fashion("FA004", "H.M Wool-blend blazer Black S", 15, 349.90, 10.0, 10.0, 10.0, 1.0));
        catalog.addProduct(new Fashion("FA005", "Nike Sportswear White L", 109, 79.90, 10.0, 10.0, 10.0, 1.0));

        catalog.addProduct(new Electronics("EL001", "Apple Mac Book Pro Sliver 256GB", 5, 15999.00, 35.0, 24.0, 15.0, 1.83));
        catalog.addProduct(new Electronics("EL002", "Apple Iphone 16 Pro Max Black 1TB", 5, 5999.00, 16.0, 7.5, 0.8, 0.23));
        catalog.addProduct(new Electronics("EL003", "Samsung Galaxy S24 Ultra Pink 512GB ", 5, 6299.00, 16.0, 8.0, 0.8, 0.23));
        catalog.addProduct(new Electronics("EL004", "Samsung Galaxy Tab 9(Wi-Fi) Space Grey 256GB", 5, 3899.00, 25.0, 16.5, 0.6, 0.5));
        catalog.addProduct(new Electronics("EL005", "Nothing Ear Wireless Earbuds Red ", 5, 479.00, 5.0, 5.0, 3.0, 0.10));

        ShoppingCart shoppingCart = new ShoppingCart(catalog);

        boolean stockStatus;

        Scanner input = new Scanner(System.in);

        boolean userloggedIn = false;
        String userMenuOption = null;
        String userOption = "Y";

        boolean adminloggedIn = false;

        // Initial registration/login screen
        while (true) {
            //    Change comment 70- 175
            clearScreen();
            logo();
//
            System.out.printf("%100s\n", "===========================================");
            System.out.printf("%100s\n", "                    Menu                   ");
            System.out.printf("%100s\n", "===========================================");
            System.out.print("\n");
            System.out.printf("%70s %-2s%-15s\n", " ", "1. ", "User");
            System.out.printf("%70s %-2s%-15s\n", " ", "2. ", "Admin");
            System.out.printf("%70s %-2s%-15s\n", " ", "3. ", "Exit");
            System.out.print("\n");
            System.out.printf("%85s", "Enter your option: ");
            userMenuOption = input.next();
            input.nextLine();
//
            switch (userMenuOption) {
                case "1":
                    clearScreen();
                    logo();
                    System.out.printf("%100s\n", "===========================================");
                    System.out.printf("%100s\n", "                   User Menu               ");
                    System.out.printf("%100s\n", "===========================================");
                    System.out.print("\n");
                    System.out.printf("%70s %-2s%-15s\n", " ", "1. ", "Register");
                    System.out.printf("%70s %-2s%-15s\n", " ", "2. ", "Login");
                    System.out.printf("%70s %-2s%-15s\n", " ", "3. ", "Back");
                    System.out.print("\n");
                    System.out.printf("%85s", "Enter your option: ");
                    userOption = input.next();
                    input.nextLine();

                    break;
                case "2":
                    clearScreen();
                    logo();
                    System.out.printf("%100s\n", "===========================================");
                    System.out.printf("%100s\n", "                   Admin Menu               ");
                    System.out.printf("%100s\n", "===========================================");
                    System.out.print("\n");
                    System.out.printf("%70s %-2s%-15s\n", " ", "1. ", "Login");
                    System.out.printf("%70s %-2s%-15s\n", " ", "2. ", "Back");
                    System.out.print("\n");
                    System.out.printf("%85s", "Enter your option: ");
                    userOption = input.next();
                    input.nextLine();

                    break;

                case "3":
                    System.out.printf("%85s", "Exiting...");
                    return; // Exit the program

                default:
                    System.out.printf("%90s", "Please enter a valid option.");
                    System.out.printf("\n%90s", "Press Enter to continue...");
                    input.nextLine().trim();
                    clearScreen();
            }

            if (userMenuOption.equals("1")) {
                switch (userOption) {
                    case "1":
                        userRegistration.register();
                        break;

                    case "2":
                        loggedInUser = loginUser(userRegistration, input);
                        if (loggedInUser instanceof Member) {
                            member = (Member) loggedInUser;
                        }
                        if (loggedInUser != null) {
                            userloggedIn = true;
                            System.out.printf("%85s", "User login successful!");
                            System.out.printf("\n%90s", "Press Enter to continue...");
                            input.nextLine().trim();
                            clearScreen();
                        }
                        break;

                    case "3":
                        userMenuOption = null;
                        break;

                    default:
                        System.out.printf("%90s", "Please enter a valid option.");
                        System.out.printf("\n%90s", "Press Enter to continue...");
                        input.nextLine().trim();
                        clearScreen();
                }
            } else if (userMenuOption.equals("2")) {
                switch (userOption) {

                    case "1":
                        // Admin Login
                        admin = AdminLogin.loginAdmin(); // Use the AdminLogin class to handle admin login
                        if (admin != null) {
                            userloggedIn = false;
                            adminloggedIn = true;

                        }
                        break;

                    case "2":
                        break;

                    default:
                        System.out.printf("\n%90s", "Please enter a valid option.");
                        System.out.printf("\n%90s", "Press Enter to continue...");
                        input.nextLine().trim();
                        clearScreen();
                }

            }

            if (adminloggedIn) {

                while (adminloggedIn) {
                    logo();
                    admin.displayAdminMenu();
                    System.out.print("\n");
                    System.out.printf("%85s", "Enter your option: ");
                    userOption = input.next();
                    input.nextLine();

                    switch (userOption) {
                        case "1":
                            admin.displayInventoryManagmentMenu();
                            System.out.printf("%85s", "Enter your option: ");
                            userOption = input.next();
                            input.nextLine();

                            if (userOption.equals("1")) {
                                admin.inventoryAddProduct(catalog);
                            } else if (userOption.equals("2")) {

                                admin.inventoryAdjustProductQuantity(catalog);
                            } else if (userOption.equals("3")) {
                                //admin remove product
                                admin.inventoryRemoveProduct(catalog);
                            } else if (userOption.equals("4")) {
                                //Exit
                            } else {
                                System.out.printf("%85s", "Invalid Option");
                                System.out.printf("\n%90s", "Press Enter to continue...");
                                input.nextLine().trim();
                                clearScreen();
                            }

                            break;
                        case "2":
                            admin.displaySalesReport(allOrderHistoryList, catalog);
                            break;
                        case "3":
                            admin.displaySystemUserReport(UserRegistration.getUsers(), UserRegistration.getUserCount());
                            break;
                        case "4":
                            adminloggedIn = false;

                            break;

                        default:
                            System.out.printf("\n%90s", "Please enter a valid option.");
                            System.out.printf("\n%90s", "Press Enter to continue...");
                            input.nextLine().trim();
                            clearScreen();
                    }
                }

            } else if (userloggedIn) {
                boolean userMenuStatus = true;
                while (userMenuStatus) {

                    shoppingCart.displayShopingCartMenu();
                    System.out.printf("%85s", "Enter your option: ");
                    userOption = input.next();
                    input.nextLine();
                    switch (userOption) {
                        case "1":
                            selectedItemArray = shoppingCart.selectItem();
                            shoppingCart.addItem(selectedItemArray);
                            break;
                        case "2":
                            shoppingCart.viewItem();
                            break;
                        case "3":
                            shoppingCart.deleteItem();
                            break;
                        case "4":
                            shoppingCart.updateItem();
                            break;
                        case "5":
                            if (shoppingCart.getProductCounter() > 0) {
                                double deliveryFee = 0.0;
                                // deliveryInfoArray = shoppingCart.getDeliveryInfo();
                                double itemTotalWeight = shoppingCart.calcTotalItemWeight();
                                String deliveryOption = shoppingCart.deliverySelection(itemTotalWeight);
                                switch (deliveryOption) {
                                    case "S":
                                        deliveryFee = shoppingCart.standardDeliveryFeeCalc(itemTotalWeight);
                                        deliveryInfoArray = shoppingCart.getDeliveryInfo();
                                        break;
                                    case "B":
                                        deliveryFee = shoppingCart.bulkyDeliveryFeeCalc(itemTotalWeight);
                                        deliveryInfoArray = shoppingCart.getDeliveryInfo();
                                        break;
                                    case "E":
                                        deliveryFee = shoppingCart.standardDeliveryFeeCalc(itemTotalWeight) + 10;
                                        deliveryInfoArray = shoppingCart.getDeliveryInfo();
                                        break;
                                    case "P":
                                        deliveryFee = 0.0;
                                        break;
                                    default:

                                }

                                productAmountTracker = shoppingCart.getProductCounter();
                                ItemArray = shoppingCart.getProductArray();
                                totalItemFee = shoppingCart.calcTotalItemFee(shoppingCart.getProductArray());
                                taxFee = shoppingCart.calculateTaxFee(totalItemFee, deliveryFee);
                                subTotalFee = shoppingCart.calculateTotalFee(totalItemFee, deliveryFee, taxFee);
// Ask if the user wants to apply a promo code
                                boolean promoApplied = false;
                                do {
                                    System.out.printf("%95s", "Do you want to apply a promo code? (y/n): ");
                                    String applyPromo = input.next();
                                    input.nextLine(); // Clear buffer

                                    if (applyPromo.equalsIgnoreCase("y")) {
                                        double tempPrice = subTotalFee;

                                        System.out.printf("%85s", "Enter promo code: ");
                                        String enteredPromoCode = input.nextLine();

                                        // Apply promo code and update subtotal fee
                                        double newSubTotal = member.applyPromoCode(enteredPromoCode, subTotalFee);
                                        promoFee = subTotalFee - newSubTotal; // Calculate promo fee discount

                                        // Check if the promo code changed the subtotal
                                        if (newSubTotal < subTotalFee) {
                                            subTotalFee = newSubTotal; // Update subtotal if promo is applied successfully
                                            promoApplied = true; // Promo was applied successfully
                                            System.out.printf("\n%95s\n", String.format("Promo code applied. Discount: %.2f", promoFee));
                                            System.out.printf("\n%90s", "Press Enter to continue...");
                                            input.nextLine();
                                        } else {
                                            System.out.printf("\n%90s\n", "Invalid promo code. Please try again.");
                                        }

                                    } else if (applyPromo.equalsIgnoreCase("n")) {
                                        promoApplied = true; // User chose not to apply promo code, so exit the loop
                                    } else {
                                        System.out.printf("\n%90s\n", "Invalid input. Please enter 'y' or 'n'.");
                                    }

                                } while (!promoApplied); // Repeat until a valid promo is applied or user chooses 'n'

                                //while (paymentStatus != true) {
                                shoppingCart.displayRoughReceipt(totalItemFee, deliveryFee, taxFee, subTotalFee, paymentMethodName, ItemArray);
                                TestPaymentMethod.displayPaymenttMenu();
                                while (true) {
                                    System.out.printf("%85s", "Enter payment option: ");
                                    userPaymentOption = input.next();
                                    input.nextLine();
                                    if (userPaymentOption.equals("1")) {
                                        paymentStatus = TestPaymentMethod.tngPayment(subTotalFee);
                                        paymentMethodName = "TNG payment";

                                    } else if (userPaymentOption.equals("2")) {
                                        paymentStatus = TestPaymentMethod.fpxPayment(subTotalFee);
                                        paymentMethodName = "FPX payment";

                                    } else if (userPaymentOption.equals("3")) {
                                        paymentStatus = TestPaymentMethod.cardPayment(subTotalFee);
                                        paymentMethodName = "Card payment";

                                    } else if (userPaymentOption.equals("4")) {
                                        break;

                                    } else {
                                        System.out.printf("%90s", "Invalid option.");
                                        System.out.printf("\n%90s", "Press Enter to continue...");
                                        input.nextLine();
                                    }
                                    if (paymentStatus == true) {
                                        break;
                                    }
                                }

                                if (paymentStatus == true) {
                                    if (!deliveryOption.equals("P")) {
                                        shoppingCart.displayDelivryInfo(deliveryInfoArray);
                                    }

                                    if (member != null) {
                                        //System.out.println("You have made a purchase. Earning points...");
                                        member.earnPoints((int) totalItemFee);  // Casting totalItemFee to an integer
                                        pointEarn = (int) totalItemFee / 10;
                                    }

                                    shoppingCart.displayReceipt(totalItemFee, deliveryFee, taxFee, subTotalFee, paymentMethodName, ItemArray, promoFee, pointEarn);

                                    // Original ItemArray with Product objects
// Create a deep copy of ItemArray
                                    Product[] copiedItemArray = new Product[ItemArray.length];
                                    for (int i = 0; i < ItemArray.length; i++) {
                                        if (ItemArray[i] != null) {
                                            copiedItemArray[i] = ItemArray[i].clone(); // Use the clone() method to create a copy
                                        } else {
                                            copiedItemArray[i] = null; // Preserve null values if any
                                        }
                                    }

// Pass the copied array to the Order constructor
                                    order = new Order(copiedItemArray, subTotalFee, deliveryOption, paymentMethodName, productAmountTracker);
                                    loggedInUser.getOrderHistoryList().add(order);
                                    allOrderHistoryList.add(order);

                                    // Display sales report
                                    // admin.displaySalesReport(allOrderHistoryList, catalog);
                                    // Clear the shopping cart only if order was created successfully
                                    if (paymentStatus) {
                                        shoppingCart.clearProductArray();
                                        ItemArray = null; // Reset ItemArray to avoid potential reuse
                                        paymentStatus = false;
                                    }
                                    break;
                                }

                                //cartProductList = shoppingCart.getProductList();
                                //shoppingCart.delivery(cartProductList);
                            } else {
                                System.out.printf("%95s", "You don't have any item in your cart");
                                System.out.printf("\n%90s", "Press Enter to continue...");
                                input.nextLine();
                                clearScreen();
                            }
                            break;

                        case "8":
                            ItemArray = shoppingCart.getProductArray();
                            shoppingCart.restoreCatalogProduct(ItemArray);
                            shoppingCart.clearProductArray();
                            userMenuStatus = false;
                            userloggedIn = false;
                            break;

                        case "6":  // Membership registration
                            if (member == null) {
                                System.out.printf("%95s", "Would you like to register as a member now? (y/n): ");
                                String response = input.next();
                                input.nextLine();

                                if (response.equalsIgnoreCase("y")) {
                                    System.out.printf("%85s", "Enter your birthday (YYYY-MM-DD): ");
                                    String birthday = input.next();
                                    input.nextLine();

                                    // Register the user as a member
                                    member = new Member(loggedInUser.getName(), loggedInUser.getEmail(), loggedInUser.getPassword());
                                    member.setBirthday(birthday);

                                    System.out.printf("\n%90s\n", "Member registration successful.");
                                    System.out.printf("%90s", "Press Enter to continue...");
                                    input.nextLine();

                                    boolean memberMenuStatus = true;
                                    while (memberMenuStatus) {
                                        clearScreen();
                                        logo();
                                        System.out.printf("\n%100s\n", "===========================================");
                                        System.out.printf("%100s\n", "              Member Menu                 ");
                                        System.out.printf("%100s\n", "===========================================");
                                        System.out.printf("%70s %-2s%-15s\n", " ", "1. ", "Redeem Points");
                                        System.out.printf("%70s %-2s%-15s\n", " ", "2. ", "Check Points");
                                        System.out.printf("%70s %-2s%-15s\n", " ", "3. ", "Back");

                                        System.out.printf("%85s", "Enter your option: ");
                                        String memberOption = input.next();
                                        input.nextLine();

                                        switch (memberOption) {
                                            case "1":
                                                member.redeemPromoCode();  // Call the redeem promo code function
                                                break;

                                            case "2":

                                                System.out.printf("\n%85s", "Your current points: " + member.getPoints());
                                                System.out.printf("\n%90s", "Press Enter to continue...");
                                                input.nextLine();
                                                break;

                                            case "3":
                                                memberMenuStatus = false;  // Exit the member menu and go back to the main menu
                                                break;

                                            default:
                                                System.out.printf("%90s", "Invalid option. Please try again.");
                                                System.out.printf("\n%90s", "Press Enter to continue...");
                                                input.nextLine();
                                        }
                                    }
                                }
                            } else {
                                System.out.printf("%95s", "You are already registered as a member.");

                                boolean memberMenuStatus = true;
                                while (memberMenuStatus) {
                                    clearScreen();
                                    logo();
                                    System.out.printf("%100s\n", "===========================================");
                                    System.out.printf("%100s\n", "              Member Menu                 ");
                                    System.out.printf("%100s\n", "===========================================");
                                    System.out.printf("%70s %-2s%-15s\n", " ", "1. ", "Redeem Points");
                                    System.out.printf("%70s %-2s%-15s\n", " ", "2. ", "Check Points");
                                    System.out.printf("%70s %-2s%-15s\n", " ", "3. ", "Back");

                                    System.out.printf("%85s", "Enter your option: ");
                                    String memberOption = input.next();
                                    input.nextLine();

                                    switch (memberOption) {
                                        case "1":
                                            clearScreen();
                                            logo();
                                            member.redeemPromoCode();  // Call the redeem promo code function
                                            break;

                                        case "2":
                                            clearScreen();
                                            logo();
                                            System.out.printf("%85s", "Your current points: " + member.getPoints());
                                            System.out.printf("\n%90s", "Press Enter to continue...");
                                            input.nextLine();
                                            break;

                                        case "3":
                                            memberMenuStatus = false;  // Exit the member menu and go back to the main menu
                                            break;

                                        default:
                                            System.out.printf("%90s", "Invalid option. Please try again.");
                                            System.out.printf("\n%90s", "Press Enter to continue...");
                                            input.nextLine();
                                    }
                                }
                            }
                            break;

                        case "7":
                            shoppingCart.displayOrderHistory(loggedInUser.getOrderHistoryList());

                            break;
                        default:
                            System.out.printf("%90s", "Invalid option.");
                            System.out.printf("\n%90s", "Press Enter to continue...");
                            input.nextLine();
                            clearScreen();
                    }
                }

            }
        }
//Change line 262 (commnet the }
    }

    private static User loginUser(UserRegistration userRegistration, Scanner input) {
        System.out.printf("%85s", "Enter your username: ");
        String username = input.next();
        input.nextLine();

      

        User user = UserRegistration.findUserByName(username); // Adjust method name accordingly

        if (user == null) {
            System.out.printf("\n%95s\n", "No user found with that username.");
            System.out.printf("\n%90s", "Press Enter to continue...");
            input.nextLine();
            clearScreen();
            return null;
        }

        System.out.printf("%85s", "Enter your password: ");
        String password = input.next();
        input.nextLine();

        if (!user.checkPassword(password)) {
            System.out.printf("%85s", "Incorrect password.");
            System.out.printf("\n%90s", "Press Enter to continue...");
            input.nextLine();
            clearScreen();
            return null;
        }

        System.out.printf("%90s", "Login successful! Welcome, " + user.getName() + ".");
        System.out.printf("\n%90s", "Press Enter to continue...");
        input.nextLine();
        clearScreen();

        return user;
    }

    private static Admin loginAdmin(Scanner input) {
        System.out.printf("%85s", "Enter your admin username: ");
        String username = input.next();
        input.nextLine();

        // Find the admin by the username using the findAdminByName method
        Admin adminLoginInstance = AdminLogin.findAdminByName(username);
        if (adminLoginInstance == null) {
            System.out.printf("%85s", "No admin found with that username.");
            System.out.printf("\n%90s", "Press Enter to continue...");
            input.nextLine();
            clearScreen();
            return null; // Return null to indicate login failure
        }

        System.out.printf("%85s", "Enter your password: ");
        String password = input.next();
        input.nextLine();

        // Check if the entered password is correct
        if (!adminLoginInstance.checkPassword(password)) {
            System.out.printf("%85s", "Incorrect password.");
            return null; // Return null to indicate login failure
        }

        System.out.printf("%85s", "Login successful! Welcome, " + adminLoginInstance.getName() + ".");

        // Convert the adminLoginInstance into an Admin object
        Admin loggedInAdmin = new Admin(adminLoginInstance.getName(), adminLoginInstance.getEmail(), password);
        return loggedInAdmin;
    }

    public static void clearScreen() {
        for (int i = 0; i < 50; i++) { // Print 50 blank lines
            System.out.println();
        }
    }

    public static void logo() {

        System.out.print("\n");
        System.out.printf("%120s\n", "  ______   __                                  __    __                                __ ");
        System.out.printf("%120s\n", " /      \\ /  |                                /  \\  /  |                              /  |");
        System.out.printf("%120s\n", "/@@@@@@  |@@ |____    ______    ______        @@  \\ @@ |  ______   __   __   __       @@ |");
        System.out.printf("%120s\n", "@@ \\__@@/ @@      \\  /      \\  /      \\       @@@  \\@@ | /      \\ /  | /  | /  |      @@ |");
        System.out.printf("%120s\n", "@@      \\ @@@@@@@  |/@@@@@@  |/@@@@@@  |      @@@@  @@ |/@@@@@@  |@@ | @@ | @@ |      @@ |");
        System.out.printf("%120s\n", " @@@@@@  |@@ |  @@ |@@ |  @@ |@@ |  @@ |      @@ @@ @@ |@@ |  @@ |@@ | @@ | @@ |      @@/ ");
        System.out.printf("%120s\n", "/  \\__@@ |@@ |  @@ |@@ \\__@@ |@@ |__@@ |      @@ |@@@@ |@@ \\__@@ |@@ \\_@@ \\_@@ |       __ ");
        System.out.printf("%120s\n", "@@    @@/ @@ |  @@ |@@    @@/ @@    @@/       @@ | @@@ |@@    @@/ @@   @@   @@/       /  \\");
        System.out.printf("%121s\n", " @@@@@@/  @@/   @@/  @@@@@@/  @@@@@@@/        @@/   @@/  @@@@@@/   @@@@@/@@@@/        \\__/ ");
        System.out.printf("%120s\n", "                              @@ |                                                        ");
        System.out.printf("%120s\n", "                              @@ |                                                        ");
        System.out.printf("%120s\n", "                              @@/                                                         ");

    }

}
