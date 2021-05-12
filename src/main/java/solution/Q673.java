package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Number of Longest Increasing Subsequence",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/number-of-longest-increasing-subsequence/"
)
public class Q673 {
    /*
    * 老子不想知道你妹的binary search解法，本宝宝不想知道！！！
    * */
    public int findNumberOfLIS(int[] nums) {
        return dpSolution(nums);
    }

    /*
        state:
            dp[i][0] means the LIS ends with nums[i]
            dp[i][1] means the the number of LIS ends with nums[i]
        transition:
            dp[i][0] = max(dp[j][0]) + 1, j->[0, i - 1] where nums[i] > nums[j]
            dp[i][1] = dp[j][1] if dp[i][0] > dp[j][0] + 1
                     = dp[i][1] + dp[j][1] if dp[i][0] == dp[j][0]
    */
    private int dpSolution(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][2];

        int countLIS = 0;
        int maxLen = 0;
        for(int i = 0; i < n; i++) {
            dp[i][0] = 1;
            dp[i][1] = 1;
            for(int j = 0; j < i; j++) {
                if(nums[i] > nums[j]) {
                    if(dp[j][0] + 1 > dp[i][0]) {
                        dp[i][0] = dp[j][0] + 1;
                        dp[i][1] = dp[j][1];
                    } else if(dp[j][0] + 1 == dp[i][0]) {
                        dp[i][1] += dp[j][1];
                    }
                }
            }

            if(dp[i][0] > maxLen) {
                maxLen = dp[i][0];
                countLIS = dp[i][1];
            } else if(dp[i][0] == maxLen) {
                countLIS += dp[i][1];
            }
        }

        return countLIS;
    }
}
