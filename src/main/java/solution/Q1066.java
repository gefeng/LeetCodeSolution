package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Campus Bikes II",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/campus-bikes-ii/"
)
public class Q1066 {
    /**
     * state:
     *  dp[i][j] denotes min sum of distance between first i workers and mask j bikes picked.
     * transition:
     *  dp[i][j] = min(dp[i - 1][j ^ (1 << k)] + dist(w[i], b[k])) j ^ (1 << k) is the submask without bike k (which means ith worker pick bike k)
     *
     * Time:  O(M * N * 2 ^ M)
     * Space: O(N * 2 ^ M)
     * */
    public int assignBikes(int[][] workers, int[][] bikes) {
        int n = workers.length;
        int m = bikes.length;

        int[][] dp = new int[n + 1][1 << m];
        for(int i = 1; i <= n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        for(int i = 1; i <= n; i++) {
            for(int j = 0; j < (1 << m); j++) {
                for(int k = 0; k < m; k++) {
                    if((j & (1 << k)) != 0) {
                        if(dp[i - 1][j ^ (1 << k)] != Integer.MAX_VALUE) {
                            dp[i][j] = Math.min(dp[i][j], dp[i - 1][j ^ (1 << k)] + dist(workers[i - 1], bikes[k]));
                        }
                    }
                }
            }
        }

        return dp[n][(1 << m) - 1];
    }

    private int dist(int[] x, int[] y) {
        return Math.abs(x[0] - y[0]) + Math.abs(x[1] - y[1]);
    }
}
