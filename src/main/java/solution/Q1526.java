package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimum Number of Increments on Subarrays to Form a Target Array",
        difficulty = QDifficulty.HARD,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/minimum-number-of-increments-on-subarrays-to-form-a-target-array/"
)
public class Q1526 {
    /**
     * Wall brick is the best explanation of this problem
     * 
     * Time:  O(N)
     * Space: O(N)
     * */
    public int minNumberOperations(int[] target) {
        int n = target.length;
        int res = 0;

        int pre = 0;
        int[] diff = new int[n];
        for(int i = 0; i < n; i++) {
            diff[i] = target[i] - pre;
            res += diff[i] > 0 ? diff[i] : 0;
            pre = target[i];
        }

        return res;
    }
}
