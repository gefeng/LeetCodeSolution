package solution.biweekly77;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimum Average Difference",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.PREFIX_SUM,
        url = "https://leetcode.com/contest/biweekly-contest-77/problems/minimum-average-difference/"
)
public class Q2256 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int minimumAverageDifference(int[] nums) {
        long min = Long.MAX_VALUE;
        int ans = -1;
        int n = nums.length;
        long[] psum = new long[n + 1];

        for(int i = 1; i <= n; i++) {
            psum[i] = psum[i - 1] + nums[i - 1];
        }

        for(int i = 0; i < n; i++) {
            long l = psum[i + 1];
            long r = psum[n] - psum[i + 1];
            long la = l / (i + 1);
            long ra = n - i - 1 == 0 ? 0 : r / (n - i - 1);
            long res = Math.abs(la - ra);
            if(res < min) {
                min = res;
                ans = i;
            }
        }

        return ans;
    }
}
