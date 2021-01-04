package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Best Time to Buy and Sell Stock",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/best-time-to-buy-and-sell-stock/"
)
public class Q121 {
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length == 0)
            return 0;
        int buy = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < prices.length; i++) {
            buy = Math.min(buy, prices[i]);
            max = Math.max(prices[i] - buy, max);
        }

        return max;
    }
}
