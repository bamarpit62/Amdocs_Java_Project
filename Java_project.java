import java.util.*;

class Transaction {
    private final String date;
    private final String description;
    private final double amount;

    public Transaction(String description, double amount) {
        this.date = new Date().toString();
        this.description = description;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Date: " + date + ", Description: " + description + ", Amount: ₹" + amount;
    }
}
//This is a transaction class that manage the transaction history

class BankAccount {                    //This is BankAccount class and have one constructor and four methods
    private final String accountNumber;
    private final String accountHolderName;
    private double balance;
    private final ArrayList<Transaction> transactionHistory;

    public BankAccount(String accountNumber, String accountHolderName, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
    } //BankAccount Constructor

    public void deposit(double amount) { //Deposit method for deposit the amount
        balance += amount;
        transactionHistory.add(new Transaction("Deposit", amount));
    }

    public boolean withdraw(double amount) {  //Withdraw method for withdraw the amount
        if (balance >= amount) {
            balance -= amount;
            transactionHistory.add(new Transaction("Withdrawal", -amount));
            return true;
        }
        System.out.println("Insufficient balance.");
        return false;
    }

    public String getAccountDetails() {    //This methos returns the details of the account
        return "Account Number: " + accountNumber +
               "\nAccount Holder: " + accountHolderName +
               "\nBalance: rupees" + balance;
    }

    public ArrayList<Transaction> getTransactionHistory() { // This method return the Arraylist that provide the transaction history
        return transactionHistory;
    }
}



public class BankManagementSystemproject {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, BankAccount> accounts = new HashMap<>();

        while (true) {
            System.out.println("1. Create Account\n2. Deposit\n3. Withdraw\n4. Details of the user\n5. Display all user\n6. Show Transaction History\n7. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1:
                    System.out.print("Account Number: ");
                    String accountNumber = scanner.nextLine();
                    System.out.print("Account Holder Name: ");
                    String accountHolderName = scanner.nextLine();
                    System.out.print("Initial Balance: ");
                    double initialBalance = scanner.nextDouble();
                    accounts.put(accountNumber, new BankAccount(accountNumber, accountHolderName, initialBalance));
                    System.out.println("Account created.");
                    break;
                case 2:
                    System.out.print("Account Number: ");
                    String depositAccountNumber = scanner.nextLine();
                    BankAccount depositAccount = accounts.get(depositAccountNumber);
                    if (depositAccount != null) {
                        System.out.print("Deposit Amount: ");
                        double depositAmount = scanner.nextDouble();
                        depositAccount.deposit(depositAmount);
                        System.out.println("Amount deposited.");
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 3:
                    System.out.print("Account Number: ");
                    String AccountNumberforWithdraw = scanner.nextLine();
                    BankAccount withdrawAccount = accounts.get(AccountNumberforWithdraw);
                    if (withdrawAccount != null) {
                        System.out.print("Withdraw Amount: ");
                        double withdrawAmount = scanner.nextDouble();
                        if (withdrawAccount.withdraw(withdrawAmount)) {
                            System.out.println("Amount withdrawn.");
                        }
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 4:
                    System.out.print("Account Number: ");
                    String accountNumberfordetails = scanner.nextLine();
                    BankAccount accountDetails = accounts.get(accountNumberfordetails);
                    if (accountDetails != null) {
                        System.out.println(accountDetails.getAccountDetails());
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;

                case 5:
                    displayAllUsers(accounts);
                    break;
                case 6:
                    System.out.print("Account Number: ");
                    String transactionHistoryNumber = scanner.nextLine();
                    BankAccount historyAccount = accounts.get(transactionHistoryNumber);
                    if (historyAccount != null) {
                        System.out.println("Transaction History for Account: " + transactionHistoryNumber);
                        historyAccount.getTransactionHistory().forEach(System.out::println);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
            

                case 7:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
        
    }

    // In the main method, I provide the choise for the 1. Create Account 2. Deposit 3. 
    // Withdraw 4. Details of the user 5. Display all user\n6. Show Transaction History 7. Exit
     private static void displayAllUsers(Map<String, BankAccount> accounts) { //This class display the all user of the bank
        System.out.println("All Users:");
        for (Map.Entry<String, BankAccount> entry : accounts.entrySet()) {
            String accountNumber = entry.getKey();
            BankAccount account = entry.getValue();
            System.out.println("Account Number: " + accountNumber);
            System.out.println(account.getAccountDetails());
            System.out.println();
        }
    }
}
