package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Knight Probability in Chessboard",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/knight-probability-in-chessboard/"
)
public class Q688 {
    private static final int[][] DIRECTIONS = new int[][] {
            {-1, 2}, {1, 2}, {2, -1}, {2, 1}, {-1, -2}, {1, -2}, {-2, -1}, {-2, 1}
    };
    public double knightProbability(int N, int K, int r, int c) {
        return dfsSolution(N, K, r, c);
    }

    private double dfsSolution(int N, int K, int r, int c) {
        return recursive(N, K, r, c, new Double[N][N][K + 1]);
    }

    private double recursive(int N, int K, int r, int c, Double[][][] memo) {
        if(K == 0)
            return 1;

        if(memo[r][c][K] != null)
            return memo[r][c][K];

        double p = 0;
        for(int[] dir : DIRECTIONS) {
            int nr = r + dir[0];
            int nc = c + dir[1];

            if(nr >= 0 && nc >= 0 && nr < N && nc < N) {
                p += (0.125 * recursive(N, K - 1, nr, nc, memo));
            }
        }

        return memo[r][c][K] = p;
    }
}
