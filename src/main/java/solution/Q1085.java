package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Sum of Digits in the Minimum Number",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/sum-of-digits-in-the-minimum-number/"
)
public class Q1085 {
    /**
     * Time:  O(1)
     * Space: O(1)
     * */
    public int sumOfDigits(int[] nums) {
        int min = 101;
        for(int x : nums) {
            min = Math.min(min, x);
        }
        int sum = 0;
        while(min != 0) {
            sum += min % 10;
            min /= 10;
        }

        return sum % 2 == 0 ? 1 : 0;
    }
}
