package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Predict the Winner",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GAME_THEORY,
        url = "https://leetcode.com/problems/predict-the-winner/"
)
public class Q486 {
    /**
     * Minmax.
     * Each player should either minimize the lost or maximize the profit.
     *
     * Time:  O(N ^ 2)
     * Space: O(N ^ 2)
     * */
    public boolean PredictTheWinner(int[] nums) {
        int n = nums.length;
        return dfs(nums, 0, n - 1, new Integer[n][n]) >= 0;
    }

    private int dfs(int[] nums, int i, int j, Integer[][] memo) {
        if(i == j) {
            return nums[i];
        }

        if(memo[i][j] != null) {
            return memo[i][j];
        }

        int max = Math.max(nums[i] - dfs(nums, i + 1, j, memo), nums[j] - dfs(nums, i, j - 1, memo));

        return memo[i][j] = max;
    }
}
