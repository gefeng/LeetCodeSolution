package solution.biweekly79;

import java.util.*;
import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Maximum Total Importance of Roads",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/contest/biweekly-contest-79/problems/maximum-total-importance-of-roads/"
)
public class Q2285 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public long maximumImportance(int n, int[][] roads) {
        long ans = 0;
        long[] deg = new long[n];

        for(int[] r : roads) {
            deg[r[0]]++;
            deg[r[1]]++;
        }

        Arrays.sort(deg);

        for(int i = 1; i <= n; i++) {
            ans += i * deg[i - 1];
        }

        return ans;
    }
}
