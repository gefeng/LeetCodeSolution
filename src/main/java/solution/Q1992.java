package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Find All Groups of Farmland",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.MATRIX,
        url = "https://leetcode.com/problems/find-all-groups-of-farmland/"
)
public class Q1992 {
    /**
     * Time:  O(M * N)
     * Space: O(M * N)
     * */
    private static final int[][] DIRECTIONS = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int[][] findFarmland(int[][] land) {
        int m = land.length;
        int n = land[0].length;
        List<int[]> farmland = new ArrayList<>();

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(land[i][j] == 1) {
                    int[] rect = new int[] {i, j, -1, -1};
                    dfs(land, i, j, rect);
                    farmland.add(rect);
                }
            }
        }

        return farmland.toArray(new int[farmland.size()][4]);
    }

    private void dfs(int[][] land, int r, int c, int[] rect) {
        int m = land.length;
        int n = land[0].length;

        rect[2] = Math.max(rect[2], r);
        rect[3] = Math.max(rect[3], c);

        land[r][c] = 0;

        for(int[] dir : DIRECTIONS) {
            int nr = r + dir[0];
            int nc = c + dir[1];
            if(nr >= 0 && nc >= 0 && nr < m && nc < n && land[nr][nc] == 1) {
                dfs(land, nr, nc, rect);
            }
        }
    }
}
