package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Cut off Trees for Golf Event",
        difficulty = QDifficulty.HARD,
        tag = QTag.BREATH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/cut-off-trees-for-golf-event/"
)
public class Q675 {
    /**
     * Time:  O(T * logT + T * M * N)
     * Space: O(M * N)
     * */
    int[][] directions = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    int[][] g;
    int m;
    int n;
    public int cutOffTree(List<List<Integer>> forest) {
        int ans = 0;
        m = forest.size();
        n = forest.get(0).size();
        g = new int[m][n];
        List<int[]> trees = new ArrayList<>();

        for(int r = 0; r < m; r++) {
            for(int c = 0; c < n; c++) {
                g[r][c] = forest.get(r).get(c);
                if(g[r][c] > 1) trees.add(new int[] {r, c, g[r][c]});
            }
        }

        Collections.sort(trees, Comparator.comparingInt(a -> a[2]));

        int sr = 0;
        int sc = 0;
        for(int[] tree : trees) {
            int steps = cut(sr, sc, tree[0], tree[1]);

            if(steps == -1) return -1;

            ans += steps;
            sr = tree[0];
            sc = tree[1];
        }

        return ans;
    }

    private int cut(int sr, int sc, int tr, int tc) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[m][n];
        int steps = 0;

        q.offer(new int[] {sr, sc});
        visited[sr][sc] = true;

        while(!q.isEmpty()) {
            int sz = q.size();
            for(int i = 0; i < sz; i++) {
                int[] cur = q.poll();
                int r = cur[0];
                int c = cur[1];

                if(r == tr && c == tc) {
                    g[r][c] = 1;
                    return steps;
                }

                for(int[] dir : directions) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];
                    if(nr >= 0 && nc >= 0 && nr < m && nc < n && g[nr][nc] != 0 && !visited[nr][nc]) {
                        q.offer(new int[] {nr, nc});
                        visited[nr][nc] = true;
                    }
                }
            }

            steps++;
        }

        return -1;
    }
}
