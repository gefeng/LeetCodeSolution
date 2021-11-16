package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.Comparator;

@Problem(
        title = "Matrix Cells in Distance Order",
        difficulty = QDifficulty.EASY,
        tag = QTag.SORT,
        url = "https://leetcode.com/problems/matrix-cells-in-distance-order/"
)
public class Q1030 {
    /**
     * Time:  O(M * N * log(M * N))
     * Space: O(M * N)
     * */
    public int[][] allCellsDistOrder(int rows, int cols, int rCenter, int cCenter) {
        int m = rows;
        int n = cols;

        int[][] g = new int[m * n][2];

        for(int i = 0, k = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                g[k++] = new int[] {i, j};
            }
        }

        Arrays.sort(g, Comparator.comparingInt(a -> Math.abs(a[0] - rCenter) + Math.abs(a[1] - cCenter)));

        return g;
    }
}
