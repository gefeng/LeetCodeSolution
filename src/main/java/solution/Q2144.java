package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Minimum Cost of Buying Candies With Discount",
        difficulty = QDifficulty.EASY,
        tag = QTag.SORT,
        url = "https://leetcode.com/problems/minimum-cost-of-buying-candies-with-discount/"
)
public class Q2144 {
    /**
     * Time:  O(N * logN)
     * Space: O(logN)
     * */
    public int minimumCost(int[] cost) {
        int n = cost.length;
        int ans = 0;
        Arrays.sort(cost);

        for(int i = n - 1; i >= 0; i -= 3) {
            ans += cost[i];
            if(i - 1 >= 0) ans += cost[i - 1];
        }

        return ans;
    }
}
