package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Subtract the Product and Sum of Digits of an Integer",
        difficulty = QDifficulty.EASY,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/subtract-the-product-and-sum-of-digits-of-an-integer/"
)
public class Q1281 {
    /**
     * Time:  O(1)
     * Space: O(1)
     * */
    public int subtractProductAndSum(int n) {
        int sum = 0;
        int prod = 1;

        while(n != 0) {
            int d = n % 10;
            sum += d;
            prod *= d;
            n /= 10;
        }

        return prod - sum;
    }
}
