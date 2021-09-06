package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "First Day Where You Have Been in All the Rooms",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/first-day-where-you-have-been-in-all-the-rooms/"
)
public class Q1997 {
    /**
     * state:
     *  dp[i] denotes total days required before leaving room i from day 0.
     * transition:
     *  dp[i] = dp[i - 1] + dp[i - 1] - dp[next[i] - 1] + 2;
     *
     * Time:  O(N)
     * Space: O(N)
     * */
    private static final int MOD = (int)1e9 + 7;
    public int firstDayBeenInAllRooms(int[] nextVisit) {
        int n = nextVisit.length;

        long[] dp = new long[n];
        dp[0] = 2;
        for(int i = 1; i < n - 1; i++) {
            int next = nextVisit[i];
            dp[i] = (MOD + (dp[i - 1] * 2 % MOD) - (next == 0 ? 0 : dp[next - 1]) + 2) % MOD;
        }

        return (int)(dp[n - 2]);
    }
}
