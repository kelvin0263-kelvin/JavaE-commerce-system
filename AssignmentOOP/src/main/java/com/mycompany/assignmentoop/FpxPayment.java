/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignmentoop;

/**
 *
 * @author marcu
 */
public class FpxPayment extends PaymentMethod {

    private static final String[] accountName = {"Marcusmjy", "Tanck", "Michaelkcck", "Hosm"};
    private static final String[] accountPassword = {"1", "12345678", "abcd1234", "lmao9876"};
    private static double[] fpxAmount = {100000, 2000, 30000, 420000};
    private String userAccount, userPassword;

    //bank
    //useraccount,userpass
    //FpxAccount account[0] =new back(userAccount,userpas);
    //FpxAccount account[1] =new back(userAccount,userpas);
//    public FpxPayment(){
//        maybank = new Maybank[4];
//        
//        maybank[0] = new Maybank("Marcusmjy", "ambatukam", 10);
//        maybank[1] = new Maybank("Tanck", "12345678", 2000);
//        maybank[2] = new Maybank("Michaelkcck", "abcd1234", 30000);
//        maybank[3] = new Maybank("Hosm", "lmao9876", 420000);
//    }
    public FpxPayment(String userAccount, String userPassword) {
        this.userAccount = userAccount;
        this.userPassword = userPassword;
    }

    public static double[] getFpxAmount() {
        return fpxAmount;
    }

    public static void setFpxAmount(double[] fpxAmount) {
        FpxPayment.fpxAmount = fpxAmount;
    }

    public static String[] getAccountName() {
        return accountName;
    }

    public static String[] getAccountPassword() {
        return accountPassword;
    }

    public static double[] getAmount() {
        return fpxAmount;
    }

    public static void setAmount(double[] amount) {
        FpxPayment.fpxAmount = amount;
    }

    //Getter&Setter
    public String getUserAccount() {
        return userAccount;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public String toString() {
        return "FpxPayment{" + "userAccount=" + userAccount + ", userPassword=" + userPassword + '}';
    }

    @Override
    public boolean ValidatePaymentMethod() {
        //for loop
        //loop all the element inside the accountArray based on the parameter;
        for (int i = 0; i < accountName.length; i++) {

            //fpxaccount[i].getAccountNAme()
            //if  user input equals to the accountName, go to the next if condition
            if (userAccount.equals(accountName[i])) {
                //if equals to the password then only return false
                if (userPassword.equals(accountPassword[i])) {
                    return true;
                }
            }
        }
        System.out.printf("\n%85s", "Wrong Username or Password");
        return false;
    }

    @Override
    public boolean MakePayment(double totalPrice) {
        for (int i = 0; i < accountName.length; i++) {
            //if  user input equals to the accountName, go to the next if condition
            if (userAccount.equals(accountName[i])) {
                //if equals to the password then only return false
                if (userPassword.equals(accountPassword[i])) {
                    if (fpxAmount[i] >= totalPrice) {
                        fpxAmount[i] -= totalPrice;
                        setAmount(fpxAmount);
                        return false;
                    }
                }
            }
        }
        return true;
    }

}
