package solution.weekly284;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Maximize the Topmost Element After K Moves",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/contest/weekly-contest-284/problems/maximize-the-topmost-element-after-k-moves/"
)
public class Q2202 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int maximumTop(int[] nums, int k) {
        int n = nums.length;

        if(n == 1 && k % 2 == 1) return -1;

        if(k > n) {
            int max = 0;
            for(int x : nums) {
                max = Math.max(max, x);
            }

            return max;
        } else {
            int max = -1;
            for(int i = 0; i < k - 1; i++) {
                max = Math.max(max, nums[i]);
            }

            return k == n ? max : Math.max(max, nums[k]);
        }
    }
}
