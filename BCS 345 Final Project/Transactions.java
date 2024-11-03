package application;
import java.time.LocalDateTime;

public class Transactions {
    private int transactionNumber;
    private int customerNumber;
    private int accountNumber;
    private char transactionType;
    private LocalDateTime transactionDateTime;
    private float transactionAmount;
    private float updatedBalance; 
    
    public Transactions(int transactionNumber, int customerNumber, int accountNumber, char transactionType,
                        float transactionAmount) {
        this.transactionNumber = transactionNumber;
        this.customerNumber = customerNumber;
        this.accountNumber = accountNumber;
        this.transactionType = transactionType;
        this.transactionAmount = transactionAmount;
        this.transactionDateTime = LocalDateTime.now();
    }


	public Transactions(int customerId, int accountNumber2, double amount, String string) {
		
	}


	// Getters and Setters
    public int getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(int transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public int getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public char getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(char transactionType) {
        this.transactionType = transactionType;
    }

    public LocalDateTime getTransactionDateTime() {
        return transactionDateTime;
    }

    public void setTransactionDateTime(LocalDateTime transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }

    public float getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(float transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

	public float getUpdatedBalance() {
		return updatedBalance;
	}


	public void setUpdatedBalance(float updatedBalance) {
		this.updatedBalance = updatedBalance;
	}

}