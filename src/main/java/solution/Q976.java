package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Largest Perimeter Triangle",
        difficulty = QDifficulty.EASY,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/largest-perimeter-triangle/"
)
public class Q976 {
    /**
     * Time:  O(N * logN)
     * Space: O(logN)
     * */
    public int largestPerimeter(int[] nums) {
        int n = nums.length;

        Arrays.sort(nums);

        for(int i = n - 1; i > 1; i--) {
            if(nums[i - 2] + nums[i - 1] > nums[i]) {
                return nums[i - 2] + nums[i - 1] + nums[i];
            }
        }

        return 0;
    }
}
