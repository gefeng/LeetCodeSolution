package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Longest Increasing Path in a Matrix",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/longest-increasing-path-in-a-matrix/"
)
public class Q329 {
    /**
     * Time:  O(M * N)
     * Space: O(M * N)
     * */
    int[][] DIRS = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    int m = 0;
    int n = 0;
    public int longestIncreasingPath(int[][] matrix) {
        m = matrix.length;
        n = matrix[0].length;
        int ans = 1;
        Integer[][] memo = new Integer[m][n];

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                ans = Math.max(ans, dfs(matrix, i, j, memo));
            }
        }

        return ans;
    }

    private int dfs(int[][] g, int r, int c, Integer[][] memo) {
        if (memo[r][c] != null) {
            return memo[r][c];
        }

        int max = 1;
        for (int[] dir : DIRS) {
            int nr = r + dir[0];
            int nc = c + dir[1];
            if (nr >= 0 && nc >= 0 && nr < m && nc < n && g[nr][nc] > g[r][c]) {
                max = Math.max(max, dfs(g, nr, nc, memo) + 1);
            }
        }

        return memo[r][c] = max;
    }
}
