package solution.biweekly82;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Subarray With Elements Greater Than Varying Threshold",
        difficulty = QDifficulty.HARD,
        tag = QTag.RECURSION,
        url = "https://leetcode.com/contest/biweekly-contest-82/problems/subarray-with-elements-greater-than-varying-threshold/"
)
public class Q2334 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int validSubarraySize(int[] nums, int threshold) {
        int n = nums.length;
        return dfs(nums, 0, n - 1, threshold);
    }

    private int dfs(int[] nums, int l, int r, int t) {
        if(l > r) {
            return -1;
        }
        if(l == r) {
            return nums[l] > t ? 1 : -1;
        }

        int n = r - l + 1;
        for(int i = l; i <= r; ) {
            if(nums[i] <= t / n) {
                i++;
                continue;
            }
            int j = i;
            int min = Integer.MAX_VALUE;
            while(i <= r && nums[i] > t / n) {
                min = Math.min(min, nums[i]);
                i++;
            }

            if(min > t / (i - j)) {
                return i - j;
            }

            int res = dfs(nums, j, i - 1, t);

            if(res != -1) {
                return res;
            }
        }

        return -1;
    }
}
