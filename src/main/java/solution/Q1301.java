package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Number of Paths with Max Score",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/number-of-paths-with-max-score/"
)
public class Q1301 {
    /**
     * Time:  O(N ^ 2)
     * Space: O(N ^ 2)
     * */
    private static final int MOD = (int)1e9 + 7;
    private static final int[][] DIRECTIONS = new int[][] {{0, 1}, {1, 0}, {1, 1}};

    private char[][] g;
    private int n;
    public int[] pathsWithMaxScore(List<String> board) {
        n = board.size();
        g = new char[n][n];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                g[i][j] = board.get(i).charAt(j);
            }
        }

        return dfs(0, 0, new Integer[n][n][2]);
    }

    private int[] dfs(int r, int c, Integer[][][] memo) {
        if(r == n - 1 && c == n - 1) {
            return new int[] {0, 1};
        }

        if(memo[r][c][0] != null) {
            return new int[] {memo[r][c][0], memo[r][c][1]};
        }

        int score = Character.isDigit(g[r][c]) ? g[r][c] - '0' : 0;
        int max = 0;
        int cnt = 0;
        List<int[]> ret = new ArrayList<>();
        for(int[] dir : DIRECTIONS) {
            int nr = r + dir[0];
            int nc = c + dir[1];
            if(nr >= 0 && nc >= 0 && nr < n && nc < n && g[nr][nc] != 'X') {
                int[] p = dfs(nr, nc, memo);
                if(p[1] != 0) {
                    ret.add(dfs(nr, nc, memo));
                }
            }
        }

        for(int[] p : ret) {
            max = Math.max(p[0], max);
        }

        if(!ret.isEmpty()) {
            for(int[] p : ret) {
                if(p[0] == max) {
                    cnt = (cnt + p[1]) % MOD;
                }
            }
            max += score;
        }

        memo[r][c][0] = max;
        memo[r][c][1] = cnt;

        return new int[] {max, cnt};
    }
}
