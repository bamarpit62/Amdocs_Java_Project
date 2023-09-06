import java.util.*;

class Transaction {            //Transaction class that display the transaction history 
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
        return "Date: " + date + ", Description: " + description + ", Amount: repees" + amount;
    }
}

class BankAccount {             //BankAccount class have four methods 1. Deposit 2. withdraw 3.getAccountDetails 4.getTransactionHistory
    private final String accountNumber;
    private final String accountHolderName;
    private double balance;
    private final ArrayList<Transaction> transactionHistory;

    public BankAccount(String accountNumber, String accountHolderName, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add(new Transaction("Deposit", amount));
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            transactionHistory.add(new Transaction("Withdrawal", -amount));
            return true;
        }
        System.out.println("Insufficient balance.");
        return false;
    }

    public String getAccountDetails() {
        return "Account Number: " + accountNumber +
               "\nAccount Holder: " + accountHolderName +
               "\nBalance: rupees" + balance;
    }

    public ArrayList<Transaction> getTransactionHistory() {
        return transactionHistory;
    }
}

public class BankManagementSystemproject {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, BankAccount> accounts = new HashMap<>();

        while (true) {
            System.out.println("1. Create Account\n2. Deposit\n3. Withdraw\n4. Details of the user\n5. Display all user\n6. Show Transaction History\n7. Exit");
            System.out.print("Enter the choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1:
                    System.out.print("Account Number Of The Customer: ");
                    String accountNumber = scanner.nextLine();
                    System.out.print("Account Holder Name: ");
                    String accountHolderName = scanner.nextLine();
                    System.out.print("Initial Balance: ");
                    double initialBalance = scanner.nextDouble();
                    accounts.put(accountNumber, new BankAccount(accountNumber, accountHolderName, initialBalance));
                    System.out.println("Account created.");
                    break;
                case 2:
                    System.out.print("Account Number Of The Customer: ");
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
                    String withdrawAccountNumber = scanner.nextLine();
                    BankAccount withdrawAccount = accounts.get(withdrawAccountNumber);
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
                    String accountDetailsNumber = scanner.nextLine();
                    BankAccount accountDetails = accounts.get(accountDetailsNumber);
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
     private static void displayAllUsers(Map<String, BankAccount> accounts) {
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

