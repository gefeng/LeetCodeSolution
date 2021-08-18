package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Rotate Function",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/rotate-function/"
)
public class Q396 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int maxRotateFunction(int[] nums) {
        int n = nums.length;
        int res = 0;
        int fk = 0;
        int sum = 0;

        for(int i = 0; i < n; i++) {
            sum += nums[i];
            fk += nums[i] * i;
        }

        res = fk;

        for(int i = n - 1; i > 0; i--) {
            fk = fk + sum - nums[i] - nums[i] * (n - 1);
            res = Math.max(res, fk);
        }

        return res;
    }
}
