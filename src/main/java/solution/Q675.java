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
    private static final int[][] DIR = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private int m = 0;
    private int n = 0;
    public int cutOffTree(List<List<Integer>> forest) {
        m = forest.size();
        n = forest.get(0).size();
        List<int[]> trees = new ArrayList<>();
        trees.add(new int[] {0, 0, 0});

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                int cell = forest.get(i).get(j);
                if(cell > 1)
                    trees.add(new int[] {cell, i, j});
            }
        }

        Collections.sort(trees, (a, b) -> {
            return a[0] - b[0];
        });

        int steps = 0;
        for(int i = 1; i < trees.size(); i++) {
            int dist = dist(forest, trees.get(i - 1)[1], trees.get(i - 1)[2], trees.get(i)[1], trees.get(i)[2]);
            if(dist == -1)
                return -1;
            steps += dist;
        }
        return steps;
    }

    // bfs find shortest path
    private int dist(List<List<Integer>> forest, int sr, int sc, int tr, int tc) {
        int dist = 0;
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {sr, sc});
        visited[sr][sc] = true;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                int[] pos = queue.poll();
                if(pos[0] == tr && pos[1] == tc) {
                    //forest.get(pos[0]).set(pos[1], 1);
                    return dist;
                }
                for(int[] dir : DIR) {
                    int r = pos[0] + dir[0];
                    int c = pos[1] + dir[1];
                    if(r < 0 || c < 0 || r > m - 1 || c > n - 1 || visited[r][c] || forest.get(r).get(c) == 0)
                        continue;

                    queue.offer(new int[] {r, c});
                    visited[r][c] = true;
                }
            }
            dist++;
        }
        return -1;
    }
}
