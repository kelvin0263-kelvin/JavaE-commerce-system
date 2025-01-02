package com.mycompany.assignmentoop;

import static com.mycompany.assignmentoop.ShoppingCart.clearScreen;
import java.util.Scanner;

public class UserRegistration {

    private static final User[] users = new User[100]; // Static array to store up to 100 users
    private static int userCount = 0; // To track the number of registered users
    // Getters for external use

    public UserRegistration() {
    }

    public static void setUserCount(int userCount) {
        UserRegistration.userCount = userCount;
    }

    public static int getUserCount() {
        return userCount;
    }

    public static User[] getUsers() {
        return users;
    }

    // Method to register the user
    public static void register() {
        Scanner input = new Scanner(System.in);

        System.out.printf("%85s", "Enter your username: ");
        String name = input.nextLine().trim();

        if (name.trim().isEmpty()) {
            System.out.printf("\n%100s", "Username cannot be empty. Please try again."); // Error message
            System.out.printf("\n%90s", "Press Enter to continue...");
            input.nextLine();
            clearScreen();
            return;
        }

        // Check for duplicate username
        for (int i = 0; i < userCount; i++) {
            if (users[i].getName().equalsIgnoreCase(name)) { // Case-insensitive comparison
                System.out.printf("%95s\n", "Registration failed: Username already exists.");
                System.out.printf("%95s\n", " Please choose a different username.");
                System.out.printf("\n%90s", "Press Enter to continue...");
                input.nextLine();
                clearScreen();
                return;
            }
        }

        System.out.printf("%85s", "Enter your email: ");
        String email = input.nextLine().trim();

        System.out.printf("%85s", "Enter your password: ");
        String password = input.nextLine().trim();

        if (userCount >= users.length) {
            System.out.printf("%95s\n", "Registration failed: Maximum number of users reached.");
            System.out.printf("\n%90s", "Press Enter to continue...");
            input.nextLine();
            clearScreen();
            return;
        }

        // Create new User object
        User user = new User(name, email, password);

        // Validate email format (assuming a method exists in the User class)
        if (!user.isValidEmail()) {
            System.out.printf("%95s\n", "Invalid email format. Please try again.");
            System.out.printf("\n%90s", "Press Enter to continue...");
            input.nextLine();
            clearScreen();
            return;
        }

        // Send verification code and complete registration
        sendVerificationEmail(user);  // Simulate sending email verification
        if (verifyEmail(user, input)) {  // Verify email
            // Add user to the static array
            users[userCount] = user; // Ensure user is added at the correct position
            userCount++; // Increment after adding the user
            System.out.printf("%95s\n", "Registration successful! Welcome, " + user.getName());
            System.out.printf("\n%90s", "Press Enter to continue...");
            input.nextLine();

        } else {
            System.out.printf("%95s\n", "Registration failed: Email verification was not successful.");
            System.out.printf("\n%90s", "Press Enter to continue...");
            input.nextLine();
            clearScreen();
        }
    }

    // Method to simulate sending a verification email to the user
    private static void sendVerificationEmail(User user) {
        String verificationCode = user.generateVerificationCode();  // Generate code
        user.setVerificationCode(verificationCode);  // Set code for the user
        // Simulate sending an email by printing the verification code
        System.out.printf("%95s\n", "Verification code sent to " + user.getEmail() + ": " + verificationCode);
    }

    // Method to verify the email
    private static boolean verifyEmail(User user, Scanner input) {
        System.out.printf("%85s", "Enter verification code sent to your email: ");
        String code = input.nextLine().trim();  // Always use nextLine for full input and trim it

        if (code.equals(user.getVerificationCode())) {
            user.setVerified(true);  // Mark the user as verified
            System.out.printf("%90s\n", "Email verification successful!");
            System.out.printf("\n%90s", "Press Enter to continue...");
            input.nextLine();
            clearScreen();
            return true;
        } else {
            System.out.printf("%90s\n", "Invalid verification code. Please try again.");
            System.out.printf("\n%90s", "Press Enter to continue...");
            input.nextLine();
            clearScreen();
            return false;
        }
    }

    // Method to log in the user (after registration and email verification)
    public static void login() {
        Scanner input = new Scanner(System.in);

        System.out.printf("%85s", "Enter your username: ");
        String loginName = input.nextLine().trim();  // Always use nextLine for full input and trim it

        User user = findUserByName(loginName);
        if (user == null) {
            System.out.printf("%95s\n", "No user found with this username.");
            System.out.printf("\n%90s", "Press Enter to continue...");
            input.nextLine();

            clearScreen();
            return;
        }

        if (!user.isVerified()) {
            System.out.printf("%95s\n", "Your email is not verified. Please verify your email to log in.");
            System.out.printf("\n%90s", "Press Enter to continue...");
            input.nextLine();
            clearScreen();
            return;
        }

        System.out.printf("%85s", "Enter your password: ");
        String password = input.nextLine().trim();  // Always use nextLine for full input and trim it

        if (user.checkPassword(password)) {
            System.out.printf("%95s\n", "Login successful! Welcome, " + user.getName());
            System.out.printf("\n%90s", "Press Enter to continue...");
            input.nextLine();
            clearScreen();
        } else {
            System.out.printf("%95s\n", "Incorrect password. Login failed.");
            System.out.printf("\n%90s", "Press Enter to continue...");
            input.nextLine();
            clearScreen();
        }
    }

    // Method to find a user by name
    public static User findUserByName(String name) {
        for (int i = 0; i < userCount; i++) { // Loop only up to userCount
            if (users[i].getName().equals(name)) {
                return users[i];
            }
        }
        return null;
    }

    // Method to display all registered users
    public static void displayAllUsers() {
        System.out.println("Registered Users:");
        for (int i = 0; i < userCount; i++) {
            System.out.println(users[i].getName() + " - " + users[i].getEmail());
        }
    }

}
