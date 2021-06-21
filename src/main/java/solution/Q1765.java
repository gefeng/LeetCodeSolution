package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

@Problem(
        title = "Map of Highest Peak",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BREATH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/map-of-highest-peak/"
)
public class Q1765 {
    /*
    * 这题提供一个思路叫multi-source bfs，可以设置多个start points。
    * 这题就是可以从所有的water cell开始bfs。
    * */
    private static final int[][] DIRECTIONS = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public int[][] highestPeak(int[][] isWater) {
        int m = isWater.length;
        int n = isWater[0].length;
        int[][] hMap = new int[m][n];

        for(int i = 0; i < m; i++) {
            Arrays.fill(hMap[i], -1);
        }

        Queue<int[]> queue = new ArrayDeque<>();

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(isWater[i][j] == 1) {
                    queue.offer(new int[] {i, j});
                    hMap[i][j] = 0;
                }
            }
        }

        int h = 1;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                int[] pos = queue.poll();
                int r = pos[0];
                int c = pos[1];

                for(int[] dir : DIRECTIONS) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];
                    if(nr >= 0 && nc >= 0 && nr < m && nc < n) {
                        if(isWater[nr][nc] == 1 || hMap[nr][nc] != -1) {
                            continue;
                        }
                        queue.offer(new int[] {nr, nc});
                        hMap[nr][nc] = h;
                    }
                }
            }

            h++;
        }

        return hMap;
    }
}
