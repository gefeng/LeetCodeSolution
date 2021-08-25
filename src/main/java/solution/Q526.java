package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Beautiful Arrangement",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/beautiful-arrangement/"
)
public class Q526 {
    /**
     * Time:  O(N ^ 2 * 2 ^ N)
     * Space: O(N * 2 ^ N)
     * */
    public int countArrangement(int n) {
        return dfs(n, 0, 0, new Integer[n][1 << n]);
    }

    private int dfs(int n, int i, int mask, Integer[][] memo) {
        if(i == n) {
            return 1;
        }

        if(memo[i][mask] != null) {
            return memo[i][mask];
        }

        int cnt = 0;
        for(int j = 0; j < n; j++) {
            if(((1 << j) & mask) != 0) {
                continue;
            }

            if((j + 1) % (i + 1) == 0 || (i + 1) % (j + 1) == 0) {
                cnt += dfs(n, i + 1, mask | (1 << j), memo);
            }
        }

        return memo[i][mask] = cnt;
    }
}
