package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Minimum Increment to Make Array Unique",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/minimum-increment-to-make-array-unique/"
)
public class Q945 {
    /**
     * Time:  O(N * logN)
     * Space: O(logN)
     * */
    public int minIncrementForUnique(int[] nums) {
        int n = nums.length;
        int ans = 0;

        Arrays.sort(nums);

        for(int i = 1; i < n; i++) {
            if(nums[i] <= nums[i - 1]) {
                ans += nums[i - 1] + 1 - nums[i];
                nums[i] = nums[i - 1] + 1;
            }
        }

        return ans;
    }
}
