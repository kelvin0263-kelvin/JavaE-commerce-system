package com.mycompany.assignmentoop;

import java.util.Scanner;

public class AdminLogin {

    private static final int MAX_ADMINS = 10;
    private static int adminCount = 3; // Initial count of pre-assigned admins

    // Static array with pre-assigned Admin objects
    private static final Admin[] admins = {
        new Admin("1", "superadmin@example.com", "1"),
        new Admin("AdminUser1", "admin1@example.com", "password1"),
        new Admin("AdminUser2", "admin2@example.com", "password2"),
        null, null, null, null, null, null, null // Remaining elements initialized to null
    };

    public static int getAdminCount() {
        return adminCount;
    }

    public static void setAdminCount(int adminCount) {
        AdminLogin.adminCount = adminCount;
    }

    public static int getMAX_ADMINS() {
        return MAX_ADMINS;
    }

    public static Admin[] getAdmins() {
        return admins;
    }

    // Method to handle admin login
    public static Admin loginAdmin() {
        Scanner input = new Scanner(System.in);
        int maxAttempts = 3; // Maximum allowed login attempts

        try {
            for (int attempt = 1; attempt <= maxAttempts; attempt++) {
                System.out.printf("%85s", "Enter your admin username: ");
                String loginName = input.nextLine();

                // Find the admin by the username
                Admin admin = findAdminByName(loginName);
                if (admin == null) {
                    System.out.printf("%90s\n", "No admin found with this username.");
                    if (attempt < maxAttempts) {
                        System.out.printf("%90s\n", "Please try again. Attempts left: " + (maxAttempts - attempt));
                    }
                    continue;
                }

                System.out.printf("%85s", "Enter your password: ");
                String password = input.nextLine();

                // Check if the entered password is correct
                if (admin.checkPassword(password)) {
                    System.out.printf("%85s\n", "Login successful!");
                    System.out.printf("%85s\n", "Welcome, " + admin.getName());
                    System.out.printf("\n%90s", "Press Enter to continue...");
                    input.nextLine().trim();
                    clearScreen();

                    return admin; // Return the logged-in admin instance
                } else {
                    System.out.printf("%90s\n", "Incorrect password. Login failed.");
                    System.out.printf("\n%90s", "Press Enter to continue...");
                    input.nextLine().trim();
                    clearScreen();
                    if (attempt < maxAttempts) {
                        System.out.printf("%90s\n", "Please try again. Attempts left: " + (maxAttempts - attempt));
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred during login: " + e.getMessage());
            System.out.printf("\n%90s", "Press Enter to continue...");
            input.nextLine().trim();
            clearScreen();
        }

        System.out.printf("%90s\n", "Maximum login attempts reached. Access denied.");
        System.out.printf("\n%90s", "Press Enter to continue...");
        input.nextLine().trim();
        clearScreen();
        return null; // Return null if login failed
    }

    // Helper method to find admin by username
    public static Admin findAdminByName(String name) {
        for (int i = 0; i < adminCount; i++) {
            if (admins[i] != null && admins[i].getName().equalsIgnoreCase(name)) {
                return admins[i];
            }
        }
        return null;
    }

    // Display all registered admins
    public static void displayAllAdmins() {
        System.out.println("Registered Admins:");
        for (int i = 0; i < adminCount; i++) {
            if (admins[i] != null) {
                System.out.println(admins[i].getName() + " - " + admins[i].getEmail());
            }
        }
    }

    public static void clearScreen() {
        for (int i = 0; i < 50; i++) { // Print 50 blank lines
            System.out.println();
        }
    }
}
