package com.mycompany.assignmentoop;

import static com.mycompany.assignmentoop.Tester.clearScreen;
import static com.mycompany.assignmentoop.Tester.logo;
import java.util.Scanner;

public class Member extends User {

    Scanner input = new Scanner(System.in);

    private int points;
    private String birthday;
    private String[] promoCodes;      // Array to store promo codes
    private int[] discountPercentages; // Array to store discount percentages
    private int promoCount;            // Counter for the number of promo codes

    public Member(String name, String email, String password) {
        super(name, email, password);
        this.points = 0;
        this.promoCodes = new String[10];         // Limit the member to hold up to 10 promo codes
        this.discountPercentages = new int[10];   // Parallel array to hold discount percentages
        this.promoCount = 0;                      // Initialize the promo count
    }

    // Getters and setters
    public int getPoints() {
        return points;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String[] getPromoCodes() {
        return promoCodes;
    }

    public int[] getDiscountPercentages() {
        return discountPercentages;
    }

    public int getPromoCount() {
        return promoCount;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setPromoCodes(String[] promoCodes) {
        this.promoCodes = promoCodes;
    }

    public void setDiscountPercentages(int[] discountPercentages) {
        this.discountPercentages = discountPercentages;
    }

    public void setPromoCount(int promoCount) {
        this.promoCount = promoCount;
    }

    @Override
    public String toString() {
        return "Member{" + "points=" + points + ", birthday=" + birthday + ", promoCodes=" + promoCodes + ", discountPercentages=" + discountPercentages + ", promoCount=" + promoCount + '}';
    }

    // Method to earn points based on purchase
    public void earnPoints(int purchaseAmount) {
        int pointsEarned = purchaseAmount / 10;
        this.points += pointsEarned;
        //  System.out.println("You have earned " + pointsEarned + " points! Total points: " + points);
    }

    // Method to redeem points for promo code
    public void redeemPromoCode() {
        int choice = 0;
        Scanner input = new Scanner(System.in);

        clearScreen();
        logo();
        System.out.printf("%100s\n", "===========================================");
        System.out.printf("%100s\n", "               Promo Code Option           ");
        System.out.printf("%100s\n", "===========================================");
        System.out.printf("%60s %-2s%15s\n", " ", "1. ", "5% discount (costs 50 points)");
        System.out.printf("%60s %-2s%15s\n", " ", "2. ", "10% discount (costs 100 points)");
        System.out.printf("%60s %-2s%15s\n", " ", "3. ", "15% discount (costs 150 points)");
        System.out.printf("%100s\n", "===========================================");

        try {
            System.out.printf("\n%90s", "Enter your option: ");
            choice = input.nextInt();
            input.nextLine();

        } catch (Exception ex) {
            System.out.printf("%90s\n", "Please enter number only");
            System.out.printf("%90s", "Press Enter to continue...");
            input.nextLine();
            input.nextLine();

            return;
        }
        int requiredPoints = 0;
        int discountPercentage = 0;

        switch (choice) {
            case 1:
                requiredPoints = 50;
                discountPercentage = 5;
                break;
            case 2:
                requiredPoints = 100;
                discountPercentage = 10;
                break;
            case 3:
                requiredPoints = 150;
                discountPercentage = 15;
                break;
            default:
                System.out.printf("\n%90s", "Invalid option.");
                System.out.printf("%80s", "Press Enter to continue...");
                input.nextLine();
                return;
        }

        if (points >= requiredPoints) {
            points -= requiredPoints; // Deduct points
            String promoCode = generatePromoCode();
            addPromoCode(promoCode, discountPercentage);
            System.out.printf("\n%100s", "Promo code redeemed: " + promoCode + " for a " + discountPercentage + "% discount.");
            System.out.printf("\n%90s", "Press Enter to continue...");
            input.nextLine();
        } else {
            System.out.printf("\n%90s", "Not enough points to redeem this promo code.");
            System.out.printf("%80s", "Press Enter to continue...");
            input.nextLine();
        }
    }
    // Method to generate a promo code

    private String generatePromoCode() {
        return String.valueOf((int) (Math.random() * 900000) + 100000); // Generate a 6-digit promo code
    }

    // Method to store a promo code and its discount percentage
    private void addPromoCode(String promoCode, int discountPercentage) {

        if (promoCount < promoCodes.length) {
            promoCodes[promoCount] = promoCode;
            discountPercentages[promoCount] = discountPercentage;
            promoCount++;
        } else {
            System.out.printf("\n%90s", "You have reached the maximum number of promo codes.");
            System.out.printf("%80s", "Press Enter to continue...");
            input.nextLine();
        }
    }

    private void removePromoCode(String promoCode) {
        boolean promoFound = false;

        // Find the promo code in the array
        for (int i = 0; i < promoCount; i++) {
            if (promoCodes[i].equalsIgnoreCase(promoCode)) {
                promoFound = true;

                // Shift elements to the left to fill the gap
                for (int j = i; j < promoCount - 1; j++) {
                    promoCodes[j] = promoCodes[j + 1];
                    discountPercentages[j] = discountPercentages[j + 1];
                }

                // Clear the last element
                promoCodes[promoCount - 1] = null;
                discountPercentages[promoCount - 1] = 0;

                promoCount--; // Decrease promo count
                System.out.printf("\n%100s\n", "Promo code '" + promoCode + "' has been successfully removed.");
                return; // Exit the method after removing the promo code
            }
        }

        // If the promo code is not found
        if (!promoFound) {
            System.out.printf("\n%90s\n", "Promo code '" + promoCode + "' not found.");
        }
    }

    // Method to apply a promo code to the subtotal
    public double applyPromoCode(String enteredPromoCode, double subTotal) {
        for (int i = 0; i < promoCount; i++) {
            if (promoCodes[i].equals(enteredPromoCode)) {
                double discountAmount = subTotal * discountPercentages[i] / 100.0;
                System.out.printf("\n%90s", "Promo code applied. Discount: " + discountPercentages[i] + "%.");
                System.out.printf("\n%85s", "Press Enter to continue...");
                input.nextLine();
                removePromoCode(enteredPromoCode);
                return subTotal - discountAmount;
            }
        }
        System.out.printf("\n%90s", "Invalid promo code.");

        return subTotal;  // No discount applied
    }

}
