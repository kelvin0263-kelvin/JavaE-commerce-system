package com.mycompany.assignmentoop;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Stream;

public class Catalog {

    // Array product
    private List<Product> products;

    public Catalog() {
        this.products = new ArrayList<>();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Catalog{" + "products=" + products + '}';
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> searchProducts(String query) {
        List<Product> results = new ArrayList<>();
        for (Product product : products) {
            if (product.getProductName().toLowerCase().contains(query.toLowerCase())) {
                results.add(product);
            }
        }
        return results;
    }

    public List<Product> getFilterProducts(String productCategory) {
        List<Product> filterProduct = new ArrayList<>();

        for (Product product : products) {
            if (product instanceof Electronics && productCategory.equalsIgnoreCase("Electronics")) {
                filterProduct.add(product);
            } else if (product instanceof Fashion && productCategory.equalsIgnoreCase("Fashion")) {
                filterProduct.add(product);
            } else if (product instanceof Furniture && productCategory.equalsIgnoreCase("Furniture")) {
                filterProduct.add(product);

            }
        }

        return filterProduct;
    }

    public void disPlayCatalog(String displayOption) {
        Scanner input = new Scanner(System.in);
        int counter = 0;

        switch (displayOption) {
            case "1":
                System.out.printf("%100s\n", "================== Product List ====================");
                System.out.printf("%-5s%-25s%-15s%-10s\n", "No", "Product Name", "Price (RM)", "Quantity");
                System.out.printf("%100s\n", "====================================================");

                for (Product items : products) {
                    System.out.printf("%-5d", (counter + 1)); // Display the product number
                    System.out.printf("%-25s", items.getProductName()); // Display the product name
                    System.out.printf("%-15.2f", items.getPrice()); // Display the product price with 2 decimal points
                    System.out.printf("%-10d\n", items.getQuantity()); // Display the product quantity
                    counter += 1; // Increment the counter
                }
                System.out.printf("%100s\n", "====================================================");

                break;

            case "2":
                System.out.println("Electronics");
                System.out.println("Fashion");
                System.out.println("Cloting");

                System.out.println("Enter your option : ");
                String userOption = input.next();

                Class<?> categoryClass = null;

                switch (userOption) {
                    case "1":
                        categoryClass = Electronics.class;
                        break;
                    case "2":
                        categoryClass = Fashion.class;
                        break;
                    case "3":
                        categoryClass = Furniture.class;
                        break;
                    default:
                        System.out.println("Invalid option.");
                        return;
                }

                System.out.printf("%4s%25s%10s%10s\n", "No", "Product Name", "Price", "Quantity");
                for (Product product : products) {
                    if (categoryClass.isInstance(product)) {
                        System.out.printf("%4d%25s%10.4f%10d\n", (counter + 1), product.getProductName(), product.getPrice(), product.getQuantity());
                        counter++;
                    }
                }
                break;

            case "3":
                System.out.println("Please enter the product name : ");
                userOption = input.next().toLowerCase().replace(" ", "");
                System.out.printf("%10s%15s%10s%10s\n", "Product No", "Name", "Price", "Quantity");

                for (Product product : products) {
                    if (product.getProductName().toLowerCase().replace(" ", "").contains(userOption)) {
                        System.out.printf("%10d", (counter + 1));
                        System.out.printf("%15s", product.getProductName());
                        System.out.printf("%10.4f", product.getPrice());
                        System.out.printf("%10d\n", product.getQuantity());
                        counter += 1;
                    }
                }
                break;

        }

    }

    public boolean checkStock(int productIndex, int productQuantity) {

        if (productIndex > 0 && productIndex < products.size() && products.get(productIndex - 1).getQuantity() >= productQuantity) {
            return true;
        } else {
            return false;
        }

    }

    public void UpdateProduct(int productIndex, int productQuantity) {

        int currentQuantity = products.get(productIndex - 1).getQuantity();
        System.out.println("cuurent amount is : " + currentQuantity);
        products.get(productIndex - 1).setQuantity(currentQuantity - productQuantity);

    }

    public Product findProductById(String productId) {
        for (Product product : products) {
            if (product.getProductID().equalsIgnoreCase(productId)) {
                return product;
            }
        }
        return null;
    }
}
