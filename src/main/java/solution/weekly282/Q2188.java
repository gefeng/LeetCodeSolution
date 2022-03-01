package solution.weekly282;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Minimum Time to Finish the Race",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/contest/weekly-contest-282/problems/minimum-time-to-finish-the-race/"
)
public class Q2188 {
    /**
     * Time:  O(N ^ 2)
     * Space: O(N)
     * */
    public int minimumFinishTime(int[][] tires, int changeTime, int numLaps) {
        int[] dp = new int[numLaps + 1];

        Arrays.fill(dp, Integer.MAX_VALUE);

        for(int[] t : tires) {
            int f = t[0], r = t[1];
            int tot = 0;
            int d = 1;
            int x = 1;
            while(true) {
                tot += f * d;
                dp[x] = Math.min(dp[x], tot);

                d = d * r;
                if(f * d > changeTime + f || x + 1 > numLaps) {
                    break;
                }
                x++;
            }
        }

        for(int i = 1; i <= numLaps; i++) {
            for(int j = 1; j < i; j++) {
                dp[i] = Math.min(dp[i], dp[j] + dp[i - j] + changeTime);
            }
        }

        return dp[numLaps];
    }
}
