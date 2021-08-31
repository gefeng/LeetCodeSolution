package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimum Number of Work Sessions to Finish the Tasks",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/minimum-number-of-work-sessions-to-finish-the-tasks/"
)
public class Q1986 {
    /**
     * Time:  O(2 ^ N * N)
     * Space: O(2 ^ N)
     * */
    public int minSessions(int[] tasks, int sessionTime) {
        int n = tasks.length;
        return dfs(tasks, sessionTime, 0, 0, new Integer[sessionTime + 1][1 << n]);
    }

    private int dfs(int[] tasks, int st, int mask, int t, Integer[][] memo) {
        if(mask == ((1 << tasks.length) - 1)) {
            return 1;
        }

        if(memo[t][mask] != null) {
            return memo[t][mask];
        }

        int min = Integer.MAX_VALUE;
        for(int i = 0; i < tasks.length; i++) {
            if((mask & (1 << i)) == 0) {
                if(tasks[i] + t > st) {
                    min = Math.min(min, dfs(tasks, st, mask | (1 << i), tasks[i], memo) + 1);
                } else {
                    min = Math.min(min, dfs(tasks, st, mask | (1 << i), t + tasks[i], memo));
                }
            }
        }

        return memo[t][mask] = min;
    }
}
