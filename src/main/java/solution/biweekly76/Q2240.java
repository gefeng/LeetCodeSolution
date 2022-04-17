package solution.biweekly76;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Number of Ways to Buy Pens and Pencils",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/contest/biweekly-contest-76/problems/number-of-ways-to-buy-pens-and-pencils/"
)
public class Q2240 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public long waysToBuyPensPencils(int total, int cost1, int cost2) {
        long ans = 0;

        for(int i = 0; i * cost1 <= total; i++) {
            int left = total - i * cost1;
            int j = left / cost2;
            ans += j + 1;
        }

        return ans;
    }
}
