package com.mycompany.assignmentoop;

import static com.mycompany.assignmentoop.Tester.clearScreen;
import static com.mycompany.assignmentoop.Tester.logo;
import java.util.Scanner;

/**
 *
 *
 * @author marcu
 */
public class TestPaymentMethod {

    public TestPaymentMethod() {
    }

    public static void displayPaymenttMenu() {

        clearScreen();
        logo();
        System.out.printf("%100s\n", "===========================================");
        System.out.printf("%100s\n", "               Payment Menu                ");
        System.out.printf("%100s\n", "===========================================");
        System.out.printf("%60s %-2s%15s\n", " ", "1. ", "TNG payment");
        System.out.printf("%60s %-2s%15s\n", " ", "2. ", "Fpx Payment");
        System.out.printf("%60s %-2s%15s\n", " ", "3. ", "Card Payment");
        System.out.printf("%60s %-2s%15s\n", " ", "4. ", "Back to Order");
        System.out.printf("%100s\n", "===========================================");

    }

    public static boolean cardPayment(double totalPrice) {
        Scanner input = new Scanner(System.in);
        boolean cardValid,
                cardPaymentStatus;
        String expiryDate;
        String DebitcardNumber;
        String cardNumber = null;
        int cardOption = 0;
        boolean continueStatus = false;

        boolean cardCont;
        do {

            System.out.printf("%100s\n", "===========================================");
            System.out.printf("%100s\n", "                  Card Menu                ");
            System.out.printf("%100s\n", "===========================================");
            System.out.printf("%60s %-2s%15s\n", " ", "1. ", "Credit Card");
            System.out.printf("%60s %-2s%15s\n", " ", "2. ", "Debit Card");
            System.out.printf("%60s %-2s%15s\n", " ", "3. ", "Exit");
            System.out.printf("%100s\n", "===========================================");
//            System.out.print("Select Card Option\n"
//                    + "[1] Credit Card\n"
//                    + "[2] Debit Card\n"
//                    + "Choose card option (1 or 2): ");
            try {
                System.out.printf("\n%85s", "Enter your option: ");
                cardOption = input.nextInt();
                input.nextLine();
            } catch (Exception ex) {
                System.out.printf("%85s", "Invalid item number");
                System.out.printf("\n%90s", "Press Enter to continue...");
                input.nextLine();
            }

            switch (cardOption) {
                case 3:
                    return false;

            }

            if (cardOption != 1 && cardOption != 2) {
                System.out.printf("\n%85s", "Please type 1 or 2 only");
                break;
            }
            switch (cardOption) {
                case 1:
                    System.out.printf("%85s", "Enter 16-digit Credit Card Number: ");
                    cardNumber = input.next();
                    input.nextLine();
                    System.out.printf("%85s", "Enter Credit Card Expiry Date (DD/MM/YYYY): ");
                    expiryDate = input.nextLine();
                    PaymentMethod creditCardPayment = new CreditCardPayment(cardNumber, expiryDate);
                    cardValid = creditCardPayment.ValidatePaymentMethod();
                    if (cardValid) {
                        boolean confirmPayment = confirmPayment(totalPrice);
                        if (confirmPayment) {
                            //  cardPaymentStatus = creditCardPayment.MakePayment(totalPrice);
                            //   if (cardPaymentStatus == true) {
                            //      System.out.printf("\n85s", "Payment Successful");
                            return true;
                            //  } else {
                            //      System.out.printf("\n%85s", "Payment Failed");
                            //      return false;
                            //    }
                        }

                    } else {
                        continueStatus = askToContinue();
                    }
                    break;
                case 2:
                    System.out.printf("%85s", "Enter 16-digit Debit Card Number: ");
                    DebitcardNumber = input.next();
                    input.nextLine();
                    PaymentMethod debitCardPayment = new DebitCardPayment(DebitcardNumber);
                    cardValid = debitCardPayment.ValidatePaymentMethod();
                    if (cardValid == true) {
                        boolean confirmPayment = confirmPayment(totalPrice);
                        if (confirmPayment == true) {
                            cardPaymentStatus = debitCardPayment.MakePayment(totalPrice);
                            if (cardPaymentStatus == true) {
                                System.out.printf("\n%85s\n", "Payment Successful");
                                System.out.printf("\n%90s", "Press Enter to continue...");
                                input.nextLine();
                                return true;
                            } else {
                                System.out.printf("\n%85s", "Insufficient Amount");
                                return false;
                            }
                        }

                    } else {
                        continueStatus = askToContinue();
                    }
                    break;

                default:
                    System.out.printf("\n%85s\n", "Please type 1 or 2 only");
                    continueStatus = askToContinue();
            }

        } while (continueStatus == true);
        return false;
    }

    public static boolean tngPayment(double totalPrice) {
        Scanner input = new Scanner(System.in);
        boolean tngValid, tngPaymentStatus;
        String phoneNum;
        boolean continueStatus = false;
        do {
            System.out.printf("%85s", "Enter Phone Number: ");
            phoneNum = input.nextLine();
            PaymentMethod tngPayment = new TouchNGoPayment(phoneNum);
            tngValid = tngPayment.ValidatePaymentMethod();

            if (tngValid == true) {
                tngPaymentStatus = tngPayment.MakePayment(totalPrice);
                //if tng paymentStatus is true
                if (tngPaymentStatus == false) {
                    System.out.printf("\n%85s\n", "Payment Successful");
                    System.out.printf("\n%90s", "Press Enter to continue...");
                    input.nextLine();
                    return true;
                } else {
                    System.out.printf("\n%85s\n", "Insufficient Balance");
                }

            } else {
                continueStatus = askToContinue();
            }
        } while (continueStatus == true);
        return false;
    }

    public static boolean fpxPayment(double totalPrice) {
        Scanner input = new Scanner(System.in);
        String userName, userPassword;
        boolean continueStatus = false;

        boolean fpxValid,
                fpxPaymentStatus;

        do {
            System.out.printf("\n%85s", "Enter username: ");
            userName = input.nextLine();
            System.out.printf("\n%85s", "Enter password: ");
            userPassword = input.nextLine();
            PaymentMethod fpxPayment = new FpxPayment(userName, userPassword);
            fpxValid = fpxPayment.ValidatePaymentMethod();
            if (fpxValid) {
                boolean comfirmPaymentStatus = confirmPayment(totalPrice);
                if (comfirmPaymentStatus) {
                    fpxPaymentStatus = fpxPayment.MakePayment(totalPrice);
                    if (!fpxPaymentStatus) {
                        System.out.printf("\n%85s\n", "Payment Successful");
                        System.out.printf("\n%90s", "Press Enter to continue...");
                        input.nextLine();
                        return true;
                    } else {
                        System.out.printf("\n%85s\n", "Insufficient Balance");
                        return false;
                    }

                }

            } else {
                continueStatus = askToContinue();
            }
        } while (continueStatus == true);
        return false;
    }

    public static boolean askToContinue() {
        String cont;
        Scanner input = new Scanner(System.in);
        do {
            System.out.printf("\n%85s", "Do you want to continue? :");
            cont = input.next().toUpperCase();
            input.nextLine();
            if (cont.equals("N")) {
                return false;
            } else if (cont.equals("Y")) {
                break;
            } else {
                System.out.printf("\n%85s", "Please type Y or N only");
            }
        } while (cont != "Y" || cont != "N");
        return true;
    }

    public static boolean confirmPayment(double totalPrice) {
        String confirmPayment;
        Scanner input = new Scanner(System.in);
        do {
            System.out.printf("\n%85s", "Do you want to confirm payment? :");
            confirmPayment = input.next().toUpperCase();
            if (confirmPayment.equals("Y")) {
                return true;
            } else if (confirmPayment.equals("N")) {
                return false;
            } else {
                System.out.printf("\n%85s", "Please type Y or N only");
            }
        } while (confirmPayment != "Y" || confirmPayment != "N");
        return false;
    }

}
