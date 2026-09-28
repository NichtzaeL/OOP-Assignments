public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();

        bank.addCustomer("Niel", "Rayes");
        bank.addCustomer("Arken", "Nichtzael");

        bank.getCustomer(0).setAccount(new Account(500.0));

        System.out.println("Number of customers: " + bank.getNumOfCustomers());

        for (int i = 0; i < bank.getNumOfCustomers(); i++) {
            Customer c = bank.getCustomer(i);
            System.out.println(i + ": " + c.getFirstName() + " " + c.getLastName()
                    + " (accounts: " + c.getNumOfAccounts() + ")");
        }

        Account acc = bank.getCustomer(0).getAccount(0);
        acc.deposit(200);
        acc.withdraw(100);
        System.out.println("Niel's balance: " + acc.getBalance());
    }
}