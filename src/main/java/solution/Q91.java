package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;

@Problem(
        title = "Decode Ways",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/decode-ways/"
)
public class Q91 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int numDecodings(String s) {
        return bottomUpOptimizedSol(s);
    }

    private int topDownSol(String s) {
        return dfs(s, 0, new Integer[s.length()]);
    }

    private int dfs(String s, int i, Integer[] memo) {
        int n = s.length();

        if(i == n) {
            return 1;
        }

        if(memo[i] != null) {
            return memo[i];
        }

        int cnt = 0;
        int c1 = s.charAt(i) - '0';

        if(c1 == 0) {
            return 0;
        } else {
            cnt += dfs(s, i + 1, memo);
        }

        if(i + 1 < n) {
            int c2 = c1 * 10 + s.charAt(i + 1) - '0';
            if(c2 > 0 && c2 < 27) {
                cnt += dfs(s, i + 2, memo);
            }
        }

        return memo[i] = cnt;
    }

    /**
     * state:
     *  dp[i] denotes # ways to decode s[0, i]
     * transition:
     *  dp[i] = dp[i - 1] + dp[i - 2]
     * */
    private int bottomUpSol(String s) {
        if(s.charAt(0) == '0') {
            return 0;
        }
        int n = s.length();
        int[] dp = new int[n];
        dp[0] = 1;
        for(int i = 1; i < n; i++) {
            int pre = s.charAt(i - 1) - '0';
            int cur = s.charAt(i) - '0';
            if(cur > 0) {
                dp[i] = dp[i - 1];
            }
            if(pre != 0 && pre * 10 + cur < 27) {
                dp[i] = i == 1 ? dp[i] + 1 : dp[i] + dp[i - 2];
            }
        }
        return dp[n - 1];
    }

    /**
     * space optimized, we only need to know dp[i - 1] and dp[i - 2]
     * */
    private int bottomUpOptimizedSol(String s) {
        if(s.charAt(0) == '0') {
            return 0;
        }

        int n = s.length();
        int dpOne = 1;
        int dpTwo = 0;

        for(int i = 1; i < n; i++) {
            int pre = s.charAt(i - 1) - '0';
            int cur = s.charAt(i) - '0';
            int next = 0;
            if(cur > 0) {
                next = dpOne;
            }
            if(pre != 0 && pre * 10 + cur < 27) {
                next = i == 1 ? next + 1 : next + dpTwo;
            }

            dpTwo = dpOne;
            dpOne = next;
        }

        return dpOne;
    }
}
