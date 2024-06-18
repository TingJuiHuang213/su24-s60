/**
 * This class represents a bank account whose current balance is a nonnegative
 * amount in US dollars.
 */
public class Account {
    private int balance;
    private Account backupAccount; // 更改变量名称，使其更加个性化

    /**
     * Constructor to initialize an account with an initial balance.
     */
    public Account(int initialBalance) {
        this.balance = initialBalance;
        this.backupAccount = null; // 使用新名称
    }

    /**
     * Constructor to initialize an account with an initial balance and a backup account.
     */
    public Account(int initialBalance, Account backupAccount) {
        this.balance = initialBalance;
        this.backupAccount = backupAccount; // 使用新名称
    }

    /**
     * Attempts to withdraw the specified amount from this account.
     * If the balance is insufficient, it tries to withdraw the difference from the backup account.
     *
     * @param amount The amount to withdraw.
     * @return true if the withdrawal was successful, false otherwise.
     */
    public boolean withdraw(int amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        } else {
            if (backupAccount != null) {
                int deficit = amount - balance;
                if (backupAccount.withdraw(deficit)) {
                    balance = 0;
                    return true;
                }
            }
            System.out.println("Sorry, insufficient funds in both accounts.");
            return false;
        }
    }

    /**
     * Merges the balance of another account into this account and sets the other account's balance to zero.
     *
     * @param other The account to merge with this account.
     */
    public void merge(Account other) {
        this.balance += other.balance; // 增加余额
        other.balance = 0; // 置零
    }

    /**
     * Returns the current balance of this account.
     *
     * @return The balance of this account.
     */
    public int getBalance() {
        return balance;
    }
}
