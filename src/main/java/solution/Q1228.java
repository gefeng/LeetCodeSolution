package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Missing Number In Arithmetic Progression",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/missing-number-in-arithmetic-progression/"
)
public class Q1228 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int missingNumber(int[] arr) {
        int sum1 = 0;
        int sum2 = 0;
        int n = arr.length;

        for(int x : arr) {
            sum1 += x;
        }

        sum2 = (arr[0] + arr[n - 1]) * (n + 1) / 2;

        return sum2 - sum1;
    }
}
