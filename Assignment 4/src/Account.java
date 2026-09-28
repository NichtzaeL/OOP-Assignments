public class Account {
    private double balance;

    public Account(double init_balance) {
        balance = init_balance;
    }

    public double getBalance() {
        return balance;
    }

    public boolean deposit(double amt) {
        if (amt > 0) {
            balance = balance + amt;
            return true;
        } else {
            return false;
        }
    }

    public boolean withdraw(double amt) {
        if (balance >= amt) {
            balance = balance - amt;
            return true;
        } else {
            return false;
        }
    }
}