package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Maximum Number of Consecutive Values You Can Make",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/maximum-number-of-consecutive-values-you-can-make/"
)
public class Q1798 {
    /*
        Cool idea... O(n^2) dp 0/1 knapsack solution got TLE
        sort the coins first
        Let's say we can make 0, 1, 2, 3 and current coin is x,
        1. if x == 4 then we are happy because we can make 4.
        2. if x < 4  then we are happy because we can make all the value less than 4 which means
           there must be some coins can make 4 - x.
        3. if x > 4  then we are unhappy because there is no way we can make 4 since the coins are                  sorted.
    */
    public int getMaximumConsecutive(int[] coins) {
        Arrays.sort(coins);

        int n = coins.length;
        int maxCanMake = 0;
        for(int i = 0; i < n; i++) {
            if(coins[i] > maxCanMake + 1) {
                break;
            }

            maxCanMake += coins[i];
        }

        return maxCanMake + 1;
    }
}
