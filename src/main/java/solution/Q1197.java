package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Queue;

@Problem(
        title = "Minimum Knight Moves",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BREATH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/minimum-knight-moves/"
)
public class Q1197 {
    /*
        |x| + |y| <= 300

        -302 - 302
    */
    /**
     * Time:  O(N ^ 2)
     * Space: O(N ^ 2)
     * */
    private static final int[][] DIRECTIONS = {{-2, -1},{-2, 1},{2, -1},{2, 1},
            {-1, -2},{1, -2},{-1, 2},{1, 2}};
    private int m = 605;
    private int n = 605;
    private int offset = 302;
    public int minKnightMoves(int x, int y) {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[m][n];
        queue.offer(new int[] {0, 0});
        visited[offset][offset] = true;

        int steps = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                int[] currPos = queue.poll();

                if(currPos[0] == x && currPos[1] == y) {
                    return steps;
                }

                for(int[] dir : DIRECTIONS) {
                    int nr = currPos[0] + dir[0];
                    int nc = currPos[1] + dir[1];
                    if(!visited[nr + offset][nc + offset]) {
                        queue.offer(new int[] {nr, nc});
                        visited[nr + offset][nc + offset] = true;
                    }
                }
            }
            steps++;
        }

        return -1;
    }
}
