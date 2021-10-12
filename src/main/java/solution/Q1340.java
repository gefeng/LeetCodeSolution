package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.Comparator;

@Problem(
        title = "Jump Game V",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/jump-game-v/"
)
public class Q1340 {
    /**
     * state:
     *  dp[i] denotes max cells can visit starting from i
     * transition
     *  dp[i] = max(dp[j] + 1) for each reachable j
     *
     * Time:  O(N ^ 2)
     * Space: O(N)
     * */
    public int maxJumps(int[] arr, int d) {
        int ans = 1;
        int n = arr.length;
        int[] dp = new int[n];

        int[][] pairs = new int[n][2];
        for(int i = 0; i < n; i++) {
            pairs[i][0] = arr[i];
            pairs[i][1] = i;
        }

        Arrays.sort(pairs, Comparator.comparingInt(a -> a[0]));

        for(int[] p : pairs) {
            int v = p[0];
            int i = p[1];

            dp[i] = 1;
            for(int j = i + 1; j < n; j++) {
                if(arr[j] >= v || i + d < j) {
                    break;
                }

                dp[i] = Math.max(dp[i], dp[j] + 1);
            }

            for(int j = i - 1; j >= 0; j--) {
                if(arr[j] >= v || i - d > j) {
                    break;
                }

                dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }

        for(int i = 0; i < n; i++) {
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}
