package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Subarray Product Less Than K",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.SLIDING_WINDOW,
        url = "https://leetcode.com/problems/subarray-product-less-than-k/"
)
public class Q713 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int ans = 0;
        int prod = 1;
        int n = nums.length;

        for(int l = 0, r = 0; r < n; r++) {
            prod *= nums[r];
            while(l < n && prod >= k) {
                prod /= nums[l++];
            }

            if(l <= r) {
                ans += r - l + 1;
            }
        }

        return ans;
    }
}
