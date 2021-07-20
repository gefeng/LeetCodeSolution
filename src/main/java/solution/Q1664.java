package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Ways to Make a Fair Array",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/ways-to-make-a-fair-array/"
)
public class Q1664 {
    /**
     * [left] nums[i] [right]
     * 1. calculate suffix sum for right part
     * 2. calculate prefix sum for left part
     * 
     * Time:  O(N)
     * Space: O(N)
     * */
    public int waysToMakeFair(int[] nums) {
        int n = nums.length;
        int res = 0;

        int[] se = new int[n + 1];
        int[] so = new int[n + 1];

        for(int i = n - 1; i >= 0; i--) {
            if(i % 2 == 0) {
                se[i] = se[i + 1] + nums[i];
                so[i] = so[i + 1];
            } else {
                so[i] = so[i + 1] + nums[i];
                se[i] = se[i + 1];
            }
        }

        int pe = 0;
        int po = 0;
        for(int i = 0; i < n; i++) {
            res = pe + so[i + 1] == po + se[i + 1] ? res + 1 : res;
            if(i % 2 == 0) {
                pe += nums[i];
            } else {
                po += nums[i];
            }
        }
        return res;
    }
}
