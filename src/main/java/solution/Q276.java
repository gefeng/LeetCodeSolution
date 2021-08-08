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
    /**
     * Bottom up dp straightforward.
     * State:
     *  dp[i] denotes number of ways to paint i posts
     * Transition:
     *  dp[i] = dp[i - 1] * (k - 1) + dp[i - 2] * (k - 1)
     *
     * Time:  O(N)
     * Space: O(N)
     *
     * */
    public int numWays(int n, int k) {
        int[] dp = new int[n + 1];
        dp[1] = k;

        for(int i = 2; i <= n; i++) {
            int one = dp[i - 1] * (k - 1);
            int two = i == 2 ? k : dp[i - 2] * (k - 1);
            dp[i] = one + two;
        }

        return dp[n];
    }
}
