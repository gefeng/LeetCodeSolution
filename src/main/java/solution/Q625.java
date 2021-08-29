package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;


@Problem(
        title = "Minimum Factorization",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/minimum-factorization/"
)
public class Q625 {
    /**
     * Time:  O(logN)
     * Space: O(1)
     * */
    public int smallestFactorization(int num) {
        long res = 0;
        long mul = 1;
        for(int i = 9; i >= 2; i--) {
            while(num % i == 0) {
                res = mul * i + res;
                num /= i;
                mul *= 10;
            }
        }

        if(num != 1 || res > Integer.MAX_VALUE) {
            return 0;
        }

        return res == 0 ? 1 : (int)res;
    }
}
