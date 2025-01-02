/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignmentoop;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marcu
 */
public class CreditCardPayment extends PaymentMethod {

    private String cardNumber;
    private String expiryDate;

    public CreditCardPayment(String cardNumber, String expiryDate) {
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public String toString() {
        return "CreditCardPayment{" + "cardNumber=" + cardNumber + ", expiryDate=" + expiryDate + '}';
    }
    
    @Override
    public boolean ValidatePaymentMethod() {
        boolean validateCardNumberStatus = false;
        validateCardNumberStatus = validateCardNumber(cardNumber);
        if (validateCardNumberStatus) {

            try {
                boolean validateExpiryDate = validateExpiryDate(expiryDate);
                if (validateExpiryDate) {
                    return true;
                }
            } catch (ParseException ex) {
                // Logger.getLogger(CreditCardPayment.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return false;
    }

    @Override
    public boolean MakePayment(double totalPrice) {
        return true;
    }

    public boolean validateCardNumber(String cardNumber) {
        // Check if the card number has exactly 16 digits
        int count = 0;
        //long temp = cardNumber;

//        // Count the number of digits in the card number
//        while (temp != 0) {
//            temp /= 10;
//            count++;
//        }
        // Check if the card number has exactly 16 digits
        if (cardNumber.length() == 16) {
            return true;
        } else {
            System.out.printf("\n%90s", "Card should have exactly 16 digits");
            return false;
        }
//        // Luhn Algorithm Implementation (assuming this is what you're doing)
//        int sum1 = 0, sum2 = 0;
//        temp = cardNumber;
//
//        // Calculate sum1 (sum of odd-placed digits from right)
//        for (int i = 1; i <= 8; i++) {
//            sum1 += temp % 10; // Add last digit to sum1
//            temp /= 100; // Skip next digit
//        }
//
//        // Reset temp to cardNumber and remove last digit for sum2 calculation
//        temp = cardNumber / 10;
//
//        // Calculate sum2 (sum of doubled even-placed digits from right)
//        for (int i = 1; i <= 8; i++) {
//            long digit = (temp % 10) * 2;
//            sum2 += (int) ((digit / 10) + (digit % 10)); // Add sum of digits of doubled value
//            temp /= 100; // Skip next digit
//        }
//
//        int finalSum = sum1 + sum2;
//
//        if (finalSum % 10 == 0) {
//            System.out.printf("\n%85s", "The credit card number is VALID");
//
//            return true; // Card number is valid
//        } else {
//            System.out.printf("\n%85s", "The credit card number is INVALID");
//            return false;
//        }
    }

    public boolean validateExpiryDate(String expiryDate) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date expireDate = dateFormat.parse(expiryDate);   //Convert to Date
        Date currentDate = new Date();
        if (!expireDate.after(currentDate)) {
            System.out.printf("\n%90s", "Card Expiry Date is INVALID");
            return false;
        }
        return true;
    }

}
