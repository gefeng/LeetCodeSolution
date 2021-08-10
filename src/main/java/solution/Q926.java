package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Flip String to Monotone Increasing",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/flip-string-to-monotone-increasing/"
)
public class Q926 {
    public int minFlipsMonoIncr(String s) {
        return prefixSumSol(s);
    }

    /**
     * State:
     *  dp[i][0] means minimum number of flips to make s[0, i] ends with 0
     *  dp[i][1] means minimum number of flips to make s[0, i] ends with 1
     * Transition:
     *  dp[i][0] = dp[i - 1][0] if s[i] == 0
     *           = dp[i - 1][0] + 1 if s[i] == 1
     *  dp[i][1] = dp[i - 1][1] + 1 if s[i] == 0
     *           = min(dp[i - 1][1],  dp[i - 1][0]) if s[i] == 1
     *
     *  Time:  O(N)
     *  Space: O(1)
     * */
    private int bottomUpDpSol(String s) {
        int n = s.length();
        int dp0 = s.charAt(0) - '0';
        int dp1 = (s.charAt(0) - '1' + 2) % 2;

        for(int i = 1; i < n; i++) {
            int next0, next1;
            if(s.charAt(i) == '0') {
                next0 = dp0;
                next1 = dp1 + 1;
            } else {
                next0 = dp0 + 1;
                next1 = Math.min(dp0, dp1);
            }
            dp0 = next0;
            dp1 = next1;
        }

        return Math.min(dp0, dp1);
    }

    /**
     * Count all ones end at i by prefix sum.
     * Iterate over the string and find for each index i,
     * minimum cost to (flip s[0,i] to zeros + flip s[i + 1, n - 1] to ones)
     *
     * Time:  O(N)
     * Space: O(N)
     * */
    private int prefixSumSol(String s) {
        int n = s.length();
        int res = n;
        int[] preSum = new int[n + 1];

        for(int i = 1; i <= n; i++) {
            preSum[i] = s.charAt(i - 1) == '1' ? preSum[i - 1] + 1 : preSum[i - 1];
        }

        for(int i = 0; i < n; i++) {
            int l = preSum[i + 1];
            int r = n - i - 1 - preSum[n] + preSum[i];
            res = Math.min(res, l + r);
        }
        return res;
    }
}
