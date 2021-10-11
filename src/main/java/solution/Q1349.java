package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Maximum Students Taking Exam",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/maximum-students-taking-exam/"
)
public class Q1349 {
    public int maxStudents(char[][] seats) {
        return bottomUpSol(seats);
    }
    
    /**
     * state:
     *  dp[i][j] denotes max seats of first i rows where ith rows state is j
     * transition:
     *  dp[i][j] = max(dp[i - 1][k] + bitCount(j)) for each valid k and j
     *
     * Time:  O(M * N * 2 ^ N * 2 ^ N)
     * Space: O(M * 2 ^ N)
     * */
    private int bottomUpSol(char[][] seats) {
        int m = seats.length;
        int n = seats[0].length;
        int ans = 0;

        int[][] dp = new int[m + 1][1 << n];

        for(int i = 1; i <= m; i++) {
            for(int j = 0; j < (1 << n); j++) {
                int max = 0;
                int cnt = Integer.bitCount(j);
                for(int k = 0; k < (1 << n); k++) {
                    if(isValid(seats, i - 1, k, j)) {
                        max = Math.max(max, dp[i - 1][k] + cnt);
                    }
                }
                dp[i][j] = max;
            }
        }

        for(int i = 0; i < (1 << n); i++) {
            ans = Math.max(ans, dp[m][i]);
        }

        return ans;
    }

    private int topDownSol(char[][] seats) {
        int m = seats.length;
        int n = seats[0].length;

        return dfs(seats, 0, 0, new Integer[m][1 << n]);
    }

    private int dfs(char[][] s, int cur, int pre, Integer[][] memo) {
        int m = s.length;
        int n = s[0].length;

        if(cur == m) {
            return 0;
        }

        if(memo[cur][pre] != null) {
            return memo[cur][pre];
        }

        int max = 0;
        for(int i = 0; i < (1 << n); i++) {
            if(isValid(s, cur, pre, i)) {
                max = Math.max(max, dfs(s, cur + 1, i, memo) + Integer.bitCount(i));
            }
        }

        return memo[cur][pre] = max;
    }

    private boolean isValid(char[][] s, int r, int preM, int curM) {
        int m = s.length;
        int n = s[0].length;

        for(int i = 0; i < n; i++) {
            if(((1 << i) & curM) != 0) {
                if(s[r][i] == '#') {
                    return false;
                }

                if(i > 0 && (((1 << i - 1) & curM) != 0 || ((1 << i - 1) & preM) != 0)) {
                    return false;
                }

                if(i < n - 1 && (((1 << i + 1) & curM) != 0 || ((1 << i + 1) & preM) != 0)) {
                    return false;
                }
            }
        }

        return true;
    }
}
