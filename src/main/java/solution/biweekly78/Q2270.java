package solution.biweekly78;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Number of Ways to Split Array",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.PREFIX_SUM,
        url = "https://leetcode.com/contest/biweekly-contest-78/problems/number-of-ways-to-split-array/"
)
public class Q2270 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int waysToSplitArray(int[] nums) {
        int n = nums.length;
        int ans = 0;
        long[] psum = new long[n + 1];

        for(int i = 1; i <= n; i++) {
            psum[i] = psum[i - 1] + nums[i - 1];
        }

        for(int i = 0; i < n - 1; i++) {
            if(psum[i + 1] >= psum[n] - psum[i + 1]) {
                ans++;
            }
        }

        return ans;
    }
}
