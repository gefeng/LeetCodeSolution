package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Delete And Earn",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/delete-and-earn/"
)
public class Q740 {
    /*
        state:
            dp[i] means the best score so far
        transition:
            dp[i] = Math.max(dp[i - 1], dp[i- 2] + i * count[i])
                    dp[i - 1] means pick previous one so current one got deleted
                    dp[i - 2] + i * count[i] means pick current number of delete its neighbors
    */
    public int deleteAndEarn(int[] nums) {
        int n = 10001;
        int[] count = new int[n];
        int[] dp = new int[n];

        for(int num : nums) {
            count[num]++;
        }

        dp[0] = 0;
        dp[1] = count[1];
        for(int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 2] + i * count[i], dp[i - 1]);
        }

        return dp[n - 1];
    }
}
