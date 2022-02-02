package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Maximum Average Subarray II",
        difficulty = QDifficulty.HARD,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/maximum-average-subarray-ii/"
)
public class Q644 {
    /**
     * Given an average, check to see if any subarray with length >= k has average >= given average.
     *
     * Math:
     *  (a1 + a2 + ... ak) / k >= avg
     *  (a1 + a2 + ... ak) - avg * k >= 0
     *  (a1 - avg) + (a2 - avg) + ... (ak - avg) >= 0
     *
     * Make sure the window size is >= k and save the smallest prefix sum min_psum between [0, i - k + 1].
     * Keep checking if psum[i] - min_psum >= 0
     *
     * Time:  O(N * log((max - min) * 1e5))
     * Space: O(N)
     * */
    public double findMaxAverage(int[] nums, int k) {
        int n = nums.length;
        double ans = 0;
        double lo = -10000;
        double hi = 10000;
        double exp = 1e-9;

        while(hi - lo >= exp) {
            double mid = (hi + lo) / 2;
            if(isOk(nums, k, mid)) {
                ans = mid;
                lo = mid;
            } else {
                hi = mid;
            }
        }

        return ans;
    }

    private boolean isOk(int[] nums, int k, double avg) {
        int n = nums.length;
        double[] psum = new double[n + 1];
        for(int i = 1; i <= n; i++) {
            psum[i] = psum[i - 1] + nums[i - 1] - avg;
        }

        double min = 0;
        for(int i = k; i <= n; i++) {
            if(psum[i] - min >= 0) return true;

            min = Math.min(min, psum[i - k + 1]);
        }

        return false;
    }
}
