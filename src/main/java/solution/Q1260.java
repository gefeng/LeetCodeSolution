package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

@Problem(
        title = "Shift 2D Grid",
        difficulty = QDifficulty.EASY,
        tag = QTag.MATRIX,
        url = "https://leetcode.com/problems/shift-2d-grid/"
)
public class Q1260 {
    /**
     * Time:  O(M * N)
     * Space: O(M * N)
     * */
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        List<List<Integer>> ans = new ArrayList<>();

        for(int i = 0; i < m; i++) {
            List<Integer> row = new ArrayList<>();
            for(int j = 0; j < n; j++) {
                row.add(0);
            }
            ans.add(row);
        }

        k = k % (m * n);
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                int ni = (((i * n + j) + k) % (m * n)) / n;
                int nj = (((i * n + j) + k) % (m * n)) % n;
                ans.get(ni).set(nj, grid[i][j]);
            }
        }

        return ans;
    }
}
