package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Problem(
        title = "Minimum Sum of Four Digit Number After Splitting Digits",
        difficulty = QDifficulty.EASY,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/minimum-sum-of-four-digit-number-after-splitting-digits/"
)
public class Q2160 {
    /**
     * Time:  O(1)
     * Space: O(1)
     * */
    public int minimumSum(int num) {
        int ans = Integer.MAX_VALUE;
        int[] f = new int[10];
        List<Integer> digits = new ArrayList<>();

        while(num != 0) {
            digits.add(num % 10);
            num /= 10;
        }

        Collections.sort(digits);

        return digits.get(0) * 10 + digits.get(2) + digits.get(1) * 10 + digits.get(3);
    }
}
