package solution.weekly300;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Number of People Aware of a Secret",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/contest/weekly-contest-300/problems/number-of-people-aware-of-a-secret/"
)
public class Q2327 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int peopleAwareOfSecret(int n, int delay, int forget) {
        int mod = (int)1e9 + 7;
        int ans = 0;
        int[] dp = new int[n + 1];

        dp[1] = 1;
        for(int i = 2; i <= n; i++) {
            for(int j = Math.max(0, i - forget + 1); j <= Math.max(0, i - delay); j++) {
                dp[i] = (dp[i] + dp[j]) % mod;
            }
        }

        for(int i = Math.max(0, n - forget + 1); i <= n; i++) {
            ans = (ans + dp[i]) % mod;
        }

        return ans;
    }
}
