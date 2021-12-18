package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Magic Squares In Grid",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.MATRIX,
        url = "https://leetcode.com/problems/magic-squares-in-grid/"
)
public class Q840 {
    /**
     * Time:  O(M ^ 2 * N ^ 2)
     * Space: O(1)
     * */
    public int numMagicSquaresInside(int[][] grid) {
        int ans = 0;
        int m = grid.length;
        int n = grid[0].length;

        for(int i = 0; i <= m - 3; i++) {
            for(int j = 0; j <= n - 3; j++) {
                if(isMagic(grid, i, j)) {
                    ans++;
                }
            }
        }

        return ans;
    }

    private boolean isMagic(int[][] g, int sr, int sc) {
        boolean[] seen = new boolean[10];
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(g[sr + i][sc + j] > 9 || g[sr + i][sc + j] < 1) return false;
                seen[g[sr + i][sc + j]] = true;
            }
        }

        for(int i = 1; i < 10; i++) {
            if(!seen[i]) return false;
        }

        int t = 0;
        for(int i = 0; i < 3; i++) {
            t += g[sr + i][sc + i];
        }

        int sum = 0;
        for(int i = 0; i < 3; i++) {
            sum += g[sr + i][sc + 2 - i];
        }

        if(sum != t) return false;

        for(int i = 0; i < 3; i++) {
            sum = 0;
            for(int j = 0; j < 3; j++) {
                sum += g[sr + i][sc + j];
            }
            if(sum != t) return false;
        }

        for(int i = 0; i < 3; i++) {
            sum = 0;
            for(int j = 0; j < 3; j++) {
                sum += g[sr + j][sc + i];
            }
            if(sum != t) return false;
        }

        return true;
    }
}
