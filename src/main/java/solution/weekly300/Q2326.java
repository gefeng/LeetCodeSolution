package solution.weekly300;

import annotations.Problem;
import data_structure.ListNode;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Spiral Matrix IV",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.MATRIX,
        url = "https://leetcode.com/contest/weekly-contest-300/problems/spiral-matrix-iv/"
)
public class Q2326 {
    /**
     * Time:  O(M * N)
     * Space: O(M * N)
     * */
    public int[][] spiralMatrix(int m, int n, ListNode head) {
        int[][] g = new int[m][n];
        int[][] DIR = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        for(int i = 0; i < m; i++) {
            Arrays.fill(g[i], -2);
        }

        int cnt = 0;
        int r = 0;
        int c = 0;
        int d = 0;
        ListNode p = head;
        while(cnt < m * n) {
            int v = p == null ? -1 : p.val;
            g[r][c] = v;

            p = p == null ? p : p.next;

            int nr = r + DIR[d][0];
            int nc = c + DIR[d][1];

            if(nr < 0 || nc < 0 || nr == m || nc == n || g[nr][nc] != -2) {
                d = (d + 1) % 4;
                nr = r + DIR[d][0];
                nc = c + DIR[d][1];
            }

            r = nr;
            c = nc;
            cnt++;
        }

        return g;
    }
}
