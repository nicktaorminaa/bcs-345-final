
package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.time.LocalDate;
import javafx.application.Platform; 


public class Banking extends Application {
	public static Customer[] customers = new Customer[100];
    private Transactions[] transactions;
	private static ArrayList<Account> accounts;

    public Banking() {
        accounts = new ArrayList<Account>();
        loadAccounts();
    }
    public void loadAccounts() {
        try {
            File file = new File("accounts.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                Account account = new Account(
                        Integer.parseInt(parts[0]),
                        Integer.parseInt(parts[1]),
                        LocalDate.parse(parts[2]),
                        Float.parseFloat(parts[3]),
                        Integer.parseInt(parts[4]));
                addAccount(account);
            }
            scanner.close();
        } catch (FileNotFoundException ex) {
            System.err.println("Error loading accounts: " + ex.getMessage());
        }
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    
    public static void addCustomer(Customer customer) {
        Customer[] newCustomers = Arrays.copyOf(customers, customers.length + 1);
        newCustomers[customers.length] = customer;
        customers = newCustomers;
        System.out.println("Customer " + customer.getName() + " added.");
    }

 
    public void addAccount1(Account account) {
        accounts.add(account);
    }
    public void addTransaction(Transactions transaction) 
    {
        Transactions[] tempTransactions = new Transactions[transactions.length + 1];
        for (int i = 0; i < transactions.length; i++) {
            tempTransactions[i] = transactions[i];
        }
        tempTransactions[tempTransactions.length - 1] = transaction;
        transactions = tempTransactions;
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Banking System");
        try {
            File file = new File("customers.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                Customer customer = new Customer(
                        Integer.parseInt(parts[0]),
                        Integer.parseInt(parts[1]),
                        parts[3],
                        parts[4],
                        parts[5],
                        parts[2]);
                addCustomer(customer);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.exit(0);
        }
    
        try {
            File file = new File("accounts.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                Account account = new Account(
                        Integer.parseInt(parts[0]),
                        Integer.parseInt(parts[1]),
                        LocalDate.parse(parts[2]),
                        Float.parseFloat(parts[3]),
                        Integer.parseInt(parts[4]));
               addAccount(account);
            }
            scanner.close(); }
           catch (FileNotFoundException e) {
            System.out.print("File not found."); 
           
           }
        }

     
		public static void main(String[] args) {
        GridPane loginGridPane = new GridPane();
        loginGridPane.setPadding(new Insets(10, 10, 10, 10));
        loginGridPane.setVgap(5);
        loginGridPane.setHgap(5);

        Label customerIDLabel = new Label("Customer ID:");
        loginGridPane.add(customerIDLabel, 0, 0);

        TextField customerIDTextField = new TextField();
        loginGridPane.add(customerIDTextField, 1, 0);

        Label pinLabel = new Label("PIN:");
        loginGridPane.add(pinLabel, 0, 1);

        TextField pinTextField = new TextField();
        loginGridPane.add(pinTextField, 1, 1);

        Button loginButton = new Button("Login");
        loginGridPane.add(loginButton, 0, 2, 2, 1);

        Scene loginScene = new Scene(loginGridPane, 300, 150);

        GridPane customerMenuGridPane = new GridPane();
        customerMenuGridPane.setPadding(new Insets(10, 10, 10, 10));
        customerMenuGridPane.setVgap(5);
        customerMenuGridPane.setHgap(5);

        Button depositFundsButton = new Button("Deposit Funds");
        customerMenuGridPane.add(depositFundsButton, 0, 0);

        Button withdrawFundsButton = new Button("Deposit Funds"); 
        customerMenuGridPane.add(withdrawFundsButton, 0, 1); 
        Button transferFundsButton = new Button("Transfer Funds");
        customerMenuGridPane.add(transferFundsButton, 0, 2);

        Button viewBalanceButton = new Button("View Balance");
        customerMenuGridPane.add(viewBalanceButton, 0, 3);

        Button exitButton = new Button("Exit");
        customerMenuGridPane.add(exitButton, 0, 4);
        

        depositFundsButton.setOnAction(event -> {
            try (Scanner scanner = new Scanner(new File("accounts.txt"));
                FileWriter writer = new FileWriter("accounts.txt", true)) {
                System.out.print("Enter the customer ID: ");
                String customerId = scanner.nextLine();
                System.out.print("Enter the amount to deposit: ");
                double amount = scanner.nextDouble();
                scanner.nextLine(); 
                
                boolean foundCustomer = false;
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] tokens = line.split(",");
                    if (tokens[0].equals(customerId)) {
                        double balance = Double.parseDouble(tokens[1]);
                        balance += amount;
                        writer.write(customerId + "," + String.format("%.2f", balance) + "\n");
                        writer.flush();
                        foundCustomer = true;
                        System.out.printf("Deposit of %.2f completed. New balance for customer %s is %.2f.%n", amount, customerId, balance);
                        break;
                    }
                }
                if (!foundCustomer) {
                    System.err.println("Customer ID " + customerId + " not found.");
                }
            } catch (IOException ex) {
                System.err.println("Error accessing balances file: " + ex.getMessage());
            }
        });

	withdrawFundsButton.setOnAction(e -> {
	    try {
	        Labeled amountField = null;
			double amount = Double.parseDouble(amountField.getText());
	        double balance = 0.0;
	        boolean found = false;
	        Scanner scanner = new Scanner(new File("balances.txt"));
	        String tempFile = "temp.txt";
	        FileWriter writer = new FileWriter(tempFile);
	        while (scanner.hasNextLine()) {
	            String line = scanner.nextLine();
	            String[] parts = line.split(":");
	            Account accountNumber = null;
				if (parts[0].equals(accountNumber)) {
	                double currentBalance = Double.parseDouble(parts[1]);
	                if (amount <= currentBalance) {
	                    balance = currentBalance - amount;
	                    writer.write(accountNumber + ":" + balance);
	                    found = true;
	                } else {
	                    System.out.println("Insufficient funds");
	                    writer.write(line);
	                }
	            } else {
	                writer.write(line);
	            }
	        }
	        scanner.close();
	        writer.close();
	        if (found) {
	            File oldFile = new File("accounts.txt");
	            oldFile.delete();
	            File newFile = new File(tempFile);
	            newFile.renameTo(oldFile);
	            System.out.println("Withdrawal successful");
	        } else {
	            System.out.println("Account not found");
	        }
	    } catch (FileNotFoundException ex) {
	        System.out.println("File not found");
	    } catch (IOException ex) {
	        System.out.println("Error reading/writing file");
	    } catch (NumberFormatException ex) {
	        System.out.println("Invalid amount entered");
	    }
	});
	transferFundsButton.setOnAction(e -> {
	    System.out.println("Please enter the account number to transfer from:");
	    Scanner input = null;
	    int fromAccountNumber = input.nextInt();
	    Account fromAccount = null;
	    for (Account account : accounts) {
	        if (account != null && account.getAccountNumber() == fromAccountNumber) {
	            fromAccount = account;
	            break;
	        }
	    }
	    if (fromAccount == null) {
	        System.out.println("From account not found.");
	        return;
	    }

	    System.out.println("Please enter the account number to transfer to:");
	    int toAccountNumber = input.nextInt();
	    Account toAccount = null;
	    for (Account account : accounts) {
	        if (account != null && account.getAccountNumber() == toAccountNumber) {
	            toAccount = account;
	            break;
	        }
	    }
	    if (toAccount == null) {
	        System.out.println("To account not found.");
	        return;
	    }

	    System.out.println("Please enter the transfer amount:");
	    double amount = input.nextDouble();
	    if (fromAccount.getBalance() < amount) {
	        System.out.println("Insufficient funds.");
	        return;
	    }
	    fromAccount.withdraw(amount);
	    toAccount.deposit(amount);
	    System.out.println("Transfer successful.");
	});
        viewBalanceButton.setOnAction(e -> {
            System.out.println("Please enter the account number to view balance for:");
            Scanner input = null;
            int accountNumber = input.nextInt();
            Account account = null;
            for (Account acc : accounts) {
                if (acc != null && acc.getAccountNumber() == accountNumber) {
                    account = acc;
                    break;
                }
            }
            if (account == null) {
                System.out.println("Account not found.");
                return;
            }
            System.out.println("Account type: " + account.getAccountNumber());
            System.out.println("Account type: " + account.getAccountType());
            System.out.println("Account balance: " + account.getBalance());
        });
        exitButton.setOnAction(e -> 
        	  {
           System.out.println("Exiting customer menu.");            
        	     
   }); 

        Scene customerMenuScene = new Scene(null);
        Stage primaryStage = new Stage();
        primaryStage.setScene(customerMenuScene);
        primaryStage.show(); 
   
        primaryStage.setOnCloseRequest(event -> {
            Platform.exit();
        });   	
}
	} 

            	