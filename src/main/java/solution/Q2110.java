package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Number of Smooth Descent Periods of a Stock",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/number-of-smooth-descent-periods-of-a-stock/"
)
public class Q2110 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public long getDescentPeriods(int[] prices) {
        int n = prices.length;
        long ans = 1;

        long cnt = 1;
        for(int i = 1; i < n; i++) {
            if(prices[i] - prices[i - 1] == -1) {
                cnt++;
            } else {
                cnt = 1;
            }
            ans += cnt;
        }

        return ans;
    }
}
