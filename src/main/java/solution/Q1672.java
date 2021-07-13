package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Richest Customer Wealth",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/richest-customer-wealth/"
)
public class Q1672 {
    public int maximumWealth(int[][] accounts) {
        int ans = 0;

        for(int[] a : accounts) {
            int sum = 0;
            for(int n : a) {
                sum += n;
            }
            ans = Math.max(ans, sum);
        }

        return ans;
    }
}
