package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Queue;

@Problem(
        title = "Minimum Moves to Reach Target with Rotations",
        difficulty = QDifficulty.HARD,
        tag = QTag.BREATH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/minimum-moves-to-reach-target-with-rotations/"
)
public class Q1210 {
    /**
     * Time:  O(N ^ 2)
     * Space: O(N ^ 2)
     * */
    private static final int[][] DIRECTIONS = new int[][] {{0, 1}, {1, 0}};
    private int n;
    private int[][] g;
    public int minimumMoves(int[][] grid) {
        this.n = grid.length;
        this.g = grid;

        Queue<int[]> q = new ArrayDeque<>();
        boolean[][][] visited = new boolean[n][n][2];

        q.offer(new int[] {0, 0, 0});
        visited[0][0][0] = true;

        int steps = 0;
        while(!q.isEmpty()) {
            int len = q.size();
            for(int i = 0; i < len; i++) {
                int[] cur = q.poll();
                int tr = cur[0], tc = cur[1], d = cur[2];

                if(d == 0 && tr == n - 1 && tc == n - 2) {
                    return steps;
                }

                int hr = d == 0 ? tr : tr + 1;
                int hc = d == 0 ? tc + 1 : tc;

                for(int[] dir : DIRECTIONS) {
                    int ntr = tr + dir[0];
                    int ntc = tc + dir[1];
                    int nhr = hr + dir[0];
                    int nhc = hc + dir[1];
                    if(isValid(ntr, ntc) && isValid(nhr, nhc) && !visited[ntr][ntc][d]) {
                        q.offer(new int[] {ntr, ntc, d});
                        visited[ntr][ntc][d] = true;
                    }
                }

                if(d == 0) {
                    if(isValid(tr + 1, tc) && isValid(hr + 1, hc) && !visited[tr][tc][1]) {
                        q.offer(new int[] {tr, tc, 1});
                        visited[tr][tc][1] = true;
                    }
                } else {
                    if(isValid(tr, tc + 1) && isValid(hr, hc + 1) && !visited[tr][tc][0]) {
                        q.offer(new int[] {tr, tc, 0});
                        visited[tr][tc][0] = true;
                    }
                }
            }

            steps++;
        }

        return -1;
    }

    private boolean isValid(int r, int c) {
        return r >= 0 && c >= 0 && r < n && c < n && g[r][c] == 0;
    }
}
