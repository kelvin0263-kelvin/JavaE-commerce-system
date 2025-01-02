/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignmentoop;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author 60164
 */

public class Account {
    private String name;
    private String email;
    private String password;
    private boolean isVerified;
    private boolean isMember; // Indicates if the user is a member
    private int points; // Points for members

    /**
     * Constructor to initialize the user details.
     * @param name The name of the user.
     * @param email The email of the user.
     * @param password The password of the user.
     */
    public Account(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.isVerified = false;  // Email verification starts as false
        this.isMember = false; // Not a member initially
        this.points = 0; // Initial points
    }

    /**
     * Registers the user by taking input for name, email, and password.
     * Sends a verification email upon successful registration.
     */
    public void register() {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter your name: ");
        name = input.nextLine();

        System.out.print("Enter your email: ");
        email = input.nextLine();

        while (!isValidEmail(email)) {
            System.out.print("Invalid email format. Please enter again: ");
            email = input.nextLine();
        }

        System.out.print("Enter your password: ");
        password = input.nextLine();

        while (!isValidPassword(password)) {
            System.out.print("Password must be at least 8 characters long and contain a number. Please enter again: ");
            password = input.nextLine();
        }

        System.out.println("Registration successful! A verification email has been sent to " + email);
        sendVerificationEmail();
    }

    /**
     * Simulates sending a verification email to the user.
     */
    private void sendVerificationEmail() {
        System.out.println("Please check your email to verify your account.");
    }

    /**
     * Verifies the email by asking the user to input the verification code.
     */
    public void verifyEmail() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter verification code sent to your email: ");
        String code = input.nextLine(); // Assume user enters the correct code for simplicity

        if (code.equals("123456")) { // Dummy verification code
            isVerified = true;
            System.out.println("Email verification successful!");
        } else {
            System.out.println("Invalid verification code.");
        }
    }

    /**
     * Updates user details such as name, email, and password if the email is verified.
     */
    public void updateUserDetails() {
        if (!isVerified) {
            System.out.println("Please verify your email before updating details.");
            return;
        }

        Scanner input = new Scanner(System.in);
        System.out.print("Enter new name: ");
        String newName = input.nextLine();

        System.out.print("Enter new email: ");
        String newEmail = input.nextLine();
        while (!isValidEmail(newEmail)) {
            System.out.print("Invalid email format. Please enter again: ");
            newEmail = input.nextLine();
        }

        System.out.print("Enter new password: ");
        String newPassword = input.nextLine();

        while (!isValidPassword(newPassword)) {
            System.out.print("Password must be at least 8 characters long and contain a number. Please enter again: ");
            newPassword = input.nextLine();
        }

        // Update details
        this.name = newName;
        this.email = newEmail;
        this.password = newPassword;

        System.out.println("User details updated successfully.");
    }

    /**
     * Logs in the user by verifying the email and password.
     * @return true if login is successful, false otherwise.
     */
    public boolean login() {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter your email: ");
        String inputEmail = input.nextLine();

        System.out.print("Enter your password: ");
        String inputPassword = input.nextLine();

        if (inputEmail.equals(email) && inputPassword.equals(password)) {
            if (isVerified) {
                System.out.println("Login successful! Welcome, " + name + ".");
                return true; // Login successful
            } else {
                System.out.println("Please verify your email before logging in.");
                return false; // Login failed due to unverified email
            }
        } else {
            System.out.println("Invalid email or password.");
            return false; // Login failed due to incorrect credentials
        }
    }

    /**
     * Allows the user to become a member.
     * Membership enables earning points.
     */
    public void becomeMember() {
        if (!isVerified) {
            System.out.println("Please verify your email before becoming a member.");
            return;
        }

        isMember = true;
        System.out.println(name + " is now a member! You can earn points on purchases.");
    }

    /**
     * Adds points to the user's account if they are a member.
     * @param pointsEarned The number of points earned from a purchase.
     */
    public void addPoints(int pointsEarned) {
        if (isMember) {
            points += pointsEarned;
            System.out.println(pointsEarned + " points added. Total points: " + points);
        } else {
            System.out.println("You need to be a member to earn points.");
        }
    }

    /**
     * Redeems the user's points.
     * @param pointsToRedeem The number of points to redeem.
     */
    public void redeemPoints(int pointsToRedeem) {
        if (pointsToRedeem <= points) {
            points -= pointsToRedeem;
            System.out.println(pointsToRedeem + " points redeemed. Remaining points: " + points);
        } else {
            System.out.println("Insufficient points to redeem.");
        }
    }

    /**
     * Validates the email format.
     * @param email The email address to validate.
     * @return true if the email format is valid, false otherwise.
     */
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return Pattern.matches(emailRegex, email);
    }

    /**
     * Validates the password strength.
     * @param password The password to validate.
     * @return true if the password meets the criteria, false otherwise.
     */
    private boolean isValidPassword(String password) {
        return password.length() >= 8 && password.matches(".\\d."); // Minimum length and must contain a number
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public boolean isMember() {
        return isMember;
    }

    public int getPoints() {
        return points;
    }
}