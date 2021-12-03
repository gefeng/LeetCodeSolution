package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Smallest Range II",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/smallest-range-ii/"
)
public class Q910 {
    /**
     * We always increase a[0..i] and decrease a[i + 1, n - 1].
     * The problem is how we can find such i which minimizes max - min.
     *
     * Time:  O(N * logN)
     * Space: O(logN)
     * */
    public int smallestRangeII(int[] nums, int k) {
        int n = nums.length;
        int ans = 0;

        Arrays.sort(nums);

        ans = nums[n - 1] - nums[0];

        for(int i = 0; i < n - 1; i++) {
            int min = Math.min(nums[0] + k, nums[i + 1] - k);
            int max = Math.max(nums[i] + k, nums[n - 1] - k);
            ans = Math.min(ans, max - min);
        }

        return ans;
    }
}
