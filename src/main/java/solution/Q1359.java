package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Count All Valid Pickup and Delivery Options",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/count-all-valid-pickup-and-delivery-options/"
)
public class Q1359 {
    /**
     * Time:  O(N ^ 2)
     * Space: O(N ^ 2)
     * */
    private static final int MOD = (int)1e9 + 7;
    public int countOrders(int n) {
        return dfs(n, 0, 0, new Integer[n + 1][n + 1]);
    }

    private int dfs(int n, int p, int d, Integer[][] memo) {
        if(d == n) {
            System.out.println(d);
            return 1;
        }

        if(memo[p][d] != null) {
            return memo[p][d];
        }

        long cnt = 0;
        if(p < n) {
            cnt = (cnt + (long)(n - p) * dfs(n, p + 1, d, memo)) % MOD;
        }

        if(p > d) {
            cnt = (cnt + (long)(p - d) * dfs(n, p, d + 1, memo)) % MOD;
        }

        return memo[p][d] = (int)cnt;
    }
}
