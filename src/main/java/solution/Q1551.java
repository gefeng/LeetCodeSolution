package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimum Operations to Make Array Equal",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/minimum-operations-to-make-array-equal/"
)
public class Q1551 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int minOperations(int n) {
        int res = 0;
        int target = (1 + 2 * (n - 1) + 1) / 2;

        for(int i = 0; i < n / 2; i++) {
            res += target - (2 * i + 1);
        }

        return res;
    }
}
