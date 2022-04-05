package solution.biweekly75;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Find Triangular Sum of an Array",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/contest/biweekly-contest-75/problems/find-triangular-sum-of-an-array/"
)
public class Q2221 {
    /**
     * Time:  O(N ^ 2)
     * Space: O(1)
     * */
    public int triangularSum(int[] nums) {
        int n = nums.length;

        for(int i = n; i > 1; i--) {
            for(int j = 0; j < i - 1; j++) {
                nums[j] = (nums[j] + nums[j + 1]) % 10;
            }
        }

        return nums[0];
    }
}
