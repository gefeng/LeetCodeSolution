package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.Map;

@Problem(
        title = "Minimum Number of Days to Eat N Oranges",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/minimum-number-of-days-to-eat-n-oranges/"
)
public class Q1553 {
    /**
     * Time:  O(logN)
     * Space: O(logN)
     * */
    public int minDays(int n) {
        return dfs(n, new HashMap<>());
    }

    private int dfs(int n, Map<Integer, Integer> memo) {
        if(n < 3) {
            return n;
        }

        if(memo.containsKey(n)) {
            return memo.get(n);
        }

        int days = Math.min(dfs(n / 3, memo) + (n % 3), dfs(n / 2, memo) + (n % 2)) + 1;

        memo.put(n, days);

        return days;
    }
}
