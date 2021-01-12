package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        BankBalance bankBalance = new BankBalance();
        System.out.println("Enter your username");
        Scanner scanner = new Scanner(System.in);
        bankBalance.customerDetails(scanner.next());
    }
}
class BankBalance{
    int lastTransaction;
    int balance;
    int AliceBalance;
    int AliceLastTransaction;
    int BobBalance;
    int BobLastTransaction;
    int AliceOwing;
    int BobOwing;
    int owing;
    String customerName;

    void customerDetails(String cName){
        if(cName.equals("Alice")){
            customerName = cName;
            System.out.println("Hello, "+customerName);
            if (BobOwing >0){
                System.out.println("Owing "+BobOwing+" from Bob.");
            }
            if (AliceOwing >0){
                System.out.println("Owing "+AliceOwing+" to Bob.");
            }
            balance = AliceBalance;
            owing = AliceOwing;
            lastTransaction = AliceLastTransaction;
            menu();
        }
        else if(cName.equals("Bob")){
            customerName = cName;
            System.out.println("Hello, "+customerName);
            if (BobOwing >0){
                System.out.println("Owing "+BobOwing+" to Alice.");
            }
            if (AliceOwing >0){
                System.out.println("Owing "+AliceOwing+" from Alice.");
            }
            balance = BobBalance;
            owing = BobOwing;
            lastTransaction = BobLastTransaction;
            menu();
        }
        else{
            System.out.println("Sorry invalid user");
        }
    }

    void deposit(int amount){
        if (amount > 0){
            switch (customerName){
                case "Bob":
                    if (BobOwing==0){
                        balance = balance+amount;
                        lastTransaction = amount;
                    }
                    else {
                        if (amount >=BobOwing){
                            int temval = amount-BobOwing;
                            AliceBalance = AliceBalance + BobOwing;
                            balance = balance+temval;
                            BobOwing = 0;
                        }
                        else{
                            BobOwing = BobOwing- amount;
                            AliceBalance = AliceBalance + amount;
                            System.out.println("Owing "+BobOwing+" to Alice.");
                        }
                        lastTransaction = amount;
                    }
                    break;
                case "Alice":
                    if (AliceOwing==0){
                        balance = balance+amount;
                        lastTransaction = amount;
                    }
                    else {
                        if (amount >=AliceOwing){
                            int temval = amount-AliceOwing;
                            BobBalance = BobBalance + AliceOwing;
                            balance = balance+temval;
                            AliceOwing = 0;
                        }
                        else{
                            AliceOwing = AliceOwing- amount;
                            BobBalance = BobBalance + amount;
                            System.out.println("Owing "+AliceOwing+" to Alice.");
                        }
                        lastTransaction = amount;
                    }
                    break;
            }
        }
    }
    void withdraw(int amount){
        if (amount > 0){
            balance = balance-amount;
            lastTransaction = -amount;
        }
    }
    void getPreviousTransaction(){
        if (lastTransaction>0){
            System.out.println("Deposit: "+lastTransaction);
        }
        else if (lastTransaction < 0){
            System.out.println("Withdraw: "+lastTransaction);
        }
        else {
            System.out.println("No Transaction happened");
        }
    }
    void transferMoney(int amonut,String cName){
        if(amonut>0){
            switch (cName){
                case "Alice":
                    if (balance>= amonut){
                        if (AliceOwing>0){
                            int b = amonut - AliceOwing;
                            if (b >= 0){
                                AliceBalance = AliceBalance +b;
                                System.out.println("Transferred "+amonut+" to Alice.");
                                System.out.println("Balance: " + balance);
                            }
                            else {
                                AliceOwing = AliceOwing -amonut;
                                System.out.println("Transferred "+amonut+" to Alice.");
                                System.out.println("Owing "+AliceOwing+" from Alice.");
                                System.out.println("Balance: " + balance);
                            }
                        }
                        else{
                            balance = balance-amonut;
                            System.out.println("Transferred "+amonut+" to Alice.");
                            System.out.println("Balance: " + balance);
                            AliceBalance = AliceBalance+ amonut;
                        }
                    }
                    else {
                        int tempO = amonut - balance;
                        BobOwing = BobOwing + tempO;
                        System.out.println("Transferred "+balance+" to Alice.");
                        AliceBalance = AliceBalance + balance;
                        balance = 0;
                        System.out.println("Owing "+BobOwing+" to Alice.");
                    }
                    break;
                case "Bob":
                    if (balance>= amonut){
                        if (BobOwing>0){
                            int b = amonut - BobOwing;
                            if (b >= 0){
                                BobBalance = BobBalance +b;
                                System.out.println("Transferred "+amonut+" to Bob.");
                                System.out.println("Balance: " + balance);
                            }
                            else {
                                BobOwing = BobOwing - amonut;
                                System.out.println("Transferred "+amonut+" to Bob.");
                                System.out.println("Owing "+BobOwing+" from Bob.");
                                System.out.println("Balance: " + balance);
                            }
                        }
                        else{
                            balance = balance-amonut;
                            System.out.println("Transferred "+amonut+" to Bob.");
                            System.out.println("Balance: " + balance);
                            BobBalance = BobBalance+ amonut;
                        }
                    }
                    else {
                        int tempO = amonut - balance;
                        AliceOwing = AliceOwing + tempO;
                        System.out.println("Transferred "+balance+" to Bob.");
                        BobBalance = BobBalance + amonut;
                        balance = 0;
                        System.out.println("Owing "+AliceOwing+" to Bob.");
                    }
                    break;
            }
        }
    }
    void menu(){
        char option ='\0';

        System.out.println("Balance: "+balance);
        Scanner scanner = new Scanner(System.in);
        System.out.println("A. Check Balance");
        System.out.println("B. Deposit");
        System.out.println("C. Withdraw");
        System.out.println("D. Previous Transaction");
        System.out.println("E. Transfer Money");
        System.out.println("F. Exit");

        do {
            System.out.println("===================================");
            System.out.println("Enter an option");
            System.out.println("===================================");
            option = scanner.next().charAt(0);

            switch (option) {
                case 'A', 'a' -> {
                    System.out.println("-----------------------------------");
                    System.out.println("Balance: " + balance);
                    System.out.println("-----------------------------------");
                }
                case 'B','b' -> {
                    System.out.println("-----------------------------------");
                    System.out.println("Enter the amount to deposit");
                    System.out.println("-----------------------------------");
                    int amount = scanner.nextInt();
                    deposit(amount);
                    System.out.println("Balance: "+balance);
                }
                case 'C','c' -> {
                    System.out.println("-----------------------------------");
                    System.out.println("Enter the amount to withdraw");
                    System.out.println("-----------------------------------");
                    int amountWithdraw = scanner.nextInt();
                    withdraw(amountWithdraw);
                    System.out.println("Balance: "+balance);
                }
                case 'D','d' -> {
                    System.out.println("-----------------------------------");
                    getPreviousTransaction();
                    System.out.println("-----------------------------------");
                }
                case 'E','e'->{
                    System.out.println("-----------------------------------");
                    System.out.println("Enter the account owner");
                    System.out.println("-----------------------------------");
                    String accountOwner = scanner.next();
                    System.out.println("-----------------------------------");
                    System.out.println("Enter the amount");
                    System.out.println("-----------------------------------");
                    int amount = scanner.nextInt();
                    transferMoney(amount,accountOwner);
                    System.out.println("-----------------------------------");

                }
                case 'F','f' ->{
                    if(customerName.equals("Alice")){
                        AliceBalance = balance;
                        AliceLastTransaction = lastTransaction;
                    }
                    else if(customerName.equals("Bob")){
                        BobBalance = balance;
                        BobLastTransaction = lastTransaction;
                    }
                    System.out.println("*********************************");
                }
                default -> System.out.println("Invalid Option...");
            }
        }while (option!='F');
        System.out.println("Thank you for using our services");
        System.out.println("Enter your name");
        customerDetails(scanner.next());
    }
}

