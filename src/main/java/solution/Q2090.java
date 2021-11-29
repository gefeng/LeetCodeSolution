package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "K Radius Subarray Averages",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.SLIDING_WINDOW,
        url = "https://leetcode.com/contest/weekly-contest-269/problems/k-radius-subarray-averages/"
)
public class Q2090 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int[] getAverages(int[] nums, int k) {
        long sum = 0;
        int n = nums.length;
        int[] ans = new int[n];

        Arrays.fill(ans, -1);

        for(int l = 0, r = 0; r < n; r++) {
            sum += nums[r];

            if(r - l + 1 > 2 * k + 1) {
                sum -= nums[l++];
            }

            if(r - l + 1 == 2 * k + 1) {
                ans[l + k] = (int)(sum / (2 * k + 1));
            }
        }

        return ans;
    }
}
