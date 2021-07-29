package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Number of Sets of K Non-Overlapping Line Segments",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/number-of-sets-of-k-non-overlapping-line-segments/"
)
public class Q1621 {
    /**
     * 这题很神奇，加一个state inMiddle可以把时间复杂度从cubic降到quadratic
     *
     * Time:  O(N * K)
     * Space: O(N * K)
     * */
    private static final int MOD = (int)1e9 + 7;
    public int numberOfSets(int n, int k) {
        return dfs(n, k, 0, 0, new Integer[n][k + 1][2]);
    }

    private int dfs(int n, int k, int i, int inMiddle, Integer[][][] memo) {
        if(k == 0) {
            return 1;
        }
        if(i > n - 1) {
            return 0;
        }

        if(memo[i][k][inMiddle] != null) {
            return memo[i][k][inMiddle];
        }

        long skip = dfs(n, k, i + 1, inMiddle, memo);

        long pick = 0;
        if(inMiddle == 0) {
            pick = dfs(n, k, i + 1, 1, memo); // place start point
        } else {
            pick = dfs(n, k - 1, i, 0, memo); // place end point
        }

        return memo[i][k][inMiddle] = (int)((skip + pick) % MOD);
    }
}
