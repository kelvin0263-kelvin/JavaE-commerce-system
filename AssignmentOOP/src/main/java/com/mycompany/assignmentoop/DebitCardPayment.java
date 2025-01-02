/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignmentoop;

/**
 *
 * @author marcu
 */
public class DebitCardPayment extends PaymentMethod {

    private static final String[] cardNumber = {"4532148213909876L", "4485743219738815L", "6011234567891234L", "5500123456789010L"};
    private static double[] cardAmount = {200000, 300, 9000, 30000};
    private String userCardNumber;

    public DebitCardPayment(String userCardNumber) {
        this.userCardNumber = userCardNumber;
    }

    public static String[] getCardNumber() {
        return cardNumber;
    }

    public static void setCardAmount(double[] cardAmount) {
        DebitCardPayment.cardAmount = cardAmount;
    }

    public static double[] getCardAmount() {
        return cardAmount;
    }

    public String getUserCardNumber() {
        return userCardNumber;
    }

    public void setUserCardNumber(String userCardNumber) {
        this.userCardNumber = userCardNumber;
    }

    @Override
    public String toString() {
        return "DebitCardPayment{" + "userCardNumber=" + userCardNumber + '}';
    }

    @Override
    public boolean ValidatePaymentMethod() {
        for (int i = 0; i < cardNumber.length; i++) {
            if (userCardNumber.equals(cardNumber[i])) {
                return true;
            }
        }
        System.out.printf("\n%85s\n", "Card Number does not exist");
        return false;
    }

    @Override
    public boolean MakePayment(double totalPrice) {

        for (int i = 0; i < cardNumber.length; i++) {
            if (userCardNumber.equals(cardNumber[i])) {
                if (cardAmount[i] >= totalPrice) {
                    cardAmount[i] -= totalPrice;
                    setCardAmount(cardAmount);
                    return true;
                }
            }
        }
        System.out.printf("\n%85s\n", "Invalid Card Number");
        return false;
    }

}
