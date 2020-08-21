package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Best Time to Buy and Sell Stock III",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/"
)
public class Q123 {
    public int maxProfit(int[] prices) {
        int cost1 = Integer.MAX_VALUE;
        int cost2 = Integer.MAX_VALUE;
        int profit1 = 0;
        int profit2 = 0;
        for(int p : prices) {
            cost1 = Math.min(cost1, p);
            profit1 = Math.max(profit1, p - cost1);
            cost2 = Math.min(cost2, p - profit1);
            profit2 = Math.max(profit2, p - cost2);
        }
        return profit2;
    }
}
