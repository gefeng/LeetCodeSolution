package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Maximum Average Subarray I",
        difficulty = QDifficulty.EASY,
        tag = QTag.SLIDING_WINDOW,
        url = "https://leetcode.com/problems/maximum-average-subarray-i/"
)
public class Q643 {
    public double findMaxAverage(int[] nums, int k) {
        double ans = Double.NEGATIVE_INFINITY;
        int n = nums.length;

        int sum = 0;
        for(int l = 0, r = 0; r < n; r++) {
            sum += nums[r];
            if(r - l + 1 > k) {
                sum -= nums[l++];
            }
            if(r - l + 1 == k) {
                double avg = (double)sum / k;
                ans = Math.max(ans, avg);
            }
        }

        return ans;
    }
}
