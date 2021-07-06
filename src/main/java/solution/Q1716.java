package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Calculate Money in Leetcode Bank",
        difficulty = QDifficulty.EASY,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/calculate-money-in-leetcode-bank/"
)
public class Q1716 {
    public int totalMoney(int n) {
        int k = n / 7;
        int r = n % 7;
        int sum = 0;

        int base = 28;
        for(int i = 0; i < k; i++) {
            sum += base;
            base += 7;
        }

        base = k + 1;
        for(int i = 0; i < r; i++) {
            sum += base;
            base++;
        }

        return sum;
    }
}
