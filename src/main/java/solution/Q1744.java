package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Can You Eat Your Favorite Candy on Your Favorite Day?",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/can-you-eat-your-favorite-candy-on-your-favorite-day/"
)
public class Q1744 {
    public boolean[] canEat(int[] candiesCount, int[][] queries) {
        int m = candiesCount.length;
        int n = queries.length;
        long[] prefixSum = new long[m + 1];
        for(int i = 1; i < m + 1; i++) {
            prefixSum[i] = prefixSum[i - 1] + candiesCount[i - 1];
        }

        boolean[] ans = new boolean[n];
        for(int i = 0; i < n; i++) {
            int[] q = queries[i];

            ans[i] = (q[1] + 1 <= prefixSum[q[0] + 1]) && ((long)q[2] * (q[1] + 1) > prefixSum[q[0]]);
        }

        return ans;
    }
}
