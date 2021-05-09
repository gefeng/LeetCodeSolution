package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Spiral Matrix III",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/spiral-matrix-iii/"
)
public class Q885 {
    private static final int[][] DIRECTIONS = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public int[][] spiralMatrixIII(int R, int C, int r0, int c0) {
        int[][] visited = new int[R * C][2];
        visited[0][0] = r0;
        visited[0][1] = c0;

        int len = 1;
        int visIdx = 1;
        int dirIdx = 0;
        int r = r0;
        int c = c0;
        while(visIdx < R * C) {
            for(int round = 0; round < 2; round++) {
                for(int i = 0; i < len; i++) {
                    r = r + DIRECTIONS[dirIdx][0];
                    c = c + DIRECTIONS[dirIdx][1];
                    if(r >= 0 && c >= 0 && r < R && c < C) {
                        visited[visIdx][0] = r;
                        visited[visIdx][1] = c;
                        visIdx++;
                    }
                }
                dirIdx = (dirIdx + 1) % DIRECTIONS.length;
            }

            len++;
        }

        return visited;
    }
}
