package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Maximum Score from Performing Multiplication Operations",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/maximum-score-from-performing-multiplication-operations/"
)
public class Q1770 {
    public int maximumScore(int[] nums, int[] multipliers) {
        int n = nums.length;
        int m = multipliers.length;
        return dfs(nums, multipliers, 0, 1, new Integer[m + 1][m + 1]);
    }

    private int dfs(int[] nums, int[] multipliers, int l, int i, Integer[][] memo) {
        if(i == multipliers.length + 1) {
            return 0;
        }

        if(memo[l][i] != null) {
            return memo[l][i];
        }

        int r = nums.length - (i - l);
        int score = 0;

        score = Math.max(dfs(nums, multipliers, l + 1, i + 1, memo) + nums[l] * multipliers[i - 1],
                dfs(nums, multipliers, l, i + 1, memo) + nums[r] * multipliers[i - 1]);

        return memo[l][i] = score;
    }
}
