package application;

import java.time.LocalDate;

public class Account {
    private int accountNumber;
    private int accountType;
    private LocalDate openDate;
    private float balance;
    private int customerId;

    public Account(int accountNumber, int accountType, LocalDate openDate, float balance, int customerId) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.openDate = openDate;
        this.balance = balance;
        this.customerId = customerId;
    }
    
    
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit of $" + amount + " successful. New balance is $" + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    } 
    

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount.");
        } else if (balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawal of " + amount + " successful.");
        } else {
            System.out.println("Insufficient funds for withdrawal of " + amount);
        }
    }
    
    
    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getAccountType() {
        return accountType;
    }

    public void setAccountType(int accountType) {
        this.accountType = accountType;
    }

    public LocalDate getOpenDate() {
        return openDate;
    }

    public void setOpenDate(LocalDate openDate) {
        this.openDate = openDate;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }


}