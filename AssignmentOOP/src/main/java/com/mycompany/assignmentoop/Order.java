/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignmentoop;

import java.util.Date;

public class Order {

    private Product[] productList;
    private double totalPrice;
    private String orderId;
    private Date orderDate;
    private String[] shippingAddress;
    private String paymentMethod;
    private static int orderCount = 0; // Static counter to track order count
    private String deliveryMethod;
    private int productAmount;

    // Constructor with current date and random orderId generation
    public Order(Product[] productList, double totalPrice, String[] shippingAddress, String paymentMethod, String deliveryMethod, int productAmount) {
        this.productList = productList;
        this.totalPrice = totalPrice;
        this.orderDate = new Date(); // Set current date as the order date
        this.shippingAddress = shippingAddress;
        this.paymentMethod = paymentMethod;
        this.orderId = generateOrderId(); // Generate random order ID
        this.deliveryMethod = deliveryMethod;
        orderCount++;
        this.productAmount = productAmount;
    }

    public Order(Product[] productList, double totalPrice, String deliveryMethod, String paymentMethod, int productAmount) {
        this.productList = productList;
        this.totalPrice = totalPrice;
        this.orderDate = new Date(); // Set current date as the order date
        this.shippingAddress = null; // Explicitly set to null if not needed
        this.paymentMethod = paymentMethod;
        this.orderId = generateOrderId(); // Generate random order ID
        this.deliveryMethod = deliveryMethod;
        orderCount++;
        this.productAmount = productAmount;

    }

    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    // Getters and Setters
    public Product[] getProductList() {
        return productList;
    }

    public void setProductList(Product[] productList) {
        this.productList = productList;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String[] getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String[] shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public static int getOrderCount() {
        return orderCount;
    }

    public int getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(int productAmount) {
        this.productAmount = productAmount;
    }

    public static void setOrderCount(int orderCount) {
        Order.orderCount = orderCount;
    }

    @Override
    public String toString() {
        return "Order{" + "productList=" + productList + ", totalPrice=" + totalPrice + ", orderId=" + orderId + ", orderDate=" + orderDate + ", shippingAddress=" + shippingAddress + ", paymentMethod=" + paymentMethod + ", deliveryMethod=" + deliveryMethod + ", productAmount=" + productAmount + '}';
    }

    // Method to generate random orderId using UUID
    private String generateOrderId() {
        return "ORD-" + (orderCount + 1);

    }

}
