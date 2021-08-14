package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Super Ugly Number",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/super-ugly-number/"
)
public class Q313 {
    /**
     * Time:  O(N * K)
     * Space: O(N)
     * */
    public int nthSuperUglyNumber(int n, int[] primes) {
        int m = primes.length;
        int[] ugly = new int[n];
        int[] dp = new int[m];

        ugly[0] = 1;
        for(int i = 1; i < n; i++) {
            int next = Integer.MAX_VALUE;
            int idx = 0;
            for(int j = 0; j < m; j++) {
                next = Math.min(next, ugly[dp[j]] * primes[j]);
            }

            // remove duplicates i.e. [2 * 7] and [7 * 2]
            for(int j = 0; j < m; j++) {
                if(ugly[dp[j]] * primes[j] == next) {
                    dp[j]++;
                }
            }

            ugly[i] = next;
        }

        return ugly[n - 1];
    }
}
