package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Paint Fence",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/paint-fence/"
)
public class Q276 {
    public int numWays(int n, int k) {
        return topDownDpSol(n, k);
        //return bottomeUpDpSol(n, k);
    }

    /*
        dp[i] means ways to paint i posts
        dp[i] = dp[i - 1] * (k - 1) + dp[i - 2] * (k - 1)
    */
    private int topDownDpSol(int n, int k) {
        if(n == 1) {
            return k;
        }

        int[] dp = new int[n + 1];
        dp[1] = k;
        dp[2] = k + k * (k - 1);
        for(int i = 3; i < n + 1; i++) {
            dp[i] = dp[i - 1] * (k - 1) + dp[i - 2] * (k - 1);
        }

        return dp[n];
    }

    private int bottomUpDpSol(int n, int k) {
        return dfs(n, k, 0, new Integer[n]);
    }

    private int dfs(int n, int k, int curr, Integer[] memo) {
        if(curr == n) {
            return 1;
        }

        if(memo[curr] != null) {
            return memo[curr];
        }

        int cnt = 0;
        int c = curr == 0 ? k : k - 1;

        cnt = dfs(n, k, curr + 1, memo) * c;
        if(curr < n - 1) {
            cnt += dfs(n, k, curr + 2, memo) * c;
        }


        return memo[curr] = cnt;
    }
}
