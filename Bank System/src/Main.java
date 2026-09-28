import java.util.Scanner;

public class Main {
    private static Bank bank = new Bank();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        do {
            printMenu();
            choice = readInt("Choose an option: ");

            switch (choice) {
                case 1 -> addCustomer();
                case 2 -> addAccount();
                case 3 -> deposit();
                case 4 -> withdraw();
                case 5 -> listCustomers();
                case 0 -> System.out.println("\nGoodbye!");
                default -> System.out.println("\nInvalid option, try again.");
            }
        } while (choice != 0);

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n==============================");
        System.out.println("        BANK MENU");
        System.out.println("==============================");
        System.out.println("1. Add customer");
        System.out.println("2. Add account to customer");
        System.out.println("3. Deposit");
        System.out.println("4. Withdraw");
        System.out.println("5. List customers & accounts");
        System.out.println("0. Exit");
        System.out.println("------------------------------");
    }

    private static void addCustomer() {
        System.out.println("\n--- Add Customer ---");
        System.out.print("First name : ");
        String first = scanner.nextLine();
        System.out.print("Last name  : ");
        String last = scanner.nextLine();

        bank.addCustomer(first, last);
        System.out.println("-> Customer \"" + first + " " + last + "\" added.");
    }

    private static void addAccount() {
        System.out.println("\n--- Add Account ---");
        Customer c = pickCustomer();
        if (c == null) return;

        double initial = readDouble("Initial balance: Rp");
        c.setAccount(new Account(initial));
        System.out.println("-> Account created for " + c.getFirstName()
                + " " + c.getLastName() + " with balance " + initial);
    }

    private static void deposit() {
        System.out.println("\n--- Deposit ---");
        Customer c = pickCustomer();
        if (c == null) return;

        Account a = pickAccount(c);
        if (a == null) return;

        double amount = readDouble("Amount to deposit: Rp");
        if (a.deposit(amount)) {
            System.out.println("-> Success. New balance: Rp" + a.getBalance());
        } else {
            System.out.println("-> Failed. Amount must be positive.");
        }
    }

    private static void withdraw() {
        System.out.println("\n--- Withdraw ---");
        Customer c = pickCustomer();
        if (c == null) return;

        Account a = pickAccount(c);
        if (a == null) return;

        double amount = readDouble("Amount to withdraw: Rp");
        if (a.withdraw(amount)) {
            System.out.println("-> Success. New balance: Rp" + a.getBalance());
        } else {
            System.out.println("-> Failed. Insufficient balance.");
        }
    }

    private static void listCustomers() {
        System.out.println("\n--- Customers & Accounts ---");
        if (bank.getNumOfCustomers() == 0) {
            System.out.println("(no customers yet)");
            return;
        }

        for (int i = 0; i < bank.getNumOfCustomers(); i++) {
            Customer c = bank.getCustomer(i);
            System.out.println((i + 1) + ". " + c.getFirstName() + " " + c.getLastName()
                    + " (" + c.getNumOfAccounts() + " account(s))");

            for (int j = 0; j < c.getNumOfAccounts(); j++) {
                Account a = c.getAccount(j);
                System.out.println("     - Account " + (j + 1) + " | balance: " + a.getBalance());
            }
        }
    }

    // ---------- helpers ----------

    private static Customer pickCustomer() {
        if (bank.getNumOfCustomers() == 0) {
            System.out.println("-> No customers yet. Add one first.");
            return null;
        }

        System.out.println("Customers:");
        for (int i = 0; i < bank.getNumOfCustomers(); i++) {
            Customer c = bank.getCustomer(i);
            System.out.println("  " + (i + 1) + ". " + c.getFirstName() + " " + c.getLastName());
        }

        int choice = readInt("Pick customer number: ");
        int index = choice - 1;
        if (index < 0 || index >= bank.getNumOfCustomers()) {
            System.out.println("-> Invalid customer number.");
            return null;
        }
        return bank.getCustomer(index);
    }

    private static Account pickAccount(Customer c) {
        if (c.getNumOfAccounts() == 0) {
            System.out.println("-> This customer has no accounts yet. Add one first.");
            return null;
        }

        System.out.println("Accounts:");
        for (int j = 0; j < c.getNumOfAccounts(); j++) {
            System.out.println("  " + (j + 1) + ". balance: " + c.getAccount(j).getBalance());
        }

        int choice = readInt("Pick account number: ");
        int index = choice - 1;
        if (index < 0 || index >= c.getNumOfAccounts()) {
            System.out.println("-> Invalid account number.");
            return null;
        }
        return c.getAccount(index);
    }

    private static int readInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a whole number: ");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    private static double readDouble(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.print("Please enter a number: ");
            scanner.next();
        }
        double value = scanner.nextDouble();
        scanner.nextLine();
        return value;
    }
}