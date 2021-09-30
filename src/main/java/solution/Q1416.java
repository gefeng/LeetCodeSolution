package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Restore the Array",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/restore-the-array/"
)
public class Q1416 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    private static final int MOD = (int)1e9 + 7;
    public int numberOfArrays(String s, int k) {
        return bottomUpSol(s, k);
    }
    
    /**
     * state:
     *  dp[i] denotes number of arrays can be formed by using first i characters
     * transition:
     *  dp[i] = sum(dp[j] + 1) if [j, i] is a valid number
     * */
    private int bottomUpSol(String s, int k) {
        int n = s.length();
        int[] dp = new int[n + 1];

        dp[0] = 1;

        for(int i = 1; i <= n; i++) {
            long cnt = 0;
            if(s.charAt(i - 1) != '0') {
                cnt = dp[i - 1];
            }

            for(int j = 1; j <= 9; j++) {
                if(i - 1 - j >= 0 && isValid(s, k, i - 1, j)) {
                    cnt = (cnt + dp[i - 1 - j]) % MOD;
                }
            }
            dp[i] = (int)cnt;
        }
        return dp[n];
    }

    private int topDownSol(String s, int k) {
        return dfs(s, k, 0, 0, new Integer[s.length()][11]);
    }

    private int dfs(String s, int k, int curr, int preLen, Integer[][] memo) {
        int n = s.length();
        if(curr == n) {
            return 1;
        }

        if(memo[curr][preLen] != null) {
            return memo[curr][preLen];
        }

        char d = s.charAt(curr);
        int cnt = 0;

        if(d != '0' && (d - '0') <= k) {
            cnt = (cnt + dfs(s, k, curr + 1, 1, memo)) % MOD;
        }

        if(curr > 0 && isValid(s, k, curr, preLen)) {
            cnt = (cnt + dfs(s, k, curr + 1, preLen + 1, memo)) % MOD;
        }

        return memo[curr][preLen] = cnt;
    }

    private boolean isValid(String s, int k, int curr, int preLen) {
        if(preLen >= 10) {
            return false;
        }

        if(s.charAt(curr - preLen) == '0') {
            return false;
        }

        long num = 0;
        for(int i = curr - preLen; i <= curr; i++) {
            num = num * 10 + s.charAt(i) - '0';
        }

        if(num <= k) {
            return true;
        }

        return false;
    }
}
