/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignmentoop;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author marcu
 */
public class TouchNGoPayment extends PaymentMethod {

    private static final String[] phoneNumber = {"0111111111", "0122222222", "0123456789", "0124353543"};
    private static double[] amount = {100000, 200, 4000, 70000};
    private String userNumber;

    public TouchNGoPayment(String userNumber) {
        this.userNumber = userNumber;
    }

    public static String[] getPhoneNumber() {
        return phoneNumber;
    }

    public static double[] getAmount() {
        return amount;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public static void setAmount(double[] amount) {
        TouchNGoPayment.amount = amount;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    @Override
    public String toString() {
        return "TouchNGoPayment{" + "userNumber=" + userNumber + '}';
    }

    @Override
    public boolean ValidatePaymentMethod() {
        boolean tngUserValidStatus = false;
        Scanner input = new Scanner(System.in);
        int otp = 0, userOtp = 0;
        boolean validatePhoneNumberStatus = validatePhoneNumber(userNumber);
        if (validatePhoneNumberStatus) {

            otp = generateOtpCode();
            try {
                System.out.printf("\n%85s", "Enter OTP number: ");
                userOtp = input.nextInt();
                input.nextLine();

            } catch (Exception ex) {
                System.out.printf("\n%85s", "Enter number only. ");
                System.out.printf("\n%90s", "Press Enter to continue...");
                input.nextLine();

            }
            if (otp != userOtp) {
                System.out.printf("\n%85s", "The OTP you entered is wrong");
                System.out.printf("\n%90s", "Press Enter to continue...");
                input.nextLine();
                return false;
            } else {
                return true;
            }

        }
        return tngUserValidStatus;
    }

    @Override
    public boolean MakePayment(double totalPrice) {
        for (int i = 0; i < phoneNumber.length; i++) {
            if (userNumber.equals(phoneNumber[i])) {
                if (amount[i] >= totalPrice) {
                    amount[i] -= totalPrice;
                    setAmount(amount);
                    System.out.printf("\n%85s", "Remaining Payment : " + amount[i]);
                    return false;
                }
            }
        }
        return true;
    }

    public boolean validatePhoneNumber(String userNumber) {
        //count length
        if (!(userNumber.length() == 10 || userNumber.length() == 11)) {
            System.out.printf("\n%90s", "Phone number must be 10 or 11 digits");

            return false;
        }
        for (int i = 0; i < userNumber.length(); i++) {
            if (!Character.isDigit(userNumber.charAt(i))) {
                System.out.printf("\n%90s", "Phone number does not contain characters");
                return false;
            }
        }
        for (int i = 0; i < phoneNumber.length; i++) {
            if (userNumber.equals(phoneNumber[i])) {
                return true;
            }

        }
        System.out.printf("\n%85s", "Phone number does not exits");
        return false;
    }

    public int generateOtpCode() {
        Random rand = new Random();
        int otp = rand.nextInt(999999 - 100000 + 1) + 100000;
        System.out.printf("\n%85s", "OTP is " + otp);
        return otp;
    }
}
