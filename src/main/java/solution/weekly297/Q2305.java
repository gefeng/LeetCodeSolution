package solution.weekly297;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Fair Distribution of Cookies",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/contest/weekly-contest-297/problems/fair-distribution-of-cookies/"
)
public class Q2305 {
    /**
     * Time:  O(2 ^ N * 2 ^ N * K * N)
     * Space: O(2 ^ N * K)
     * */
    public int distributeCookies(int[] cookies, int k) {
        int n = cookies.length;
        int[][] dp = new int[k + 1][1 << n];  // minimum unfairness of first i children

        for(int i = 0; i < (1 << n); i++) {
            int sum = 0;
            for(int j = 0; j < n; j++) {
                if((i & (1 << j)) != 0) {
                    sum += cookies[j];
                }
            }
            dp[1][i] = sum;
        }

        for(int i = 2; i <= k; i++) {
            for(int j = 0; j < (1 << n); j++) {
                int min = Integer.MAX_VALUE;
                for(int m = 0; m < (1 << n); m++) {
                    int sum = getSum(cookies, j, m);
                    if(sum != -1) {
                        min = Math.min(min, Math.max(dp[i - 1][m], sum));
                    }
                }

                dp[i][j] = min;
            }
        }

        return dp[k][(1 << n) - 1];
    }

    private int getSum(int[] A, int cur, int pre) {
        int n = A.length;
        int sum = 0;

        for(int i = 0; i < n; i++) {
            int b1 = cur & (1 << i);
            int b2 = pre & (1 << i);

            if(b2 != 0 && b1 == 0) {
                return -1;
            }

            if(b2 == 0 && b1 != 0) {
                sum += A[i];
            }
        }

        return sum;
    }
}
