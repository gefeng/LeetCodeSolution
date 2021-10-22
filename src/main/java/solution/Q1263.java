package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Queue;

@Problem(
        title = "Minimum Moves to Move a Box to Their Target Location",
        difficulty = QDifficulty.HARD,
        tag = QTag.BREATH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/minimum-moves-to-move-a-box-to-their-target-location/"
)
public class Q1263 {
    /**
     * I use DSU to check connectivity (player can reach some point to push box). Or we can do DFS.
     *
     * Time:  O(M * N * M * N)
     * Space: O(M * N)
     * */
    private class DSU {
        int[] p;
        int[] w;
        DSU(int n) {
            p = new int[n];
            w = new int[n];
            for(int i = 0; i < n; i++) {
                p[i] = i;
            }
        }
        int find(int i) {
            if(p[i] != i) {
                p[i] = find(p[i]);
            }
            return p[i];
        }
        void union(int i, int j) {
            int x = find(i);
            int y = find(j);
            if(x == y) return;
            if(w[x] == w[y]) {
                p[y] = x;
                w[x]++;
            } else if(w[x] > w[y]) {
                p[y] = x;
            } else {
                p[x] = y;
            }
        }
    }

    private static final int[][] DIRECTIONS = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int minPushBox(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[] p= new int[2];
        int[] g = new int[2];
        int[] b = new int[2];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 'S') {
                    p[0] = i;
                    p[1] = j;
                    grid[i][j] = '.';
                } else if(grid[i][j] == 'B') {
                    b[0] = i;
                    b[1] = j;
                    grid[i][j] = '.';
                } else if(grid[i][j] == 'T') {
                    g[0] = i;
                    g[1] = j;
                    grid[i][j] = '.';
                }
            }
        }

        Queue<int[]> q = new ArrayDeque<>();
        boolean[][][] seen = new boolean[m][n][4];
        q.offer(new int[] {b[0], b[1], p[0], p[1]});

        int ans = -1;
        int pushs = 0;
        while(!q.isEmpty()) {
            int len = q.size();
            for(int k = 0; k < len; k++) {
                int[] cur = q.poll();
                int br = cur[0], bc = cur[1];
                int pr = cur[2], pc = cur[3];

                if(br == g[0] && bc == g[1]) {
                    return pushs;
                }

                DSU dsu = new DSU(m * n);
                for(int i = 0; i < m; i++) {
                    for(int j = 0; j < n; j++) {
                        if(grid[i][j] == '#' || (i == br && j == bc)) {
                            continue;
                        }

                        for(int[] dir : DIRECTIONS) {
                            int ni = dir[0] + i;
                            int nj = dir[1] + j;
                            if(ni >= 0 && nj >= 0 && ni < m && nj < n) {
                                if(grid[ni][nj] == '#' || (ni == br && nj == bc)) {
                                    continue;
                                }
                                dsu.union(i * n + j, ni * n + nj);
                            }
                        }
                    }
                }

                for(int i = 0; i < 4; i++) {
                    int[] dir = DIRECTIONS[i];
                    int nbr = br + dir[0];
                    int nbc = bc + dir[1];
                    int npr = br + dir[0] * -1;
                    int npc = bc + dir[1] * -1;
                    // check next position for the box
                    if(nbr >= 0 && nbc >= 0 && nbr < m && nbc < n && grid[nbr][nbc] != '#') {
                        // is it possible to push
                        if(npr >= 0 && npc >= 0 && npr < m && npc < n && grid[npr][npc] != '#') {
                            if(dsu.find(pr * n + pc) == dsu.find(npr * n + npc)) {
                                //System.out.println(nbr + " " + nbc + " " + br + " " + bc + " " + (pushs + 1));
                                if(!seen[nbr][nbc][i]) {
                                    q.offer(new int[] {nbr, nbc, br, bc});
                                    seen[nbr][nbc][i] = true;
                                }
                            }
                        }
                    }
                }
            }
            pushs++;
        }

        return -1;
    }
}
