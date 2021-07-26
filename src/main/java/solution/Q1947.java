package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Maximum Compatibility Score Sum",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/maximum-compatibility-score-sum/"
)
public class Q1947 {
    /**
     * Time:  O(N * 2 ^ N * N ^ 2) can be improved by pre-compute scores
     * Space: O(N * 2 ^ N)
     * */
    public int maxCompatibilitySum(int[][] students, int[][] mentors) {
        int m = students.length;
        return dfs(students, mentors, 0, 0, new Integer[m][1 << m]);
    }

    private int dfs(int[][] s, int[][] m, int idx, int state, Integer[][] memo) {
        if(idx == s.length) {
            return 0;
        }

        if(memo[idx][state] != null) {
            return memo[idx][state];
        }

        int max = 0;
        for(int i = 0; i < m.length; i++) {
            if(((1 << i) & state) != 0) {
                continue;
            }

            max = Math.max(max, dfs(s, m, idx + 1, state | (1 << i), memo) + getScore(s[idx], m[i]));
        }

        return memo[idx][state] = max;
    }

    private int getScore(int[] s, int[] m) {
        int score = 0;
        for(int i = 0; i < s.length; i++) {
            if(s[i] == m[i]) {
                score++;
            }
        }

        return score;
    }
}
