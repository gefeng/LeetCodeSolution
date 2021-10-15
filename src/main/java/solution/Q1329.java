package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Problem(
        title = "Sort the Matrix Diagonally",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.SORT,
        url = "https://leetcode.com/problems/sort-the-matrix-diagonally/"
)
public class Q1329 {
    /**
     * Time:  O(M * N * log(M * N))
     * Space: O(M * N)
     * */
    public int[][] diagonalSort(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] ans = new int[m][n];

        List<List<Integer>> diags = new ArrayList<>();

        for(int i = 0; i < m; i++) {
            int r = i;
            int c = 0;
            List<Integer> d = new ArrayList<>();

            while(r < m && c < n) {
                d.add(mat[r++][c++]);
            }

            Collections.sort(d);
            diags.add(d);
        }

        for(int i = 1; i < n; i++) {
            int r = 0;
            int c = i;
            List<Integer> d = new ArrayList<>();

            while(r < m && c < n) {
                d.add(mat[r++][c++]);
            }

            Collections.sort(d);
            diags.add(d);
        }

        int k = 0;
        for(int i = 0; i < m; i++) {
            int r = i;
            int c = 0;
            while(r < m && c < n) {
                ans[r][c] = diags.get(k).get(c);
                r++;
                c++;
            }
            k++;
        }

        for(int i = 1; i < n; i++) {
            int r = 0;
            int c = i;
            while(r < m && c < n) {
                ans[r][c] = diags.get(k).get(r);
                r++;
                c++;
            }
            k++;
        }

        return ans;
    }
}
