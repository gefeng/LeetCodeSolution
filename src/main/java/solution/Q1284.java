package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Queue;

@Problem(
        title = "Minimum Number of Flips to Convert Binary Matrix to Zero Matrix",
        difficulty = QDifficulty.HARD,
        tag = QTag.BREATH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/minimum-number-of-flips-to-convert-binary-matrix-to-zero-matrix/"
)
public class Q1284 {
    /**
     * Time:  O(M * N * 2 ^ (M * N))
     * Space: O(2 ^ (M * N))
     * */
    private static final int[][] DIRECTIONS = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private int m;
    private int n;
    public int minFlips(int[][] mat) {
        m = mat.length;
        n = mat[0].length;
        int ans = -1;

        int init = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                init |= mat[i][j] << i * n + j;
            }
        }
        //System.out.println(init);

        Queue<Integer> q = new ArrayDeque<>();
        boolean[] seen = new boolean[1 << m * n];

        q.offer(init);
        seen[init] = true;

        int d = 0;
        while(!q.isEmpty()) {
            int len = q.size();

            for(int i = 0; i < len; i++) {
                int cur = q.poll();

                if(cur == 0) {
                    ans = d;
                    break;
                }
                for(int r = 0; r < m; r++) {
                    for(int c = 0; c < n; c++) {
                        int nst = getNext(cur, r, c);
                        if(!seen[nst]) {
                            q.offer(nst);
                            seen[nst] = true;
                        }
                    }
                }
            }
            d++;
        }

        return ans;
    }

    private int getNext(int sta, int r, int c) {
        int nst = sta;
        nst ^= 1 << r * n + c;

        for(int[] dir : DIRECTIONS) {
            int nr = r + dir[0];
            int nc = c + dir[1];
            if(nr >= 0 && nc >= 0 && nr < m && nc < n) {
                nst ^= 1 << nr * n + nc;
            }
        }

        return nst;
    }
}
