package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Solving Questions With Brainpower",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/solving-questions-with-brainpower/"
)
public class Q2140 {
    public long mostPoints(int[][] questions) {
        return dfs(questions, 0, new Long[questions.length]);
    }

    private long dfs(int[][] questions, int cur, Long[] memo) {
        int n = questions.length;

        if(cur >= n) return 0;
        if(memo[cur] != null) return memo[cur];

        long skip = dfs(questions, cur + 1, memo);
        long pick = dfs(questions, cur + questions[cur][1] + 1, memo) + questions[cur][0];

        return memo[cur] = Math.max(skip, pick);
    }
}