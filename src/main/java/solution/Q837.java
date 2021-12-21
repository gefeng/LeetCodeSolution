package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "New 21 Game",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/new-21-game/"
)
public class Q837 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public double new21Game(int n, int k, int maxPts) {
        if(k == 0) return 1.0;

        double[] dp = new double[n + 1];
        dp[0] = 1;
        double sum = 1;
        for(int i = 1; i < k; i++) {
            dp[i] = sum / maxPts;

            sum += dp[i];
            if(i >= maxPts) sum -= dp[i - maxPts];
        }

        double tot = 0;
        for(int i = k; i <= n; i++) {
            tot += sum / maxPts;
            if(i - maxPts >= 0) sum -= dp[i - maxPts];
        }

        return tot;
    }
}
