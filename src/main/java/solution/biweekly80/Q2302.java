package solution.biweekly80;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Count Subarrays With Score Less Than K",
        difficulty = QDifficulty.HARD,
        tag = QTag.SLIDING_WINDOW,
        url = "https://leetcode.com/contest/biweekly-contest-80/problems/count-subarrays-with-score-less-than-k/"
)
public class Q2302 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public long countSubarrays(int[] nums, long k) {
        int n = nums.length;
        long ans = 0;
        long rolling = 0;

        for(int l = 0, r = 0; r < n; r++) {
            rolling += nums[r];
            while(rolling * (r - l + 1) >= k) {
                rolling -= nums[l++];
            }

            ans += r - l + 1;
        }

        return ans;
    }
}
