package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Find the smallest Divisor Given a Threshold",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/find-the-smallest-divisor-given-a-threshold/"
)
public class Q1283 {
    /**
     * Time:  O(N * log(max(nums)))
     * Space: O(1)
     * */
    public int smallestDivisor(int[] nums, int threshold) {
        int lo = 1;
        int hi = (int)1e6;
        int ans = 1;

        while(lo <= hi) {
            int mid = lo + hi >> 1;

            int sum = 0;
            for(int x : nums) {
                sum += (x + mid - 1) / mid;
            }

            if(sum <= threshold) {
                ans = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return ans;
    }
}
