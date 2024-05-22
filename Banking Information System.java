import java.util.Scanner;

class BankUser {
    private String name;
    private String address;
    private String contactInfo;
    private double balance;
    private static int accountNumberCounter = 1001;
    private int accountNumber;

    public BankUser(String name, String address, String contactInfo, double initialDeposit) {
        this.name = name;
        this.address = address;
        this.contactInfo = contactInfo;
        this.balance = initialDeposit;
        this.accountNumber = accountNumberCounter++;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful. Current balance: " + balance);
        } else {
            System.out.println("Invalid amount for deposit.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal successful. Current balance: " + balance);
        } else {
            System.out.println("Invalid amount or insufficient balance for withdrawal.");
        }
    }

    public void displayAccountDetails() {
        System.out.println("Name: " + name);
        System.out.println("Address: " + address);
        System.out.println("Contact Information: " + contactInfo);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Balance: " + balance);
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }
}

class WithdrawalModule {
    public static void withdraw(BankUser user, double amount) {
        user.withdraw(amount);
    }
}

public class BankingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankUser[] bankUsers = new BankUser[100];
        int userCount = 0;

        int choice;
        do {
            System.out.println("1. User Registration");
            System.out.println("2. Account Management");
            System.out.println("3. Deposit");
            System.out.println("4. Withdrawal");
            System.out.println("5. Fund Transfer");
            System.out.println("6. Account Statements");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("User Registration:");
                    System.out.print("Enter name: ");
                    String name = scanner.next();
                    System.out.print("Enter address: ");
                    String address = scanner.next();
                    System.out.print("Enter contact information: ");
                    String contactInfo = scanner.next();
                    System.out.print("Enter initial deposit amount: ");
                    double initialDeposit = scanner.nextDouble();

                    bankUsers[userCount] = new BankUser(name, address, contactInfo, initialDeposit);
                    System.out.println("User registered successfully!");
                    System.out.println("Account Number: " + bankUsers[userCount].getAccountNumber());  // Display the account number
                    userCount++;
                    break;
                case 2:
                    System.out.println("Account Management:");
                    System.out.print("Enter account number: ");
                    int accountNumber = scanner.nextInt();
                    boolean found = false;
                    for (BankUser user : bankUsers) {
                        if (user != null && user.getAccountNumber() == accountNumber) {
                            user.displayAccountDetails();
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Account not found!");
                    }
                    break;
                case 3:
                    System.out.println("Deposit:");
                    System.out.print("Enter account number: ");
                    accountNumber = scanner.nextInt();
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    for (BankUser user : bankUsers) {
                        if (user != null && user.getAccountNumber() == accountNumber) {
                            user.deposit(depositAmount);
                            break;
                        }
                    }
                    break;
                case 4:
                    System.out.println("Withdrawal:");
                    System.out.print("Enter account number: ");
                    accountNumber = scanner.nextInt();
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawalAmount = scanner.nextDouble();
                    for (BankUser user : bankUsers) {
                        if (user != null && user.getAccountNumber() == accountNumber) {
                            WithdrawalModule.withdraw(user, withdrawalAmount);
                            break;
                        }
                    }
                    break;
                case 5:
                    System.out.println("Fund Transfer:");
                    System.out.print("Enter sender's account number: ");
                    int senderAccountNumber = scanner.nextInt();
                    System.out.print("Enter recipient's account number: ");
                    int recipientAccountNumber = scanner.nextInt();
                    System.out.print("Enter transfer amount: ");
                    double transferAmount = scanner.nextDouble();

                    BankUser sender = null;
                    BankUser recipient = null;
                    for (BankUser user : bankUsers) {
                        if (user != null && user.getAccountNumber() == senderAccountNumber) {
                            sender = user;
                        }
                        if (user != null && user.getAccountNumber() == recipientAccountNumber) {
                            recipient = user;
                        }
                    }
                    if (sender != null && recipient != null) {
                        sender.withdraw(transferAmount);
                        recipient.deposit(transferAmount);
                        System.out.println("Fund transfer successful.");
                    } else {
                        System.out.println("Sender or recipient account not found.");
                    }
                    break;
                case 6:
                    System.out.println("Account Statements:");
                    System.out.print("Enter account number: ");
                    accountNumber = scanner.nextInt();
                    for (BankUser user : bankUsers) {
                        if (user != null && user.getAccountNumber() == accountNumber) {
                            System.out.println("--Account Statement--");
                            user.displayAccountDetails();
                            // Additional code to display transaction history could be added here
                            break;
                        }
                    }
                    break;
                case 7:
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 7);

        scanner.close();
    }
}
