package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Cells with Odd Values in a Matrix",
        difficulty = QDifficulty.EASY,
        tag = QTag.MATRIX,
        url = "https://leetcode.com/problems/cells-with-odd-values-in-a-matrix/"
)
public class Q1252 {
    /**
     * Time:  O(M + N + K)
     * Space: O(M + N)
     * */
    public int oddCells(int m, int n, int[][] indices) {
        int ans = 0;
        int[] rows = new int[m];
        int[] cols = new int[n];

        for(int[] p : indices) {
            int r = p[0], c = p[1];
            rows[r]++;
            cols[c]++;
        }

        int cntOdd = 0;
        int cntEve = 0;
        for(int x : rows) {
            if(x % 2 == 1) {
                ans += n;
                cntOdd += 1;
            } else {
                cntEve += 1;
            }
        }

        for(int x : cols) {
            if(x % 2 == 1) {
                ans += cntEve;
                ans -= cntOdd;
            }
        }

        return ans;
    }
}
