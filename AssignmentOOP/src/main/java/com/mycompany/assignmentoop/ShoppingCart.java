package com.mycompany.assignmentoop;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

public class ShoppingCart implements Delivery {

    Scanner input = new Scanner(System.in);

    private List<Product> productList;
    private Product productArray[];
    private int productCounter = 0;
    private Catalog catalog;

    public ShoppingCart(Catalog catalog) {
        productList = new ArrayList<>();
        productArray = new Product[100];
        this.catalog = catalog;
    }
    //////////////////////Getter and Setter Start/////////////////////////

    public int getProductCounter() {
        return productCounter;
    }

    public Product[] getProductArray() {
        return productArray;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public void setProductArray(Product[] productArray) {
        this.productArray = productArray;
    }

    public void setProductCounter(int productCounter) {
        this.productCounter = productCounter;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" + "productList=" + productList + ", productArray=" + productArray + ", productCounter=" + productCounter + ", catalog=" + catalog + '}';
    }

    //////////////////////Getter and Setter End/////////////////////////
    String userOption;

    public void displayShopingCartMenu() {

        clearScreen();

        logo();
        System.out.printf("%100s\n", "===========================================");
        System.out.printf("%100s\n", "               Ordering Option             ");
        System.out.printf("%100s\n", "===========================================");
        System.out.printf("%60s %-2s%15s\n", " ", "1. ", "Insert Item");
        System.out.printf("%60s %-2s%15s\n", " ", "2. ", "View Cart");
        System.out.printf("%60s %-2s%15s\n", " ", "3. ", "Remove Item");
        System.out.printf("%60s %-2s%15s\n", " ", "4. ", "Update Cart");
        System.out.printf("%60s %-2s%15s\n", " ", "5. ", "Proceed to Checkout");
        System.out.printf("%60s %-2s%15s\n", " ", "6. ", "Member");
        System.out.printf("%60s %-2s%15s\n", " ", "7. ", "Order History");
        System.out.printf("%60s %-2s%15s\n", " ", "8. ", "Log out");
        System.out.printf("%100s\n", "===========================================");

    }

    public void displayOrderHistory(List<Order> allOrderList) {

        clearScreen();        // Check if the order list is null or empty
        if (allOrderList == null || allOrderList.isEmpty()) {
            System.out.printf("%90s\n", "No records found.");
            System.out.printf("%90s", "Press Enter to continue...");
            input.nextLine(); // Wait for user input to proceed
            clearScreen();    // Clear the screen after the message
            return;           // Exit the method
        }

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

        // Display the header of the sales report
        System.out.printf("%50s %s\n", "", "=================================================================================");
        System.out.printf("%50s %s\n", "", "                                   Order History                                 ");
        System.out.printf("%50s %s\n\n", "", "=================================================================================");

        // Variables to keep track of total sales and number of orders
        double totalSales = 0.0;
        int totalOrders = allOrderList.size();

        // Date formatter for displaying order dates
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

        // Display each order's details
        for (Order order : allOrderList) {
            System.out.printf("\n\n%50s %s\n", "", "---------------------------------- Order Details -------------------------------");
            System.out.printf("%50s %s\n", "", "Order ID    Order Date     Total Price (RM)     Delivery Method   Payment Method");
            System.out.printf("%50s %s\n", "", "---------------------------------------------------------------------------------");
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

            // Display each product in the current order
            System.out.printf("\n%50s %s\n", "", "--------------------------------- Product Details -------------------------------");
            System.out.printf("%50s %-50s %-20s %-10s\n", "", "Product Name", "Price (RM)", "Quantity");

            Product[] products = order.getProductList(); // Get product list from the order
            if (products != null) { // Check if the product list is not null
                for (int i = 0; i < order.getProductAmount(); i++) { // Loop through the products
                    Product product = products[i];
                    if (product != null) { // Check if the product is not null
                        System.out.printf("%50s %-50s %-20.2f %-10d\n", "",
                                product.getProductName(),
                                product.getPrice(),
                                product.getQuantity());
                    } else {
                        System.out.printf("%50s %s\n", "", "Null product found in the list.");
                    }
                }
            } else {
                System.out.printf("%50s %s\n", "", "No products available for this order.");
            }

            System.out.printf("%50s %s\n", "", "---------------------------------------------------------------------------------");

        }

        System.out.printf("\n%90s", "Press Enter to continue...");
        input.nextLine().trim();
        clearScreen();
    }

    public void addItem(Product[] selectedItem) {
        // Clear the existing productArray by setting it to a new array if needed
        productArray = new Product[selectedItem.length];

        // Copy all elements from selectedItem to productArray
        System.arraycopy(selectedItem, 0, productArray, 0, selectedItem.length);

        // Set the productCounter to reflect the number of items in the passed-in array
        productCounter = selectedItem.length;
    }

    public Product[] selectItem() {
        int counter = 0; // For printing the catalog item purpose
        int productIndex = 0; //User select the product option that displayed on catalog
        int productQuantity = 0;
        String productCategory = "";
        List<Product> selectedProductList = new ArrayList<>();
        Product[] selectedProductArray = null;

        if (productArray != null) {
            for (Product product : productArray) {
                if (product != null) { // Check if the product is not null
                    System.out.println(product.getProductName());

                    selectedProductList.add((Product) product.clone()); // Clone to avoid reference issues
                }
            }

        }
        do {
            // Display box top border using printf with centered alignment
            clearScreen();
            logo();
            System.out.printf("%100s\n", "===========================================");
            System.out.printf("%100s\n", "              Shopping Option              ");
            System.out.printf("%100s\n", "===========================================");

            // Display the menu options centered within the box
            System.out.printf("%60s %s\n", " ", "1. Show All Products");
            System.out.printf("%60s %s\n", " ", "2. Shop by Category");
            System.out.printf("%60s %s\n", " ", "3. Shop by Search");
            System.out.printf("%60s %s\n", " ", "4. Go Back");

            // Display box bottom border using printf with centered alignment
            System.out.printf("%100s\n", "===========================================");
            // Prompt user to enter an option
            System.out.printf("%85s", "Enter your option: ");
            userOption = input.next(); // Capture the user's option
            input.nextLine();

            // Check if the entered option is invalid
            if (!userOption.equals("1") && !userOption.equals("2")
                    && !userOption.equals("3") && !userOption.equals("4")) {
                System.out.printf("\n%85s\n", "Invalid option.");
                System.out.printf("\n%90s", "Press Enter to continue...");
                input.nextLine();
                clearScreen();

            }

        } while (!userOption.equals("1") && !userOption.equals("2")
                && !userOption.equals("3") && !userOption.equals("4"));

        switch (userOption) {
            case "1":
                clearScreen();
                logo();
                System.out.printf("%115s\n", "=========================== Product List ==============================");
                System.out.printf("%115s\n", "No   Product Name                                  Price (RM)  Quantity");
                System.out.printf("%115s\n", "=======================================================================");
                counter = 0; // Reset counter each time you enter case "1"
                for (Product product : catalog.getProducts()) {
                    System.out.printf("%45d", (counter + 1));
                    System.out.print(" ");
                    System.out.printf("%-50s", product.getProductName());
                    System.out.printf("%10.2f", product.getPrice());
                    System.out.printf("%10d\n", product.getQuantity());
                    counter += 1;
                }
                System.out.printf("%115s\n", "=======================================================================");

                while (true) {

                    System.out.printf("\n\n%85s\n", "Type 'no' to exit");
                    System.out.printf("%85s", "Enter the product option : ");
                    String userOption = input.next();
                    input.nextLine();

                    if (userOption.equalsIgnoreCase("no")) {
                        break;
                    }

                    boolean userOptionIntStatus = true;

                    try {
                        productIndex = Integer.parseInt(userOption);
                    } catch (Exception ex) {
                        userOptionIntStatus = false;
                    }

                    if (userOptionIntStatus) {
                        try {

                            if (productIndex > 0 && productIndex <= catalog.getProducts().size()) {
                                Product selectedProduct = catalog.getProducts().get(productIndex - 1);

                                if (selectedProduct.getQuantity() > 0) {
                                    try {
                                        System.out.printf("%85s", "Enter the product quantity: ");
                                        productQuantity = input.nextInt();
                                        input.nextLine();

                                    } catch (Exception ex) {
                                        System.out.printf("%85s", "Enter number only.");

                                    }
/////////////////////
                                    if (productQuantity > 0 && productQuantity <= selectedProduct.getQuantity()) {
                                        // Check if the product already exists in the selectedProductList
                                        boolean productExists = false;
                                        for (Product p : selectedProductList) {
                                            if (p.getProductName().equals(selectedProduct.getProductName())) {
                                                // Increase the quantity of the existing product in the cart
                                                int currentQuantity = p.getQuantity();
                                                p.setQuantity(currentQuantity + productQuantity);
                                                productExists = true;
                                                break;
                                            }
                                        }

// If the product does not already exist in the cart, add it
                                        if (!productExists) {
                                            Product clonedProduct = (Product) selectedProduct.clone();
                                            clonedProduct.setQuantity(productQuantity);
                                            selectedProductList.add(clonedProduct);
                                        }

                                        // Reduce the quantity from the original product in the catalog
                                        selectedProduct.setQuantity(selectedProduct.getQuantity() - productQuantity);

                                        // Display a message showing the total quantity in the cart
                                        int totalQuantityInCart = selectedProductList.stream()
                                                .filter(p -> p.getProductName().equals(selectedProduct.getProductName()))
                                                .mapToInt(Product::getQuantity)
                                                .sum();
                                        System.out.printf("\n\n%95s", productQuantity + " " + selectedProduct.getProductName() + "(s) added to cart.");
                                        System.out.printf("\n%85s", "Total quantity now: " + totalQuantityInCart);

                                    } else {
                                        System.out.printf("\n%85s", "Invalid quantity.");
                                        System.out.printf("\n%95s", "Please enter a quantity between 1 and " + selectedProduct.getQuantity());
                                        input.nextLine();

                                    }
                                } else {
                                    System.out.printf("%85s", "Product Out of Stock");
                                }

                            } else {
                                System.out.printf("%90s", "Please select a valid product index.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.printf("%\n85s\n", "Invalid input.");
                        }
                    } else {
                        System.out.printf("%90s\n", "Please select a valid product index.");

                    }

                }
                break;

            case "2":
                clearScreen();
                logo();
                System.out.printf("\n%100s", "===========================================");
                System.out.printf("\n%100s", "              Product Categoty             ");
                System.out.printf("\n%100s", "===========================================");
                System.out.printf("\n%60s %s\n", " ", "1. Electronics");
                System.out.printf("%60s %s\n", " ", "2. Fashion");
                System.out.printf("%60s %s\n", " ", "3. Furniture");
                System.out.printf("%60s %s\n", " ", "4. Exit");
                System.out.printf("%100s\n", "===========================================");

                System.out.printf("\n%85s", "Enter your option: ");
                userOption = input.next();
                input.nextLine();

                System.out.printf("%115s\n", "=========================== Product List ==============================");
                System.out.printf("%115s\n", "No   Product Name                                  Price (RM)  Quantity");
                System.out.printf("%115s\n", "=======================================================================");

                // Display products based on selected category
                if (userOption.equals("1")) {
                    productCategory = "Electronics";
                    counter = 0; // Reset counter
                    for (Product product : catalog.getProducts()) {
                        if (product instanceof Electronics) {
                            System.out.printf("%45d", (counter + 1));
                            System.out.print(" ");
                            System.out.printf("%-50s", product.getProductName());
                            System.out.printf("%10.2f", product.getPrice());
                            System.out.printf("%10d\n", product.getQuantity());
                            counter += 1;
                        }
                    }
                    System.out.printf("%115s\n", "=======================================================================");

                } else if (userOption.equals("2")) {
                    productCategory = "Fashion";
                    counter = 0; // Reset counter
                    for (Product product : catalog.getProducts()) {
                        if (product instanceof Fashion) {
                            System.out.printf("%45d", (counter + 1));
                            System.out.print(" ");
                            System.out.printf("%-50s", product.getProductName());
                            System.out.printf("%10.2f", product.getPrice());
                            System.out.printf("%10d\n", product.getQuantity());
                            counter += 1;
                        }
                    }
                    System.out.printf("%115s\n", "=======================================================================");

                } else if (userOption.equals("3")) {
                    productCategory = "Furniture";
                    counter = 0; // Reset counter
                    for (Product product : catalog.getProducts()) {
                        if (product instanceof Furniture) {
                            System.out.printf("%45d", (counter + 1));
                            System.out.print(" ");
                            System.out.printf("%-50s", product.getProductName());
                            System.out.printf("%10.2f", product.getPrice());
                            System.out.printf("%10d\n", product.getQuantity());
                            counter += 1;
                        }
                    }
                    System.out.printf("%115s\n", "=======================================================================");

                } else if (userOption.equals("4")) {
                    clearScreen();
                    break;

                } else {
                    System.out.printf("\n%90s", "Invalid category selection");
                    System.out.printf("\n%90s", "Press Enter to continue...");
                    input.nextLine();
                    clearScreen();
                    break;
                }
                // Display products based on selected category

                while (true) {
                    boolean userOptionIntStatus = true;

                    System.out.printf("\n\n%85s\n", "Type 'no' to exit");
                    System.out.printf("%85s", "Enter the product option : ");
                    userOption = input.next();
                    input.nextLine();

                    if (userOption.equalsIgnoreCase("no")) {
                        break;
                    }

                    try {
                        productIndex = Integer.parseInt(userOption);
                    } catch (Exception ex) {
                        userOptionIntStatus = false;

                    }

                    if (userOptionIntStatus) {

                        if (productIndex > 0 && productIndex <= counter) { // Ensure index is within the displayed products
                            Product selectedProduct = catalog.getFilterProducts(productCategory).get(productIndex - 1);

                            if (selectedProduct.getQuantity() > 0) {
                                try {
                                    System.out.printf("\n%85s", "Enter product quantity: ");
                                    productQuantity = input.nextInt();
                                    input.nextLine();

                                } catch (Exception ex) {
                                    System.out.printf("\n%85s", "Enter number only. ");

                                }
                                if (productQuantity > 0 && productQuantity <= selectedProduct.getQuantity()) {
                                    // Check if the product already exists in selectedProductList
                                    boolean productExists = false;
                                    for (Product p : selectedProductList) {
                                        if (p.getProductName().equals(selectedProduct.getProductName())) {
                                            // Increase the quantity of the existing product in the cart
                                            int currentQuantity = p.getQuantity();
                                            p.setQuantity(currentQuantity + productQuantity);
                                            productExists = true;
                                            break;
                                        }
                                    }

                                    // If the product does not already exist in the cart, add it
                                    if (!productExists) {
                                        // Clone the product to avoid sharing the same instance
                                        Product clonedProduct = (Product) selectedProduct.clone();
                                        clonedProduct.setQuantity(productQuantity);
                                        selectedProductList.add(clonedProduct);
                                    }

                                    // Reduce the quantity from the original product in the catalog
                                    selectedProduct.setQuantity(selectedProduct.getQuantity() - productQuantity);

                                    // Display a message showing the total quantity in the cart
                                    int totalQuantityInCart = selectedProductList.stream()
                                            .filter(p -> p.getProductName().equals(selectedProduct.getProductName()))
                                            .mapToInt(Product::getQuantity)
                                            .sum();

                                    System.out.printf("\n\n%95s", productQuantity + " " + selectedProduct.getProductName() + "(s) added to cart.");
                                    System.out.printf("\n%85s", "Total quantity now: " + totalQuantityInCart);

                                } else {
                                    System.out.printf("\n%85s", "Invalid quantity.");
                                    System.out.printf("\n%95s", "Please enter a quantity between 1 and " + selectedProduct.getQuantity());
                                    input.nextLine();

                                }
                            } else {
                                System.out.printf("%85s", "Product Out of Stock");

                            }

                        } else {
                            System.out.printf("%90s", "Please select a valid product index.");

                        }

                    } else {
                        System.out.printf("%90s", "Please select a valid product index.");
                    }

                }
                break;

            case "3":
                List<Product> searchResults = new ArrayList<>();

                System.out.printf("%85s", "Enter product name: ");

                userOption = input.next();
                input.nextLine();

                System.out.printf("%115s\n", "=========================== Product List ==============================");
                System.out.printf("%115s\n", "No   Product Name                                  Price (RM)  Quantity");
                System.out.printf("%115s\n", "=======================================================================");
                // Display products based on search input
                for (Product product : catalog.getProducts()) {
                    if (product.getProductName().toLowerCase().contains(userOption.toLowerCase())) {

                        System.out.printf("%45d", (searchResults.size() + 1));
                        System.out.print(" ");
                        System.out.printf("%-50s", product.getProductName());
                        System.out.printf("%10.2f", product.getPrice());
                        System.out.printf("%10d\n", product.getQuantity());
                        counter += 1;
                        searchResults.add(product);
                    }
                }
                System.out.printf("%115s\n", "=======================================================================");

                if (searchResults.isEmpty()) {
                    System.out.printf("%90s", "No products found matching your search.");
                    System.out.printf("\n%90s", "Press Enter to continue...");
                    input.nextLine();
                    clearScreen();
                    break;
                }

                while (true) {

                    System.out.printf("\n\n%85s\n", "Type 'no' to exit");
                    System.out.printf("%85s", "Enter the product option : ");
                    userOption = input.next();
                    input.nextLine();

                    if (userOption.equalsIgnoreCase("no")) {
                        break;
                    }

                    try {
                        productIndex = Integer.parseInt(userOption);

                        if (productIndex > 0 && productIndex <= searchResults.size()) {
                            Product selectedProduct = searchResults.get(productIndex - 1);

                            if (selectedProduct.getQuantity() > 0) {
                                try {
                                    System.out.printf("%85s", "Enter the product quantity: ");
                                    productQuantity = input.nextInt();
                                    input.nextLine();

                                } catch (Exception ex) {
                                    System.out.printf("%85s", "Enter number only");
                                }

                                if (productQuantity <= 0 || productQuantity > selectedProduct.getQuantity()) {
                                    input.nextLine();
                                    System.out.printf("\n%85s", "Invalid quantity.");
                                    System.out.printf("\n%95s", "Please enter a quantity between 1 and " + selectedProduct.getQuantity());
                                    continue;
                                }

                                // Check if the product is already in the selectedProductList
                                boolean productExists = false;
                                for (Product p : selectedProductList) {
                                    if (p.getProductName().equals(selectedProduct.getProductName())) {
                                        // Increase the quantity of the existing product in the cart
                                        int currentQuantity = p.getQuantity();
                                        p.setQuantity(currentQuantity + productQuantity);
                                        productExists = true;
                                        break;
                                    }
                                }

                                // If the product does not already exist in the cart, add it
                                if (!productExists) {
                                    // Clone the product to avoid sharing the same instance
                                    Product clonedProduct = (Product) selectedProduct.clone();
                                    clonedProduct.setQuantity(productQuantity);
                                    selectedProductList.add(clonedProduct);
                                }

                                // Update the stock quantity of the original product
                                int productCurrentQuantity = selectedProduct.getQuantity();
                                selectedProduct.setQuantity(productCurrentQuantity - productQuantity);

                                //    System.out.printf("\n\n%95s", productQuantity + " " + selectedProduct.getProductName() + "(s) added to cart.");
                                System.out.printf("\n\n%115s", productQuantity + " " + selectedProduct.getProductName() + "(s) added to cart. Total quantity now: "
                                        + selectedProductList.stream()
                                                .filter(p -> p.getProductName().equals(selectedProduct.getProductName()))
                                                .map(Product::getQuantity)
                                                .findFirst()
                                                .orElse(0));

                            } else {
                                System.out.printf("%85s", "Product Out of Stock");

                            }

                        } else {
                            System.out.printf("%85s", "Invalid product index.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.printf("%85s", "Invalid input. ");
                    }
                }
                break;

            case "4":

                break;
            default:

        }

        selectedProductArray = selectedProductList.toArray(new Product[0]);
        selectedProductList.clear();

        return selectedProductArray;
    }

    public void viewItem() {
        int index = 1;
        clearScreen();

        if (productCounter > 0) {
            logo();
            System.out.printf("%100s\n", "===========================================");
            System.out.printf("%100s\n", "               Current Cart Item           ");
            System.out.printf("%100s\n", "===========================================");
            for (int a = 0; a < productCounter; a++) {

                try {

                    // Print product number
                    System.out.printf("%100s\n", " "); // Adjust spacing to align properly
                    System.out.printf("%60s %s\n", " ", "Product " + index++);

                    // Print product name
                    System.out.printf("%60s %s %s\n", " ", "Product Name : ", productArray[a].getProductName());

                    // Print total price
                    System.out.printf("%60s %s %.2f\n", " ", "Product Total Price : ", productArray[a].getPrice() * productArray[a].getQuantity());

                    // Print quantity
                    System.out.printf("%60s %s %d\n", " ", "Product Quantity : ", productArray[a].getQuantity());
                } catch (NullPointerException e) {
                    System.out.printf("%85s", "Product Display Error");
                    break;
                }
            }

            System.out.printf("%100s\n", "===========================================");
            System.out.printf("\n%90s", "Press Enter to continue...");
            input.nextLine();

        } else {
            System.out.printf("%95s", "You don't have any item in your cart");
            System.out.printf("\n%90s", "Press Enter to continue...");
            input.nextLine();
            clearScreen();
        }

    }

    public void restoreCatalogProduct(Product[] itemArray) {
        // Loop through each removed product in the itemArray
        for (Product removedProduct : itemArray) {
            if (removedProduct == null) {
                continue; // Skip if null
            }
            // Loop through each product in the catalog to find a match
            for (Product catalogProduct : catalog.getProducts()) {
                if (catalogProduct.getProductID().equals(removedProduct.getProductID())) {
                    // Restore the quantity to the catalog product
                    catalogProduct.setQuantity(catalogProduct.getQuantity() + removedProduct.getQuantity());
                    break; // Exit inner loop since the product has been found
                }
            }
        }
    }

    public void deleteItem() {
        if (productCounter > 0) {
            int removeIndex;
            viewItem(); // Display current items in the cart

            try {

                System.out.printf("\n%95s", "Enter the item number to remove: ");
                removeIndex = input.nextInt();
                input.nextLine();

                // Validate the entered index
                if (removeIndex > 0 && removeIndex <= productCounter) {
                    removeIndex = removeIndex - 1; // Adjust to zero-based index

                    // Store the quantity to add back to the catalog
                    Product removedProduct = productArray[removeIndex];
                    int removedQuantity = removedProduct.getQuantity();

                    // Find the corresponding product in the catalog and update its quantity
                    for (Product catalogProduct : catalog.getProducts()) {
                        if (catalogProduct.getProductID() == removedProduct.getProductID()) {
                            catalogProduct.setQuantity(catalogProduct.getQuantity() + removedQuantity);
                            break;
                        }
                    }

                    // Shift elements to the left to remove the item from the cart
                    for (int i = removeIndex; i < productCounter - 1; i++) {
                        productArray[i] = productArray[i + 1];
                    }

                    // Set the last element to null since it’s now a duplicate
                    productArray[productCounter - 1] = null;

                    // Decrease the productCounter
                    productCounter--;

                    System.out.printf("%95s", "Removed successfully");
                    System.out.printf("\n%90s", "Press Enter to continue...");
                    input.nextLine();

                    clearScreen();
                } else {
                    System.out.printf("%85s", "Invalid item number");
                    System.out.printf("\n%90s", "Press Enter to continue...");
                    input.nextLine();
                    clearScreen();
                }

            } catch (Exception ex) {
                System.out.printf("\n%95s", "Please enter a valid number only");
                System.out.printf("\n%90s", "Press Enter to continue...");
                input.nextLine();
                input.nextLine();

                clearScreen();
            }

        } else {
            System.out.printf("%95s", "You don't have any item in your cart");
            System.out.printf("\n%90s", "Press Enter to continue...");
            input.nextLine();
            clearScreen();
        }
    }

    public void updateItem() {
        if (productCounter > 0) {
            while (true) {
                if (productCounter <= 0) {
                    break;
                }
                viewItem(); // Display the current cart items

                try {
                    System.out.printf("\n\n%85s\n", "Type 'no' to exit");
                    System.out.printf("%85s", "Enter the product option : ");
                    String userOption = input.next();
                    input.nextLine();

                    if (userOption.equalsIgnoreCase("no")) {
                        break;
                    }

                    int productIndex = Integer.parseInt(userOption);

                    if (productIndex > 0 && productIndex <= productCounter) {
                        System.out.printf("\n%90s", "Enter the quantity to update: ");
                        int itemQuantity = input.nextInt();
                        input.nextLine();

                        // Get the selected product by its index (adjusting for zero-based array)
                        Product selectedProduct = productArray[productIndex - 1];

                        // Calculate the new quantity
                        int newQuantity = selectedProduct.getQuantity() - itemQuantity;

                        if (newQuantity <= 0) {
                            // Remove the item from the cart if the quantity is zero or less
                            for (int k = productIndex - 1; k < productCounter - 1; k++) {
                                productArray[k] = productArray[k + 1]; // Shift elements to the left
                            }

                            // Set the last element to null since it was shifted
                            productArray[productCounter - 1] = null;

                            // Decrease the product counter
                            productCounter--;

                            // Update the catalog to add back the full quantity of this product
                            for (Product catalogProduct : catalog.getProducts()) {
                                if (catalogProduct.getProductID() == selectedProduct.getProductID()) {
                                    catalogProduct.setQuantity(catalogProduct.getQuantity() + selectedProduct.getQuantity());
                                    break;
                                }
                            }

                            System.out.printf("\n%115s", "Product " + selectedProduct.getProductName() + " has been removed from your cart as the quantity is now 0.");
                            System.out.printf("\n%90s", "Press Enter to continue...");
                            input.nextLine();
                            clearScreen();
                        } else {
                            // Update the quantity in the cart
                            selectedProduct.setQuantity(newQuantity);

                            // Update the catalog quantity correctly
                            for (Product catalogProduct : catalog.getProducts()) {
                                if (catalogProduct.getProductID() == selectedProduct.getProductID()) {
                                    catalogProduct.setQuantity(catalogProduct.getQuantity() + itemQuantity);
                                    break;
                                }
                            }
                            System.out.printf("\n%100s", "The quantity of " + selectedProduct.getProductName() + " has been updated.");
                            System.out.printf("\n%90s", "Press Enter to continue...");
                            input.nextLine();
                            clearScreen();
                        }

                    } else {
                        System.out.printf("\n%90s", "Please enter a valid product index.");
                        System.out.printf("\n%90s", "Press Enter to continue...");
                        input.nextLine();
                        input.nextLine();

                        clearScreen();
                    }

                } catch (Exception e) {
                    System.out.printf("\n%90s", "Please enter a valid number.");
                    input.nextLine(); // Clear the input buffer
                    input.nextLine(); // Clear the input buffer

                }
            }
        } else {
            System.out.printf("%95s", "You don't have any item in your cart");
            System.out.printf("\n%90s", "Press Enter to continue...");
            input.nextLine();
            clearScreen();
        }
    }

    public void clearProductArray() {
        Arrays.fill(productArray, null);
        productCounter = 0;
    }

    public double calcTotalItemFee(Product[] productArray) {
        double totalItemFee = 0.0;
        for (Product product : productArray) {
            if (product != null) {  // Check for null before accessing product
                totalItemFee += product.getQuantity() * product.getPrice();
            }
        }
        return totalItemFee;
    }

    public void checkProductArray() {
        System.out.println("Checking product array:");
        for (int i = 0; i < productArray.length; i++) {
            if (productArray[i] != null) {
                System.out.println("Product " + (i + 1) + ": " + productArray[i].getProductName()
                        + ", Quantity: " + productArray[i].getQuantity()
                        + ", Price: " + productArray[i].getPrice());
            } else {
                System.out.println("Product " + (i + 1) + ": null entry.");
            }
        }
    }

    public double calcTotalItemWeight() {

        double totalWeight = 0.0;
        double totalVolumetricWeight = 0.0;
        double quantity = 0;
        // Loop through the productArray to calculate total weight and volumetric weight
        for (Product product : productArray) {
            if (product != null) {
                // Sum up the total weight of all products
                totalWeight += (product.getWeight() * product.getQuantity());
                // Calculate volumetric weight for each product
                double volumetricWeight = (product.getLength() * product.getWidth() * product.getHeight()) / 5000;
                totalVolumetricWeight += volumetricWeight * product.getQuantity();
            }
        }

        // Use the higher of the two weights
        double finalWeight = Math.max(totalWeight, totalVolumetricWeight);

        return finalWeight;

    }

    public double calculateTaxFee(double totalItemFee, double deliveryFee) {
        final double SST = 0.06;
        double taxFee;
        double totalFee = totalItemFee + deliveryFee;
        taxFee = (totalFee * SST);
        return taxFee;
    }

    public double calculateTotalFee(double totalItemFee, double totalDeliveryFee, double taxFee) {

        return totalItemFee + totalDeliveryFee + taxFee;
    }

    public String[] getDeliveryInfo() {

        String[] deliveryInfoArray = new String[3];
        System.out.printf("%100s\n", "===========================================");
        System.out.printf("%100s\n", "               Delivery Info               ");
        System.out.printf("%100s\n", "===========================================");
        System.out.print("\n");

        System.out.printf("%85s", "Enter recipient name: ");
        deliveryInfoArray[0] = input.nextLine().trim(); // Using nextLine() to capture full input

        // Phone number validation
        boolean isValidPhone = false;
        while (!isValidPhone) {
            System.out.printf("%90s", "Enter recipient phone number: ");
            deliveryInfoArray[1] = input.nextLine().trim();

            // Validate phone number format: Only digits, and length between 10 to 15
            if (deliveryInfoArray[1].matches("\\d{10,15}")) {
                isValidPhone = true; // Valid phone number
            } else {
                System.out.printf("%90s\n", "Invalid phone number.");
                System.out.printf("%105s\n", "Please enter a valid phone number (10 to 15 digits).");

            }
        }

        System.out.printf("%90s", "Enter recipient address: ");
        deliveryInfoArray[2] = input.nextLine().trim();

        System.out.printf("%90s\n", "Delivery enter sucessfully");
        System.out.printf("%90s\n", "Press enter continue...");
        input.nextLine().trim();

        return deliveryInfoArray;
    }

    public void displayDelivryInfo(String[] deliveryInfoArray) {
        System.out.printf("%115s\n", "=======================================================================");
        System.out.printf("%115s\n", "                          Delivery Information                         ");
        System.out.printf("%115s\n", "=======================================================================");
        System.out.print("\n");

        System.out.printf("%45s%s%s \n", "", "Recipient Name        :", deliveryInfoArray[0]);
        System.out.printf("%45s%s%s \n", "", "Recipient Phone Number:", deliveryInfoArray[1]);
        System.out.printf("%45s%s%s \n", "", "Recipient Address     :", deliveryInfoArray[2]);

    }

    public void displayReceipt(double itemFee, double deliveryFee, double taxFee, double subtotalFee, String paymentMethod, Product[] productArray, double promoFee, int pointEarn) {
        // Display the header of the receipt
        clearScreen();

        System.out.printf("%115s\n", "=======================================================================");
        System.out.printf("%115s\n", "                                Receipt                                ");
        System.out.printf("%115s\n", "=======================================================================");
        System.out.print("\n");

        // Display product details
        System.out.printf("%115s\n", "=========================== Product List ==============================");
        System.out.printf("%115s\n", "No   Product Name                                  Price (RM)  Quantity");
        System.out.printf("%115s\n", "=======================================================================");

        for (int i = 0; i < productCounter; i++) {
            Product product = productArray[i];
            System.out.printf("%45d", (i + 1));
            System.out.print(" ");
            System.out.printf("%-50s", product.getProductName());
            System.out.printf("%10.2f", product.getPrice());
            System.out.printf("%10d\n", product.getQuantity());

        }

        System.out.printf("%115s\n", "=======================================================================");

        // Display fees and total
        System.out.printf("%103s %10.2f\n", "Item Fee:", itemFee);
        System.out.printf("%103s %10.2f\n", "Delivery Fee:", deliveryFee);
        System.out.printf("%103s %10.2f\n", "Tax Fee:", taxFee);
        System.out.printf("%103s %10.2f\n", "Promo Fee:", promoFee);
        System.out.printf("%103s %10.2f\n", "Subtotal:", subtotalFee);
        System.out.printf("%103s %s\n", "Payment Method:", paymentMethod);
        System.out.printf("%103s %10.2f\n", "Point Earn:", (double) pointEarn);
        System.out.printf("%115s\n", "=======================================================================");

        // Prompt to continue
        System.out.printf("%90s\n", "Press enter to continue...");
        Scanner input = new Scanner(System.in); // Ensure input is defined
        input.nextLine(); // Wait for user to press enter
    }

    public void displayRoughReceipt(double itemFee, double deliveryFee, double taxFee, double subtotalFee, String paymentMethod, Product[] productArray) {
        // Display the header of the receipt

        clearScreen();
        logo();
        System.out.printf("%115s\n", "=======================================================================");
        System.out.printf("%115s\n", "                                Summary                                ");
        System.out.printf("%115s\n", "=======================================================================");
        System.out.print("\n");

        // Display product details
        System.out.printf("%115s\n", "=========================== Product List ==============================");
        System.out.printf("%115s\n", "No   Product Name                                  Price (RM)  Quantity");
        System.out.printf("%115s\n", "=======================================================================");

        for (int i = 0; i < productCounter; i++) {
            Product product = productArray[i];
            System.out.printf("%45d", (i + 1));
            System.out.print(" ");
            System.out.printf("%-50s", product.getProductName());
            System.out.printf("%10.2f", product.getPrice());
            System.out.printf("%10d\n", product.getQuantity());

        }

        System.out.printf("%115s\n", "=======================================================================");
        System.out.printf("%103s %10.2f\n", "Subtotal:", subtotalFee);
        System.out.printf("%115s\n", "=======================================================================");

        // Prompt to continue
        System.out.printf("%90s\n", "Press enter to continue...");
        Scanner input = new Scanner(System.in); // Ensure input is defined
        input.nextLine(); // Wait for user to press enter
    }

    public static void logo() {

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

    public static void clearScreen() {
        for (int i = 0; i < 50; i++) { // Print 50 blank lines
            System.out.println();
        }

    }

    @Override
    public String deliverySelection(double itemTotalWeight) {

        // Set weight threshold for bulky delivery
        double bulkyThreshold = 30.0; // Example: assume bulky delivery for total weight > 30kg
        String deliveryOption;
        boolean isValid = false; // Flag to check validity

        // Display delivery options
        do {
            clearScreen();
            logo();
            if (itemTotalWeight > bulkyThreshold) {
                System.out.printf("%100s\n", "===========================================");
                System.out.printf("%100s\n", "               Delivery Option             ");
                System.out.printf("%100s\n", "===========================================");
                System.out.print("\n");

                System.out.printf("%70s %s\n", "Item weight =", itemTotalWeight + "kg");
                System.out.print("\n");
                System.out.printf("%70s %-2s%15s\n", " ", "B. ", "Bulky delivery");
                System.out.printf("%70s %-2s%15s\n", " ", "P. ", "In-Store Pickup");
                System.out.printf("%100s\n", "===========================================");

                // Prompt for delivery option
                System.out.printf("%95s", "Enter the delivery option(B/P): ");
                deliveryOption = input.next();
                input.nextLine();

                // Check if the entered option is valid
                if (deliveryOption.equalsIgnoreCase("B") || deliveryOption.equalsIgnoreCase("P")) {
                    isValid = true;
                } else {
                    System.out.printf("%85s", "Invalid option.");
                    System.out.printf("\n%90s", "Press Enter to continue...");
                    input.nextLine();
                    clearScreen();
                }

            } else {
                clearScreen();
                logo();
                System.out.printf("%100s\n", "===========================================");
                System.out.printf("%100s\n", "               Delivery Option             ");
                System.out.printf("%100s\n", "===========================================");
                System.out.print("\n");
                System.out.printf("%70s %.2f kg\n", "Item weight =", itemTotalWeight);

                //System.out.printf("%70s %s\n", "Item weight =", itemTotalWeight + "kg");
                System.out.printf("%100s\n", "Express delivery(Addtional CHARGE = RM 20)");

                System.out.print("\n");
                System.out.printf("%70s %-2s%15s\n", " ", "S. ", "Standard delivery");
                System.out.printf("%70s %-2s%15s\n", " ", "E. ", "Express delivery");
                System.out.printf("%70s %-2s%15s\n", " ", "P. ", "In-Store Pickup");

                // Prompt for delivery option
                System.out.printf("%95s", "Enter the delivery option(S/E/P): ");
                deliveryOption = input.next();
                input.nextLine();

                // Check if the entered option is valid
                if (deliveryOption.equalsIgnoreCase("S") || deliveryOption.equalsIgnoreCase("E") || deliveryOption.equalsIgnoreCase("P")) {
                    isValid = true;
                } else {
                    System.out.printf("%85s", "Invalid option.");
                    System.out.printf("\n%90s", "Press Enter to continue...");
                    input.nextLine();
                    clearScreen();
                }
            }
        } while (!isValid); // Repeat until a valid option is entered

        // Return the selected delivery option
        return deliveryOption.toUpperCase(); // Return the delivery option in uppercase for consistency
    }

    @Override
    public double standardDeliveryFeeCalc(double itemTotalWeight) {
        double deliveryFee = 0.0;
        double basicDeliveryFee = 5.0;

        deliveryFee = (itemTotalWeight * 2.0) + basicDeliveryFee;

        return deliveryFee;
    }

    @Override
    public double bulkyDeliveryFeeCalc(double itemTotalWeight) {
        double deliveryFee = 0.0;
        double basicDeliveryFee = 10.0;

        deliveryFee = (itemTotalWeight * 5.0) + basicDeliveryFee;

        return deliveryFee;
    }
}
