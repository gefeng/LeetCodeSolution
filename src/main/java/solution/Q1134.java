package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Armstrong Number",
        difficulty = QDifficulty.EASY,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/armstrong-number/"
)
public class Q1134 {
    public boolean isArmstrong(int n) {
        List<Integer> digits = getDigits(n);
        int k = digits.size();
        int sum = 0;
        for(int d : digits) {
            int pow = 1;
            for(int i = 0; i < k; i++) {
                pow *= d;
            }
            sum += pow;
        }

        return sum == n;
    }

    private List<Integer> getDigits(int n) {
        List<Integer> digits = new ArrayList<>();
        while(n != 0) {
            digits.add(n % 10);
            n /= 10;
        }
        return digits;
    }
}
