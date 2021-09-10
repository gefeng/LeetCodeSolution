package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Longest Subarray of 1's After Deleting One Element",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.SLIDING_WINDOW,
        url = "https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element/"
)
public class Q1493 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int longestSubarray(int[] nums) {
        int n = nums.length;
        int res = 0;
        int cntDelete = 0;

        for(int l = 0, r = 0; r < n; r++) {
            if(nums[r] == 0) {
                cntDelete++;
            }

            if(cntDelete > 1) {
                cntDelete = nums[l++] == 0 ? cntDelete - 1 : cntDelete;
            }

            if(cntDelete <= 1) {
                res = Math.max(res, r - l + 1);
            }
        }

        return res == 0 ? 0 : res - 1;
    }
}
