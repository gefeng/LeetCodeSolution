package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "2 Keys Keyboard",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/2-keys-keyboard/"
)
public class Q650 {
    /**
     * Time:  O(N ^ 2)
     * Space: O(N ^ 2)
     * */
    public int minSteps(int n) {
        return dfs(n, 1, 0, new Integer[2 * n + 5][2 * n + 5]);
    }

    private int dfs(int n, int cur, int buffer, Integer[][] memo) {
        if(cur == n) return 0;
        if(cur > n) return n + 1;

        if(memo[cur][buffer] != null) return memo[cur][buffer];

        int min = n + 1;
        // paste
        if(buffer != 0)
            min = Math.min(min, dfs(n, cur + buffer, buffer, memo) + 1);

        min = Math.min(min, dfs(n, cur * 2, cur, memo) + 2);

        return memo[cur][buffer] = min;
    }
}
