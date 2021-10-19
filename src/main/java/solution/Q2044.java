package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.Map;

@Problem(
        title = "Count Number of Maximum Bitwise-OR Subsets",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BACKTRACKING,
        url = "https://leetcode.com/problems/count-number-of-maximum-bitwise-or-subsets/"
)
public class Q2044 {
    /**
     * Time:  O(2 ^ N)
     * Space: O(2 ^ N)
     * */
    public int countMaxOrSubsets(int[] nums) {
        int n = nums.length;
        int max = 0;
        int ans = 0;
        for(int i = 1; i < (1 << n); i++) {
            int or = 0;
            for(int j = 0; j < n; j++) {
                if(((1 << j) & i) != 0) {
                    or |= nums[j];
                }
            }
            if(or == max) {
                ans++;
            } else if(or > max) {
                ans = 1;
                max = or;
            }
        }

        return ans;
    }
}
