package solution.weekly292;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Count Number of Texts",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/contest/weekly-contest-292/problems/count-number-of-texts/"
)
public class Q2266 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    private long mod = (long)1e9 + 7;
    public int countTexts(String s) {
        long ans = 0;
        int n = s.length();

        for(int i = 0; i < n; ) {
            int j = i;
            char c = s.charAt(j);
            while(i < n && s.charAt(i) == c) {
                i++;
            }

            int tot = i - j;
			int len = c == '7' || c == '9' ? 4 : 3;
			long[] dp = new long[len];
			Arrays.fill(dp, 1);
			int k = 1;
			for(; k < Math.min(tot, len); k++) {
				for(int p = 0; p < k; p++) {
					dp[k] = (dp[k] + dp[p]) % mod;
				}
			}

			for(; k < tot; k++) {
				long sum = dp[len - 1];
				for(int p = 0; p < len - 1; p++) {
					sum = (sum + dp[p]) % mod;
					dp[p] = dp[p + 1];
				}
				dp[len - 1] = sum;
			}

            ans = ans == 0 ? dp[Math.min(k, len) - 1] : (ans * dp[Math.min(k, len) - 1]) % mod;
        }

        return (int)ans;
    }
}
