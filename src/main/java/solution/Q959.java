package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Regions Cut By Slashes",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.UNION_FIND,
        url = "https://leetcode.com/problems/regions-cut-by-slashes/"
)
public class Q959 {
    /**
     * This one is fun.
     *
     * Time:  O(N ^ 2)
     * Space: O(N ^ 2)
     * */
    private static final int[][] DIRECTIONS = new int[][] {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
    public int regionsBySlashes(String[] grid) {
        int n = grid.length;
        int ans = 0;
        char[][] g = new char[n][n];
        DSU dsu = new DSU(4 * n * n);

        for(int i = 0; i < n; i++) {
            g[i] = grid[i].toCharArray();
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                int idx = i * n + j;
                if(g[i][j] == ' ') {
                    dsu.union(idx * 4, idx * 4 + 1);
                    dsu.union(idx * 4 + 1, idx * 4 + 2);
                    dsu.union(idx * 4 + 2, idx * 4 + 3);
                } else if(g[i][j] == '/') {
                    dsu.union(idx * 4, idx * 4 + 1);
                    dsu.union(idx * 4 + 2, idx * 4 + 3);
                } else {
                    dsu.union(idx * 4, idx * 4 + 3);
                    dsu.union(idx * 4 + 1, idx * 4 + 2);
                }

                for(int k = 0; k < 4; k++) {
                    int[] dir = DIRECTIONS[k];
                    int ni = i + dir[0];
                    int nj = j + dir[1];
                    if(ni >= 0 && nj >= 0 && ni < n && nj < n) {
                        dsu.union(idx * 4 + k, (ni * n + nj) * 4 + (k + 2) % 4);
                    }
                }
            }
        }

        return dsu.count();
    }

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
            if(p[i] != i) p[i] = find(p[i]);
            return p[i];
        }
        void union(int i, int j) {
            int x = find(i);
            int y = find(j);
            if(x == y) return;
            if(w[x] == w[y]) {
                p[y] = x;
                w[x] += 1;
            } else if(w[x] > w[y]) {
                p[y] = x;
            } else {
                p[x] = y;
            }
        }
        int count() {
            int cnt = 0;
            for(int i = 0; i < p.length; i++) {
                if(find(i) == i) {
                    cnt++;
                }
            }
            return cnt;
        }
    }
}
