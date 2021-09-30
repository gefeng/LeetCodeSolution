package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimum Value to Get Positive Step by Step Sum",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/minimum-value-to-get-positive-step-by-step-sum/"
)
public class Q1413 {
    /**
     * Time:  O(N * log(sum(nums)))
     * Space: O(1)
     * */
    public int minStartValue(int[] nums) {
        int n = nums.length;
        int lo = 1;
        int hi = 20000;
        int ans = 1;
        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            boolean valid = true;
            int sum = mid;
            for(int i = 0; i < n; i++) {
                sum += nums[i];
                if(sum < 1) {
                    valid = false;
                    break;
                }
            }

            if(valid) {
                hi = mid - 1;
                ans = mid;
            } else {
                lo = mid + 1;
            }
        }

        return ans;
    }
}
