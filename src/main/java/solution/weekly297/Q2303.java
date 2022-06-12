package solution.weekly297;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Calculate Amount Paid in Taxes",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/contest/weekly-contest-297/problems/calculate-amount-paid-in-taxes/"
)
public class Q2303 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public double calculateTax(int[][] brackets, int income) {
        int n = brackets.length;
        double ans = 0;

        for(int i = 0; i < n; i++) {
            int[] b = brackets[i];
            int x = Math.min(income, b[0]) - (i == 0 ? 0 : brackets[i - 1][0]);

            if(x < 0) {
                break;
            }

            ans += (double)x * ((double)b[1] / 100);
        }

        return ans;
    }
}
