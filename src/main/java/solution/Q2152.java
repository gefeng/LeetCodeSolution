package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Minimum Number of Lines to Cover Points",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/minimum-number-of-lines-to-cover-points/"
)
public class Q2152 {
    /**
     * Time:  O(2 ^ N * N)
     * Space: O(2 ^ N)
     * */
    public int minimumLines(int[][] points) {
        int n = points.length;
        int[] dp = new int[1 << n];

        Arrays.fill(dp, n);
        dp[0] = 0;
        for(int i = 1; i < (1 << n); i++) {
            int cnt = Integer.bitCount(i);
            if(cnt <= 2) {
                dp[i] = 1;
                continue;
            }

            int p1 = -1;
            int p2 = -1;
            boolean oneLine = true;
            for(int j = 0; j < n; j++) {
                if((i & (1 << j)) != 0) {
                    if(p1 == -1) {
                        p1 = j;
                    } else if(p2 == -1) {
                        p2 = j;
                    } else {
                        int dx1 = points[p2][0] - points[p1][0];
                        int dy1 = points[p2][1] - points[p1][1];
                        int dx2 = points[j][0] - points[p1][0];
                        int dy2 = points[j][1] - points[p1][1];

                        if(dx1 * dy2 != dx2 * dy1) {
                            oneLine = false;
                            break;
                        }
                    }
                }
            }

            if(oneLine) {
                dp[i] = 1;
            }
        }

        for(int i = 0; i < (1 << n); i++) {
            if(dp[i] == n) {
                for(int j = i & i - 1; j > 0; j = j - 1 & i) {
                    dp[i] = Math.min(dp[i], dp[j] + dp[i ^ j]);
                }
            }
        }

        return dp[(1 << n) - 1];
    }

    private int gcd(int a, int b) {
        if(a == 0) {
            return b;
        }

        return gcd(b % a, a);
    }
}
