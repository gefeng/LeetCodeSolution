package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Queue;

@Problem(
        title = "Shortest Distance from All Buildings",
        difficulty = QDifficulty.HARD,
        tag = QTag.BREATH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/shortest-distance-from-all-buildings/"
)
public class Q317 {
    /*
    * 这题的time complexity 能想到的就是O(m^2*n^2)
    * 有两种思路，因为要知道一个cell是否能连通所有buildings，所以不管哪种思路都要统计。
    * 一种bfs搜寻所有empty cells，计算到所有buildings的最短距离，同时判断能否reach所有buildings
    * 另一种是bfs搜所有buildings，因为bfs总是找到最短距离，所以每次访问cell累加当前距离即可，同时统计该cell被building reach的次数
    * */
    private static final int[][] DIRECTIONS = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private int m;
    private int n;
    public int shortestDistance(int[][] grid) {
        m = grid.length;
        n = grid[0].length;

        //return searchEmptyCellsSolution(int[][] grid);
        return searchBuildingsSolution(grid);
    }

    private int searchBuildingsSolution(int[][] grid) {
        int[][] dBuildings = new int[m][n];
        int[][] cBuildings = new int[m][n];
        int countBuildings = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 1) {
                    countBuildings++;
                    bfsDistToEmptyCells(grid, i, j, dBuildings, cBuildings);
                }
            }
        }

        int minDist = Integer.MAX_VALUE;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 0 && cBuildings[i][j] == countBuildings) {
                    minDist = Math.min(minDist, dBuildings[i][j]);
                }
            }
        }

        return minDist == Integer.MAX_VALUE ? -1 : minDist;
    }

    private void bfsDistToEmptyCells(int[][] grid, int sr, int sc, int[][] dBuildings, int[][] cBuildings) {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[m][n];
        queue.offer(new int[] {sr, sc});
        int dist = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                int[] pos = queue.poll();
                int r = pos[0];
                int c = pos[1];
                if(grid[r][c] != 1) {
                    dBuildings[r][c] += dist;
                    cBuildings[r][c]++;
                }

                for(int[] dir : DIRECTIONS) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];
                    if(nr >= 0 && nc >= 0 && nr < m && nc < n &&
                            grid[nr][nc] != 1 && grid[nr][nc] != 2 && !visited[nr][nc]) {
                        queue.offer(new int[] {nr, nc});
                        visited[nr][nc] = true;
                    }
                }
            }
            dist++;
        }
    }

    private int searchEmptyCellsSolution(int[][] grid) {
        int numBuildings = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 1) {
                    numBuildings++;
                }
            }
        }

        int minDist = Integer.MAX_VALUE;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 0) {
                    int dist = bfsDistToBuildings(grid, i, j, numBuildings);
                    if(dist != -1) {
                        minDist = Math.min(minDist, dist);
                    }
                }
            }
        }

        return minDist == Integer.MAX_VALUE ? -1 : minDist;
    }

    private int bfsDistToBuildings(int[][] grid, int sr, int sc, int numBuildings) {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[m][n];
        queue.offer(new int[] {sr, sc});
        visited[sr][sc] = true;
        int totalDist = 0;
        int dist = 0;
        int countBuildings = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                int[] curr = queue.poll();

                for(int[] dir : DIRECTIONS) {
                    int r = dir[0] + curr[0];
                    int c = dir[1] + curr[1];
                    if(r >= 0 && c >= 0 && r < m && c < n) {
                        if(grid[r][c] == 2 || visited[r][c])
                            continue;
                        if(grid[r][c] == 1 && !visited[r][c]) {
                            totalDist += (dist + 1);
                            visited[r][c] = true;
                            countBuildings++;
                        }
                        if(grid[r][c] == 0) {
                            queue.offer(new int[] {r, c});
                            visited[r][c] = true;
                        }
                    }
                }
            }
            dist++;
        }

        if(countBuildings < numBuildings) {
            return -1;
        }

        return totalDist;
    }
}
