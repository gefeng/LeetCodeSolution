package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Stone Game VII",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/stone-game-viii/"
)
public class Q1872 {
    /**
     * state:
     *  dp[i] denotes maximum score difference current player can get with i stones left
     * transition:
     *  assume previous stones have been merged into one.
     *      1. one stone left [xxxxxx] both Alice and Bob cannot move so dp[1] = 0
     *      2. two stones left [xxxxxx]x Alice should take all dp[2] = psum[n] - dp[1]
     *      3. three stones left [xxxxx]xx:
     *          a. take all: dp[3] = psum[n] - dp[1]
     *          b. take two: dp[3] = psum[n - 1] - dp[2]
     *              => dp[3] = max(a, b)
     *      4 four stons left [xxxxx]xxx:
     *          a. take all: dp[4] = psum[n] - dp[1]
     *          b. take three: dp[4] = psum[n - 1] - dp[2]
     *          c. take two: dp[4] = psum[n - 2] - dp[3]
     *              => dp[4] = max(a, b, c) => dp[4] = max(dp[3], psum[n - 2] - dp[3])
     *
     *  dp[i] = max(dp[i - 1], psum[n - i + 2] - dp[i - 1])
     *
     *  Time:  O(N)
     *  Space: O(N)
     * */
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;
        int[] psum = new int[n + 1];
        int[] dp = new int[n + 1];

        Arrays.fill(dp, Integer.MIN_VALUE);

        for(int i = 1; i <= n; i++) {
            psum[i] = psum[i - 1] + stones[i - 1];
        }

        dp[2] = psum[n];
        for(int i = 3; i <= n; i++) {
            dp[i] = Math.max(dp[i - 1], psum[n - i + 2] - dp[i - 1]);
        }

        return dp[n];
    }
}
