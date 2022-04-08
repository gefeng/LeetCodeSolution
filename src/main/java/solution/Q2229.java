package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Check if an Array Is Consecutive",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/check-if-an-array-is-consecutive/"
)
public class Q2229 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public boolean isConsecutive(int[] nums) {
        int n = nums.length;
        int x = Integer.MAX_VALUE;
        int cnt = 0;
        int cntZero = 0;

        for(int i = 0; i < n; i++) {
            x = Math.min(x, nums[i]);
        }

        for(int i = 0; i < n; i++) {
            int v = Math.abs(nums[i]) - x;

            if(v > n - 1) {
                return false;
            }

            if(nums[i] == 0) {
                cntZero++;
            }

            if(nums[v] >= 0) {
                nums[v] = -nums[v];
                cnt++;
            }
        }

        if(cntZero > 1) {
            return false;
        }

        return cnt == n;
    }
}
