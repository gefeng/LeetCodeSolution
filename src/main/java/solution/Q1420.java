package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Build Array Where You Can Find The Maximum Exactly K Comparisons",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/build-array-where-you-can-find-the-maximum-exactly-k-comparisons/"
)
public class Q1420 {
    /**
     * Time:  O(N * K * M)
     * Space: O(N * K * M)
     * */
    private static final int MOD = (int)1e9 + 7;
    public int numOfArrays(int n, int m, int k) {
        return dfs(n, m, 0, k, 0, new Integer[n][k + 1][101]);
    }

    private int dfs(int n, int m, int i, int k, int prevMax, Integer[][][] memo) {
        if(i == n) {
            return k == 0 ? 1 : 0;
        }

        if(memo[i][k][prevMax] != null) {
            return memo[i][k][prevMax];
        }

        int cnt = 0;
        for(int d = 1; d <= prevMax; d++) {
            cnt = (cnt + dfs(n, m, i + 1, k, prevMax, memo)) % MOD;
        }

        if(k > 0) {
            for(int d = prevMax + 1; d <= m; d++) {
                cnt = (cnt + dfs(n, m, i + 1, k - 1, d, memo)) % MOD;
            }
        }

        return memo[i][k][prevMax] = cnt;
    }
}
