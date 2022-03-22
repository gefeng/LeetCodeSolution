package solution.biweekly74;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Minimum White Tiles After Covering With Carpets",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/contest/biweekly-contest-74/problems/minimum-white-tiles-after-covering-with-carpets/"
)
public class Q2209 {
    /**
     * Time:  O(N ^ 2)
     * Space: O(N ^ 2)
     * */
    public int minimumWhiteTiles(String floor, int numCarpets, int carpetLen) {
        int n = floor.length();
        int[] psum = new int[n + 1];
        int[][] dp = new int[n + 1][numCarpets + 1];

        int sum = 0;
        for(int i = 1; i <= n; i++) {
            Arrays.fill(dp[i], n);
            sum += floor.charAt(i - 1) - '0';
            dp[i][0] = sum;
        }

        Arrays.fill(dp[0], 0);

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= numCarpets; j++) {
                if(floor.charAt(i - 1) == '1') {
                    dp[i][j] = Math.min(dp[i - 1][j] + 1, dp[Math.max(0, i - carpetLen)][j - 1]);
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[Math.max(0, i - carpetLen)][j - 1]);
                }
            }
        }

        return dp[n][numCarpets];
    }

    private int topDownSol(String floor, int numCarpets, int carpetLen) {
        int n = floor.length();
        int[] psum = new int[n + 1];
        for(int i = 1; i <= n; i++) {
            psum[i] = psum[i - 1] + (floor.charAt(i - 1) == '1' ? 1 : 0);
        }

        return dfs(floor, carpetLen, psum, 0, numCarpets, new Integer[n][numCarpets + 1]);
    }

    private int dfs(String floor, int carpetLen, int[] psum, int cur, int left, Integer[][] memo) {
        int n = floor.length();
        if(cur == n) return 0;
        if(left == 0) {
            return psum[n] - psum[cur];
        }

        if(memo[cur][left] != null) {
            return memo[cur][left];
        }

        int min = n;
        if(floor.charAt(cur) == '0') { // black
            int cover = dfs(floor, carpetLen, psum, Math.min(cur + carpetLen, n), left - 1, memo);
            int skip = dfs(floor, carpetLen, psum, cur + 1, left, memo);
            min = Math.min(cover, skip);
        } else {
            int cover = dfs(floor, carpetLen, psum, Math.min(cur + carpetLen, n), left - 1, memo);
            int skip = dfs(floor, carpetLen, psum, cur + 1, left, memo);
            min = Math.min(cover, skip + 1);
        }

        return memo[cur][left] = min;
    }
}
