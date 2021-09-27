package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Maximum Difference Between Increasing Elements",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/maximum-difference-between-increasing-elements/"
)
public class Q2016 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int maximumDifference(int[] nums) {
        int n = nums.length;
        int ans = -1;
        int min = nums[0];
        for(int i = 1; i < n; i++) {
            if(nums[i] > min) {
                ans = Math.max(ans, nums[i] - min);
            } else {
                min = nums[i];
            }
        }

        return ans;
    }
}
