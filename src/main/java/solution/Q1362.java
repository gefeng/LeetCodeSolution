package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Closest Divisors",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/closest-divisors/"
)
public class Q1362 {
    /**
     * Time:  O(sqrt(N))
     * Space: O(1)
     * */
    public int[] closestDivisors(int num) {
        int[] x = getDivisors(num + 1);
        int[] y = getDivisors(num + 2);

        return Math.abs(x[0] - x[1]) < Math.abs(y[0] - y[1]) ? x : y;
    }

    private int[] getDivisors(int num) {
        int[] ans = new int[2];
        for(int i = 1; i * i <= num; i++) {
            if(num % i == 0) {
                ans[0] = i;
            }
        }
        ans[1] = num / ans[0];
        return ans;
    }
}
