package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Best Team With No Conflicts",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/best-team-with-no-conflicts/"
)
public class Q1626 {
    /**
     * Sort by age then score in increasing order,
     * then the problem becomes finding LIS
     *
     * state:
     *  dp[i] means best score by picking from [0, i] players including ith player
     * transition:
     *  dp[i] = Math.max(dp[i], dp[j] + scores[i]) where score[j] >= score[i]
     *
     *  Time:  O(N ^ 2)
     *  Space: O(N)
     * */
    public int bestTeamScore(int[] scores, int[] ages) {
        int n = scores.length;
        int res = 0;
        int[][] pairs = new int[n][2];
        int[] dp = new int[n];

        for(int i = 0; i < n; i++) {
            pairs[i][0] = ages[i];
            pairs[i][1] = scores[i];
        }

        Arrays.sort(pairs, (a, b) -> {
            if(a[0] == b[0]) {
                return Integer.compare(b[1], a[1]);
            }
            return Integer.compare(b[0], a[0]);
        });

        dp[0] = pairs[0][1];
        res = dp[0];
        for(int i = 1; i < n; i++) {
            dp[i] = pairs[i][1];
            for(int j = 0; j < i; j++) {
                if(pairs[j][0] == pairs[i][0] || pairs[j][1] >= pairs[i][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + pairs[i][1]);
                }
            }

            res = Math.max(res, dp[i]);
        }

        return res;
    }
}
