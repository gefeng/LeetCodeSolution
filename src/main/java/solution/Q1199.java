package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Minimum Time to Build Blocks",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/minimum-time-to-build-blocks/"
)
public class Q1199 {
    /**
     * Time:  O(N ^ 2)
     * Space: O(N ^ 2)
     * */
    public int minBuildTime(int[] blocks, int split) {
        int n = blocks.length;

        Arrays.sort(blocks);

        return dfs(blocks, split, n - 1, 1, new Integer[n][n + 1]);
    }

    private int dfs(int[] blocks, int split, int cur, int k, Integer[][] memo) {
        int n = blocks.length;
        if(cur == -1) {
            return 0;
        }
        if(k >= cur + 1) {
            return blocks[cur];
        }

        if(memo[cur][k] != null) {
            return memo[cur][k];
        }

        int best = Integer.MAX_VALUE;
        if(k != 1) {
            best = Math.min(best, Math.max(blocks[cur], dfs(blocks, split, cur - 1, k - 1, memo)));
        }
        int build = Math.max(dfs(blocks, split, cur - 1, 2 * k - 1, memo), blocks[cur]) + split;
        int wait = dfs(blocks, split, cur, 2 * k, memo) + split;

        best = Math.min(best, Math.min(build, wait));

        return memo[cur][k] = best;
    }
}
