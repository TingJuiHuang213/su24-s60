/**
 * This class represents a bank account whose current balance is a nonnegative
 * amount in US dollars.
 */
public class Account {
    private int balance;
    private Account parentAccount;

    /**
     * Constructor to initialize an account with an initial balance.
     */
    public Account(int initialBalance) {
        this.balance = initialBalance;
        this.parentAccount = null;
    }

    /**
     * Constructor to initialize an account with an initial balance and a parent account.
     */
    public Account(int initialBalance, Account parentAccount) {
        this.balance = initialBalance;
        this.parentAccount = parentAccount;
    }

    /**
     * Deposits the specified amount into this account.
     *
     * @param amount The amount to deposit.
     */
    public void deposit(int amount) {
        if (amount < 0) {
            System.out.println("Cannot deposit negative amount");
        } else {
            balance += amount;
        }
    }

    /**
     * Withdraws the specified amount from this account if possible.
     * If the balance is insufficient, it tries to withdraw the difference from the parent account.
     *
     * @param amount The amount to withdraw.
     * @return true if the withdrawal was successful, false otherwise.
     */
    public boolean withdraw(int amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        } else if (parentAccount != null) {
            // 计算需要从父账户提取的金额
            int deficit = amount - balance;
            // 尝试从父账户提取不足的部分
            if (parentAccount.withdraw(deficit)) {
                balance = 0; // 当前账户余额归零
                return true;
            }
        }
        // 提取失败，输出提示信息
        System.out.println("Insufficient funds");
        return false;
    }

    /**
     * Merges the balance of another account into this account and sets the other account's balance to zero.
     *
     * @param other The account to merge with this account.
     */
    public void merge(Account other) {
        // 将另一个账户的余额合并到当前账户
        this.balance += other.balance;
        // 将另一个账户的余额置零
        other.balance = 0;
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
