package solution.biweekly76;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Find Closest Number to Zero",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/contest/biweekly-contest-76/problems/find-closest-number-to-zero/"
)
public class Q2239 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int findClosestNumber(int[] nums) {
        int ans = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for(int x : nums) {
            int abs = Math.abs(x);
            if(abs < min) {
                min = abs;
                ans = x;
            } else if(abs == min) {
                ans = Math.max(ans, x);
            }
        }

        return ans;
    }
}
