package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Deque;

@Problem(
        title = "Final Prices With a Special Discount in a Shop",
        difficulty = QDifficulty.EASY,
        tag = QTag.STACK,
        url = "https://leetcode.com/problems/final-prices-with-a-special-discount-in-a-shop/"
)
public class Q1475 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int[] finalPrices(int[] prices) {
        int n = prices.length;
        int[] ans = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();

        for(int i = 0; i < n; i++) {
            while(!stack.isEmpty() && prices[stack.peek()] >= prices[i]) {
                ans[stack.pop()] -= prices[i];
            }

            ans[i] = prices[i];
            stack.push(i);
        }

        return ans;
    }
}
