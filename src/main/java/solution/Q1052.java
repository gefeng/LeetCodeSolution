package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Grumpy Bookstore Owner",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.SLIDING_WINDOW,
        url = "https://leetcode.com/problems/grumpy-bookstore-owner/"
)
public class Q1052 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int ans = 0;
        int n = customers.length;
        int tot = 0;
        int[] preSum = new int[n + 1];

        for(int i = 1; i <= n; i++) {
            tot += grumpy[i - 1] == 0 ? customers[i - 1] : 0;
            preSum[i] = preSum[i - 1] + (grumpy[i - 1] == 1 ? customers[i - 1] : 0);
        }

        for(int i = 0; i <= n - minutes; i++) {
            int sum = tot + (preSum[i + minutes] - preSum[i]);
            ans = Math.max(ans, sum);
        }

        return ans;
    }
}
