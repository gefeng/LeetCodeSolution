package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Tiling a Rectangle with the Fewest Squares",
        difficulty = QDifficulty.HARD,
        tag = QTag.BACKTRACKING,
        url = "https://leetcode.com/problems/tiling-a-rectangle-with-the-fewest-squares/"
)
public class Q1240 {
    /**
     * Time:  O((M * N) ^ K * K ^ 2)
     * Space: O(M * N)
     * */
    int ans = Integer.MAX_VALUE;
    int m;
    int n;
    boolean[][] g;
    public int tilingRectangle(int n, int m) {
        this.m = m;
        this.n = n;
        this.g = new boolean[n][m];

        dfs(0, 0, 0);

        return ans;
    }

    private void dfs(int r, int c, int cnt) {
        if(cnt >= ans) {
            return;
        }

        if(r == n) {
            ans = cnt;
            return;
        }

        if(c == m) {
            dfs(r + 1, 0, cnt);
            return;
        }

        if(g[r][c]) {
            dfs(r, c + 1, cnt);
            return;
        }

        int k = Math.min(n - r, m - c);
        for(int i = 1; i <= k; i++) {
            if(!canFill(r, c, i)) {
                break;
            }
            set(r, c, i, true);
            dfs(r, c + 1, cnt + 1);
            set(r, c, i, false);
        }
    }

    private boolean canFill(int r, int c, int k) {
        for(int i = 0; i < k; i++) {
            for(int j = 0; j < k; j++) {
                if(g[r + i][c + j]) {
                    return false;
                }
            }
        }
        return true;
    }
    private void set(int r, int c, int k, boolean v) {
        for(int i = 0; i < k; i++) {
            for(int j = 0; j < k; j++) {
                g[r + i][c + j] = v;
            }
        }
    }
}
