package com.mycompany.assignmentoop;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String name;
    private String email;
    private String password;
    private boolean isVerified;
    private String verificationCode;
    private List<Order> orderHistoryList = new ArrayList<>(); // Ensure this is initialized

    // Constructor
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.isVerified = false; // By default, the user is not verified

    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }

    public List<Order> getOrderHistoryList() {
        return orderHistoryList;
    }

    public void setOrderHistoryList(List<Order> orderHistoryList) {
        this.orderHistoryList = orderHistoryList;
    }

    public boolean isIsVerified() {
        return isVerified;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIsVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }

    @Override
    public String toString() {
        return "User{" + "name=" + name + ", email=" + email + ", password=" + password + ", isVerified=" + isVerified + ", verificationCode=" + verificationCode + ", orderHistoryList=" + orderHistoryList + '}';
    }

    
    // Method to generate a random 6-digit verification code
    public String generateVerificationCode() {
        return String.format("%06d", (int) (Math.random() * 1000000));
    }

    // Check if the email is valid (basic validation for demo purposes)
    public boolean isValidEmail() {
        return email.contains("@");
    }

    // Check if the input password matches the user's password
    public boolean checkPassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }

}
