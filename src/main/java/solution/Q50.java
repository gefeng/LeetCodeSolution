package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Pow(x, n)",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/powx-n/"
)
public class Q50 {
    public double myPow(double x, int n) {
        if(n == 0)
            return 1;
        if(n == 1)
            return x;
        if(n == -1)
            return 1/x;

        double ans = myPow(x, n / 2);
        return ans * ans * myPow(x, n % 2);
    }
}
