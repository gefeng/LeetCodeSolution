package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "K Highest Ranked Items Within a Price Range",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BREATH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/k-highest-ranked-items-within-a-price-range/"
)
public class Q2146 {
    /**
     * Time:  O(M * N * log(M * N))
     * Space: O(M * N)
     * */
    private static final int[][] directions = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private int[][] g;
    private int m;
    private int n;
    private int lower;
    private int upper;
    public List<List<Integer>> highestRankedKItems(int[][] grid, int[] pricing, int[] start, int k) {
        m = grid.length;
        n = grid[0].length;
        g = grid;
        lower = pricing[0];
        upper = pricing[1];

        List<int[]> items = new ArrayList<>();

        bfs(start[0], start[1], items);

        Queue<int[]> pq = new PriorityQueue<>((a, b) -> {
            int d1 = a[3];
            int d2 = b[3];
            int p1 = a[2];
            int p2 = b[2];
            if(d1 == d2) {
                if(p1 == p2) {
                    if(a[0] == b[0]) {
                        return Integer.compare(a[1], b[1]);
                    } else {
                        return Integer.compare(a[0], b[0]);
                    }
                } else {
                    return Integer.compare(p1, p2);
                }
            } else {
                return Integer.compare(d1, d2);
            }
        });

        for(int[] item : items) {
            pq.offer(item);
        }

        List<List<Integer>> ans = new ArrayList<>();
        while(!pq.isEmpty() && k > 0) {
            int[] item = pq.poll();
            ans.add(Arrays.asList(item[0], item[1]));
            k--;
        }
        return ans;
    }

    private void bfs(int sr, int sc, List<int[]> items) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[m][n];

        q.offer(new int[] {sr, sc});
        visited[sr][sc] = true;

        int d = 0;
        while(!q.isEmpty()) {
            int sz = q.size();
            for(int i = 0; i < sz; i++) {
                int[] cur = q.poll();
                int r = cur[0];
                int c = cur[1];
                if(g[r][c] > 1 && g[r][c] >= lower && g[r][c] <= upper) {
                    items.add(new int[] {r, c, g[r][c], d});
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
            d++;
        }
    }
}
