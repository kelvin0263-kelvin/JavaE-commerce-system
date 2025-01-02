package com.mycompany.assignmentoop;

abstract class Product {

    private String productID;
    private String productName;
    private int quantity;
    private double price;
    private double weight;
    private double width;
    private double height;
    private double length;

    public Product(String productID, String productName, int quantity, double price, double weight, double width, double height, double length) {
        this.productID = productID;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.weight = weight;
        this.width = width;
        this.height = height;
        this.length = length;
    }

    public String getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public abstract Product clone();

    public double getWeight() {
        return weight;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getLength() {
        return length;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    @Override
    public String toString() {
        return "Product{" + "productID=" + productID + ", productName=" + productName + ", quantity=" + quantity + ", price=" + price + ", weight=" + weight + ", width=" + width + ", height=" + height + ", length=" + length + '}';
    }

}
