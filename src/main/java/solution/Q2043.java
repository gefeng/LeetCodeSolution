package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Simple Bank System",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DESIGN,
        url = "https://leetcode.com/problems/simple-bank-system/"
)
public class Q2043 {
    /**
     * Time:  O(1) for each operation
     * Space: O(N)
     * */
    long[] balance;
    int n;
    public Q2043(long[] balance) {
        this.n = balance.length;
        this.balance = balance;
    }

    public boolean transfer(int account1, int account2, long money) {
        if(account1 < 1 || account1 > n || account2 < 1 || account2 > n) {
            return false;
        }

        if(balance[account1 - 1] < money) {
            return false;
        }

        balance[account2 - 1] += money;
        balance[account1 - 1] -= money;
        return true;
    }

    public boolean deposit(int account, long money) {
        if(account < 1 || account > n) {
            return false;
        }

        balance[account - 1] += money;
        return true;
    }

    public boolean withdraw(int account, long money) {
        if(account < 1 || account > n) {
            return false;
        }

        if(balance[account - 1] < money) {
            return false;
        }

        balance[account - 1] -= money;
        return true;
    }
}
