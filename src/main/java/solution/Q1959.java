package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimum Total Space Wasted With K Resizing Operations",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/minimum-total-space-wasted-with-k-resizing-operations/"
)
public class Q1959 {
    /**
     * Time:  O(N ^ 3)
     * Space: O(N ^ 2)
     * */
    public int minSpaceWastedKResizing(int[] nums, int k) {
        return dfs(nums, 0, k + 1, new Integer[nums.length][k + 2]);
    }

    private int dfs(int[] nums, int i, int k, Integer[][] memo) {
        if(i == nums.length) {
            return 0;
        }
        if(k == 0) {
            return Integer.MAX_VALUE;
        }

        if(memo[i][k] != null) {
            return memo[i][k];
        }

        int max = 0;
        int space = 0;
        int res = Integer.MAX_VALUE;
        for(int j = i; j < nums.length; j++) {
            max = Math.max(max, nums[j]);
            space += nums[j];

            int ret = dfs(nums, j + 1, k - 1, memo);

            res = Math.min(res, ret + (ret == Integer.MAX_VALUE ? 0 : max * (j - i + 1) - space));
        }

        return memo[i][k] = res;
    }
}
