package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Count Numbers with Unique Digits",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/count-numbers-with-unique-digits/"
)
public class Q357 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int countNumbersWithUniqueDigits(int n) {
        int res = 1;
        Integer[][] memo = new Integer[n + 1][1 << 10];
        for(int i = 1; i < 10; i++) {
            res += dfs(n, 1, (1 << i), memo);
        }
        return res;
    }

    private int dfs(int n, int i, int mask, Integer[][] memo) {
        if(i > n) {
            return 0;
        }

        if(memo[i][mask] != null) {
            return memo[i][mask];
        }

        int cnt = 1;
        for(int j = 0; j < 10; j++) {
            if(((1 << j) & mask) == 0) {
                cnt += dfs(n, i + 1, mask | (1 << j), memo);
            }
        }

        return memo[i][mask] = cnt;
    }
}
