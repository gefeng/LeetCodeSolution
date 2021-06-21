package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Count Sub Islands",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DEPTH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/count-sub-islands/"
)
public class Q1905 {
    private static final int[][] DIRECTIONS = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private int m;
    private int n;
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        m = grid1.length;
        n = grid1[0].length;
        int cnt = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid2[i][j] == 1 && dfs(grid1, grid2, i, j)) {
                    cnt++;
                }
            }
        }

        return cnt;
    }

    private boolean dfs(int[][] grid1, int[][] grid2, int sr, int sc) {
        boolean isSub = grid1[sr][sc] == 1;

        grid2[sr][sc] = 0;

        for(int[] dir : DIRECTIONS) {
            int nr = sr + dir[0];
            int nc = sc + dir[1];
            if(nr >= 0 && nc >= 0 && nr < m && nc < n && grid2[nr][nc] == 1) {
                isSub &= dfs(grid1, grid2, nr, nc);
                //ret = (ret && dfs(grid1, grid2, nr, nc)); // dont use the expression, java will evaluate ret first if true second condition will be dropped
            }
        }

        return isSub;
    }
}
