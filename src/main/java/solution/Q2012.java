package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Sum of Beauty in the Array",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/sum-of-beauty-in-the-array/"
)
public class Q2012 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int sumOfBeauties(int[] nums) {
        int ans = 0;
        int n = nums.length;
        int[] l = new int[n];
        int[] r = new int[n];

        int max = nums[0];
        for(int i = 1; i < n; i++) {
            l[i] = max;
            max = Math.max(max, nums[i]);
        }

        int min = nums[n - 1];
        for(int i = n - 2; i >= 0; i--) {
            r[i] = min;
            min = Math.min(min, nums[i]);
        }

        for(int i = 1; i < n - 1; i++) {
            if(nums[i] > l[i] && nums[i] < r[i]) {
                ans += 2;
            } else if(nums[i] > nums[i - 1] && nums[i] < nums[i + 1]) {
                ans += 1;
            }
        }

        return ans;
    }
}
