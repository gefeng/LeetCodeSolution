package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Maximum Profit of Operating a Centennial Wheel",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/maximum-profit-of-operating-a-centennial-wheel/"
)
public class Q1599 {
    /**
     * N: number of customers
     * Time:  O(N)
     * Space: O(1)
     * */
    public int minOperationsMaxProfit(int[] customers, int boardingCost, int runningCost) {
        int n = customers.length;
        int res = -1, maxProfit = 0;
        int gain = 0;
        int wait = 0;
        int board = 0;
        int round = 1;

        while(round <= n || wait > 0) {
            wait = round > n ? wait : wait + customers[round - 1];

            // boarding
            board = Math.min(wait, 4);
            gain += board * boardingCost;
            wait -= board;

            int profit = gain - round * runningCost;
            if(profit > maxProfit) {
                maxProfit = profit;
                res = round;
            }

            round++;
        }

        return res;
    }
}
