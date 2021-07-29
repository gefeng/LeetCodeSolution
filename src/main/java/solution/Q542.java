package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "01 Matrix",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BREATH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/01-matrix/"
)
public class Q542 {
    private static final int[][] DIRECTIONS = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int[][] updateMatrix(int[][] mat) {
        return bottomUpDpSol(mat);
    }

    /**
     * Time:  O(M * N)
     * Space: O(M * N)
     * */
    private int[][] multiSourceBfsSol(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] res = new int[m][n];
        Queue<int[]> queue = new ArrayDeque<>();

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(mat[i][j] == 0) {
                    queue.offer(new int[] {i, j});
                } else {
                    res[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        int dist = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();

            for(int i = 0; i < size; i++) {
                int[] pos = queue.poll();
                int r = pos[0];
                int c = pos[1];

                for(int[] dir : DIRECTIONS) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];
                    if(nr >= 0 && nc >= 0 && nr < m && nc < n && mat[nr][nc] != 0) {
                        if(res[nr][nc] > dist + 1) {
                            res[nr][nc] = dist + 1;
                            queue.offer(new int[] {nr, nc});
                        }
                    }
                }
            }

            dist++;
        }

        return res;
    }

    /*
        state:
            dp[i][j] means the shortest distance from 0 to mat[i][j]
        transition:
            dp[i][j] = min(dp[neighbors] + 1)
    */
    /**
     * state:
     *  dp[i][j] means the shortest distance from 0 to mat[i][j]
     * transition:
     *  dp[i][j] = min(dp[neighbors] + 1)
     *
     * Time:  O(M * N)
     * Space: O(M * N)
     * */
    private int[][] bottomUpDpSol(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] dp = new int[m][n];

        for(int i = 0; i < m; i++) {
            Arrays.fill(dp[i], 10000);
        }

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(mat[i][j] == 0) {
                    dp[i][j] = 0;
                } else {
                    if(i > 0) {
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + 1);
                    }
                    if(j > 0) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + 1);
                    }
                }
            }
        }

        for(int i = m - 1; i >= 0; i--) {
            for(int j = n - 1; j >= 0; j--) {
                if(i < m - 1) {
                    dp[i][j] = Math.min(dp[i][j], dp[i + 1][j] + 1);
                }
                if(j < n - 1) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][j + 1] + 1);
                }
            }
        }

        return dp;
    }
}
