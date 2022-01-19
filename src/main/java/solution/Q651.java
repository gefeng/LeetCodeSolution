package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "4 Keys Keyboard",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/4-keys-keyboard/"
)
public class Q651 {
    /**
     * state:
     *  dp[i] denotes max A can get with i operations
     * transition:
     *  dp[i] = max(dp[i - 1] + 1, max(dp[i - 2 - j] * j)) where j is number of times of copy
     *
     * Time:  O(N ^ 2)
     * Space: O(N)
     * */
    public int maxA(int n) {
        int[] dp = new int[n + 1];

        for(int i = 1; i <= n; i++) {
            dp[i] = Math.max(dp[i], dp[i - 1] + 1); // print 'A'

            for(int j = 1; i - 2 - j >= 1; j++) {
                dp[i] = Math.max(dp[i], dp[i - 2 - j] + dp[i - 2 - j] * j); // copy j times
            }
        }

        return dp[n];
    }
}
