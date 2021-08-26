package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Longest Line of Consecutive One in Matrix",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.MATRIX,
        url = "https://leetcode.com/problems/longest-line-of-consecutive-one-in-matrix/"
)
public class Q562 {
    /**
     * Time:  O(M * N)
     * Space: O(1)
     * */
    public int longestLine(int[][] mat) {
        return dpSol(mat);
    }

    private int bruteForceSol(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int res = 0;

        for(int i = 0; i < m; i++) {
            int cnt = 0;
            for(int j = 0; j < n; j++) {
                if(mat[i][j] == 1) {
                    cnt++;
                } else {
                    cnt = 0;
                }
                res = Math.max(res, cnt);
            }
        }

        for(int i = 0; i < n; i++) {
            int cnt = 0;
            for(int j = 0; j < m; j++) {
                if(mat[j][i] == 1) {
                    cnt++;
                } else {
                    cnt = 0;
                }
                res = Math.max(res, cnt);
            }
        }

        for(int i = 0; i < m; i++) {
            int cnt = 0;
            for(int r = i, c = 0; r >= 0 && c < n; r--, c++) {
                if(mat[r][c] == 1) {
                    cnt++;
                } else {
                    cnt = 0;
                }
                res = Math.max(res, cnt);
            }
        }

        for(int i = 1; i < n; i++) {
            int cnt = 0;
            for(int r = m - 1, c = i; r >= 0 && c < n; r--, c++) {
                if(mat[r][c] == 1) {
                    cnt++;
                } else {
                    cnt = 0;
                }
                res = Math.max(res, cnt);
            }
        }

        for(int i = 0; i < m; i++) {
            int cnt = 0;
            for(int r = i, c = 0; r < m && c < n; r++, c++) {
                if(mat[r][c] == 1) {
                    cnt++;
                } else {
                    cnt = 0;
                }
                res = Math.max(res, cnt);
            }
        }

        for(int i = 1; i < n - 1; i++) {
            int cnt = 0;
            for(int r = 0, c = i; r < m && c < n; r++, c++) {
                if(mat[r][c] == 1) {
                    cnt++;
                } else {
                    cnt = 0;
                }
                res = Math.max(res, cnt);
            }
        }

        return res;
    }

    /**
     * state:
     *  dp[i][j][0] denotes consecutive one's in row ends with cell (i, j)
     *  dp[i][j][1] denotes consecutive one's in column ends with cell (i, j)
     *  dp[i][j][2] denotes consecutive one's in diagonal ends with cell (i, j)
     *  dp[i][j][3] denotes consecutive one's in anti-diagonal ends with cell (i, j)
     * */
    private int dpSol(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int res = 0;
        int[][][] dp = new int[m][n][4];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(mat[i][j] == 0) {
                    dp[i][j][0] = 0;
                    dp[i][j][1] = 0;
                    dp[i][j][2] = 0;
                    dp[i][j][3] = 0;
                } else {
                    dp[i][j][0] = i == 0 ? 1 : dp[i - 1][j][0] + 1;
                    dp[i][j][1] = j == 0 ? 1 : dp[i][j - 1][1] + 1;
                    dp[i][j][2] = (i == 0 || j == 0) ?  1 : dp[i - 1][j - 1][2] + 1;
                    dp[i][j][3] = (i == 0 || j == n - 1) ? 1 : dp[i - 1][j + 1][3] + 1;
                }

                for(int k = 0; k < 4; k++) {
                    res = Math.max(res, dp[i][j][k]);
                }
            }
        }

        return res;
    }
}
