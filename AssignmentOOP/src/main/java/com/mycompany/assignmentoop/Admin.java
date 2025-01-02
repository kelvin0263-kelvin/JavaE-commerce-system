package com.mycompany.assignmentoop;

import static com.mycompany.assignmentoop.ShoppingCart.clearScreen;
import static com.mycompany.assignmentoop.Tester.logo;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

public class Admin {

    private String name;
    private String email;
    private String password;
    private boolean isVerified;

    // Constructor for Admin
    public Admin(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.isVerified = false; // Default to not verified
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIsVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Admin{" + "name=" + name + ", email=" + email + ", password=" + password + ", isVerified=" + isVerified + ", input=" + input + '}';
    }

    Scanner input = new Scanner(System.in);

    public boolean isIsVerified() {
        return isVerified;
    }

    public boolean checkPassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    public void displayAdminMenu() {
        clearScreen();

        logo();

        System.out.printf("%100s\n", "===========================================");
        System.out.printf("%100s\n", "               Admin Menu                  ");
        System.out.printf("%100s\n", "===========================================");
        System.out.printf("%60s %-2s%15s\n", " ", "1. ", "Inventory Management");
        System.out.printf("%60s %-2s%15s\n", " ", "2. ", "Views Sales Report");
        System.out.printf("%60s %-2s%15s\n", " ", "3. ", "System User Report");
        System.out.printf("%60s %-2s%15s\n", " ", "4. ", "Log Out");
        System.out.printf("%100s\n", "===========================================");

    }

    public void displaySystemUserReport(User[] users, int userCount) {
        clearScreen();
        logo();
        System.out.printf("%100s\n", "===========================================");
        System.out.printf("%100s\n", "              User Report                  ");
        System.out.printf("%100s\n", "===========================================");
        System.out.printf("%100s\n", "   Name                       Email        ");
        System.out.printf("%100s\n\n", "===========================================");

        // System.out.printf("countttttttttt"+userCount);
        // Check if there are users to display
        if (userCount > 0) {
            for (int i = 0; i < userCount; i++) {
                // Print each user's name and email with proper alignment
                System.out.printf("%58s%-25s %-25s\n", "", users[i].getName(), users[i].getEmail());
            }
        } else {
            System.out.printf("%85s\n", "No Users Registered");
        }
        System.out.printf("\n%100s\n", "===========================================");
        System.out.printf("%100s\n", "Total Register User : " + userCount);
        System.out.printf("%100s\n", "===========================================");

        System.out.printf("\n%90s", "Press Enter to continue...");
        input.nextLine();
        clearScreen();

    }

    public void displayInventoryManagmentMenu() {

        clearScreen();
        logo();
        System.out.printf("%100s\n", "===========================================");
        System.out.printf("%100s\n", "               Inventory Menu              ");
        System.out.printf("%100s\n", "===========================================");
        System.out.printf("%60s %-2s%15s\n", " ", "1. ", "Add New Product");
        System.out.printf("%60s %-2s%15s\n", " ", "2. ", "Add Product Quantiy");
        System.out.printf("%60s %-2s%15s\n", " ", "3. ", "Remove Product");
        System.out.printf("%60s %-2s%15s\n", " ", "4. ", "Exit");
        System.out.printf("%100s\n", "===========================================");

    }

    public void inventoryAddProduct(Catalog catalog) {
        Scanner scanner = new Scanner(System.in);
        int quantity = 0;
        double price = 0;
        double length = 0;
        double width = 0;
        double height = 0;
        double weight = 0;
        // Prompt for product type with alignment
        System.out.printf("\n%90s\n", "Product type(Furniture/Fashion/Electronics)");

        System.out.printf("%80s", "Enter product type: ");
        String productType = scanner.nextLine().toUpperCase();

        if (!productType.equals("FURNITURE") && !productType.equals("FASHION") && !productType.equals("ELECTRONICS")) {
            System.out.printf("%80s\n", "Invalid product type.");
            System.out.printf("%80s", "Press Enter to continue...");
            input.nextLine();
            clearScreen();
            return;
        }

        String productId = null;
        do {
            System.out.printf("%80s", "Enter product ID: ");
            productId = scanner.nextLine();
            if (productId.isEmpty()) { // Check if the input is empty after trimming
                System.out.printf("%110s\n", "Product id cannot be empty. Please enter a valid product id.");
            }

        } while (productId.isEmpty()); // Repeat until a non-empty input is provided        F
        // Check if productId already exists in the catalog
        if (catalog.findProductById(productId) != null) {
            System.out.printf("%80s\n", "Error: Product ID already exists.");
            System.out.printf("%80s\n", "Please enter a unique Product ID.");
            System.out.printf("\n%90s", "Press Enter to continue...");
            input.nextLine();
            clearScreen();
            return; // Exit the method if the product ID is not unique
        }
//
//        // Prompt for product name with alignment
//        System.out.printf("%80s", "Enter product name: ");
        String productName = null;

        do {
            System.out.printf("%80s", "Enter product name: ");
            productName = scanner.nextLine().trim(); // Read input and trim whitespace

            if (productName.isEmpty()) { // Check if the input is empty after trimming
                System.out.printf("%110s\n", "Product name cannot be empty. Please enter a valid product name.");
            }

        } while (productName.isEmpty()); // Repeat until a non-empty input is provided        

        try {
            // Prompt for product quantity with alignment
            System.out.printf("%80s", "Enter product quantity: ");
            quantity = scanner.nextInt();
            scanner.nextLine(); // Clear the newline character left in the buffer

            // Prompt for product price with alignment
            System.out.printf("%80s", "Enter product price: ");
            price = scanner.nextDouble();
            scanner.nextLine(); // Clear the newline character left in the buffer

            // Prompt for product length with alignment
            System.out.printf("%80s", "Enter product length: ");
            length = scanner.nextDouble();
            scanner.nextLine(); // Clear the newline character left in the buffer

            // Prompt for product width with alignment
            System.out.printf("%80s", "Enter product width: ");
            width = scanner.nextDouble();
            scanner.nextLine(); // Clear the newline character left in the buffer

            // Prompt for product height with alignment
            System.out.printf("%80s", "Enter product height: ");
            height = scanner.nextDouble();
            scanner.nextLine(); // Clear the newline character left in the buffer
        } catch (Exception ex) {
            System.out.printf("\n%80s", "Enter number only.");
            System.out.printf("\n%80s", "Press Enter to continue...");
            input.nextLine();
            return;

        }
        // Create and add the product to the catalog based on the type
        Product newProduct = null;

        if (productType.equalsIgnoreCase(
                "FURNITURE")) {
            newProduct = new Furniture(productId, productName, quantity, price, length, width, height, weight);
        } else if (productType.equalsIgnoreCase(
                "FASHION")) {
            newProduct = new Fashion(productId, productName, quantity, price, length, width, height, weight);
        } else if (productType.equalsIgnoreCase(
                "ELECTRONICS")) {
            newProduct = new Electronics(productId, productName, quantity, price, length, width, height, weight);
        } else {
            System.out.printf("%80s\n", "Invalid product type.");
            System.out.printf("\n%90s", "Press Enter to continue...");
            input.nextLine();
            clearScreen();
            return; // Exit the method if the product type is invalid
        }

        catalog.addProduct(newProduct);

        System.out.printf(
                "%80s\n", "Product added successfully.");
        System.out.printf(
                "\n%90s", "Press Enter to continue...");
        input.nextLine();

        clearScreen();
    }

// Method to adjust product quantity in the catalog
    public void inventoryAdjustProductQuantity(Catalog catalog) {

        Scanner scanner = new Scanner(System.in);
        int newQuantity = 0;
        // Display all products in the catalog
        System.out.printf("%115s\n", "=========================== Product List ======================================");
        System.out.printf("%115s\n", "Product ID   Product Name                                  Price (RM)  Quantity");
        System.out.printf("%115s\n", "================================================================================");

        for (Product product : catalog.getProducts()) {
//            System.out.printf("%-15s%-25s%-10.2f%-10d\n",
//                    product.getProductID(),
//                    product.getProductName(),
//                    product.getPrice(),
//                    product.getQuantity());
//            
            System.out.printf("%45s", product.getProductID());
            System.out.print(" ");
            System.out.printf("%-50s", product.getProductName());
            System.out.printf("%10.2f", product.getPrice());
            System.out.printf("%10d\n", product.getQuantity());
        }

        System.out.printf("%115s\n", "=======================================================================");

        System.out.printf("%80s", "Enter product ID to adjust quantity: ");
        String productId = scanner.nextLine();
        // Find the product in the catalog
        Product product = catalog.findProductById(productId);

        if (product == null) {
            System.out.printf("\n%80s\n", "Product not found.");
            System.out.printf("%80s", "Press Enter to continue...");
            input.nextLine();
            clearScreen();
            return;
        }

        try {
            System.out.printf("%80s", "Enter new quantity: ");
            newQuantity = scanner.nextInt();
        } catch (Exception ex) {
            System.out.printf("%80s\n", "Please enter number only");
            System.out.printf("%80s", "Press Enter to continue...");
            input.nextLine();
            return;
        }

        if (product != null) {
            product.setQuantity(newQuantity);
            System.out.printf("%90s\n", "Product quantity updated successfully.");
            System.out.printf("%80s", "Press Enter to continue...");
            input.nextLine();
            clearScreen();
        } else {

            System.out.printf("%80s", "Product not found.");
            System.out.printf("%80s", "Press Enter to continue...");
            input.nextLine();
            clearScreen();
            return;
        }
    }

    // Method to display all products and remove a selected product from the catalog
    public void inventoryRemoveProduct(Catalog catalog) {
        Scanner scanner = new Scanner(System.in);

        System.out.printf("%115s\n", "=========================== Product List ======================================");
        System.out.printf("%115s\n", "Product ID   Product Name                                  Price (RM)  Quantity");
        System.out.printf("%115s\n", "================================================================================");

        for (Product product : catalog.getProducts()) {

            System.out.printf("%45s", product.getProductID());
            System.out.print(" ");
            System.out.printf("%-50s", product.getProductName());
            System.out.printf("%10.2f", product.getPrice());
            System.out.printf("%10d\n", product.getQuantity());
        }

        System.out.printf("%115s\n", "=======================================================================");

        // Prompt the user to enter the product ID to remove
        System.out.printf("%80s", "Enter the Product ID to remove: ");
        String productId = scanner.nextLine();

        // Find and remove the product within this method
        boolean productRemoved = false;
        for (int i = 0; i < catalog.getProducts().size(); i++) {
            Product product = catalog.getProducts().get(i);
            if (product.getProductID().equalsIgnoreCase(productId)) {
                catalog.getProducts().remove(i);
                System.out.printf("%80s\n", "Product removed successfully.");
                productRemoved = true;
                System.out.printf("%80s", "Press Enter to continue...");

                input.nextLine();
                clearScreen();
                return;
            }
        }

        if (!productRemoved) {
            System.out.printf("%80s\n", "Product not found.");
            System.out.printf("%90s\n", "Please check the Product ID and try again.");
            System.out.printf("%80s", "Press Enter to continue...");

            input.nextLine();
            clearScreen();
            return;

        }
    }

    public void displaySalesReport(List<Order> allOrderList, Catalog catalog) {
        clearScreen();
        // Lambda expression to get the delivery method name
        Function<String, String> getDeliveryMethodName = (deliveryOption) -> {
            switch (deliveryOption.toUpperCase()) {
                case "P":
                    return "Pickup";
                case "S":
                    return "Standard";
                case "B":
                    return "Bulky";
                case "E":
                    return "Express";
                default:
                    return "Unknown";
            }
        };

        System.out.printf("%50s %s\n", "", "=================================================================================");
        System.out.printf("%50s %s\n", "", "                                    Sales Report                                 ");
        System.out.printf("%50s %s\n", "", "=================================================================================");
        System.out.print("\n");

        // Variables to keep track of total sales and number of orders
        double totalSales = 0.0;
        int totalOrders = allOrderList.size();

        // HashMap to track total quantity sold for each product
        Map<String, Integer> productSales = new HashMap<>();

        // Date formatter for displaying order dates
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

        // Display each order's details
        System.out.printf("%50s %s\n", "", "=================================================================================");
        System.out.printf("%50s %s\n", "", "Order ID    Order Date     Total Price (RM)     Delivery Method   Payment Method");
        System.out.printf("%50s %s\n", "", "=================================================================================");

        for (Order order : allOrderList) {
            // Add the order's total price to the total sales
            totalSales += order.getTotalPrice();

            // Determine the delivery method based on the code in the order
            String deliveryMethod = getDeliveryMethodName.apply(order.getDeliveryMethod());

            // Display order details
            System.out.printf("%50s %-10s %-15s %-20.2f %-20s %-15s\n", "",
                    order.getOrderId(),
                    dateFormatter.format(order.getOrderDate()), // Format date to display
                    order.getTotalPrice(),
                    deliveryMethod, // Display human-readable delivery method
                    order.getPaymentMethod());

            // Aggregate the quantities sold for each product in this order
            for (Product product : order.getProductList()) {
                if (product != null) {  // Added null check here
                    String productName = product.getProductName();
                    int quantity = product.getQuantity();
                    productSales.put(productName, productSales.getOrDefault(productName, 0) + quantity);
                } else {
                    //  System.out.println("Warning: Null product found in order ID " + order.getOrderId());
                }
            }
        }

        // Display the summary
        System.out.printf("%50s %s\n", "", "=================================================================================");
        System.out.printf("\n%50s %-25s: %d\n", "", "Total Number of Orders", totalOrders);
        System.out.printf("%51s%-25s: RM %.2f\n\n", "", "Total Sales", totalSales);
        System.out.printf("%50s %s\n", "", "=================================================================================");

        // Display total quantity sold for each product in the catalog
        System.out.printf("%50s%s\n", "", "       Product Name                                            Total Quantity Sold");
        System.out.printf("%50s %s\n", "", "=================================================================================");

        for (Product catalogProduct : catalog.getProducts()) {
            String productName = catalogProduct.getProductName();
            int soldQuantity = productSales.getOrDefault(productName, 0); // Get the sold quantity or 0 if not sold
            System.out.printf("%50s%-75s %-20d\n", "", productName, soldQuantity);
        }

        System.out.printf("%50s %s\n", "", "=================================================================================");

        System.out.printf("\n%90s", "Press Enter to continue...");
        input.nextLine().trim();
        clearScreen();
    }

}
