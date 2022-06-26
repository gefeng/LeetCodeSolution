package solution.weekly299;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Count Number of Ways to Place Houses",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/contest/weekly-contest-299/problems/count-number-of-ways-to-place-houses/"
)
public class Q2320 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int countHousePlacements(int n) {
        long mod = (long)1e9 + 7;
        long ans = 0;
        long[][] dp = new long[n + 1][2];
        dp[1][0] = 1;
        dp[1][1] = 1;
        for(int i = 2; i <= n; i++) {
            dp[i][1] = dp[i - 1][0];
            dp[i][0] = (dp[i - 1][0] + dp[i - 1][1]) % mod;
        }

        long tot = (dp[n][0] + dp[n][1]) % mod;
        ans = ((dp[n][0] * tot % mod) + (dp[n][1] * tot % mod)) % mod;


        return (int)ans;
    }
}
