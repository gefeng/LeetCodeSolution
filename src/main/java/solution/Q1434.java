package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.List;

@Problem(
        title = "Number of Ways to Wear Different Hats to Each Other",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/number-of-ways-to-wear-different-hats-to-each-other/"
)
public class Q1434 {
    /**
     * Time:  O(M * N * 2 ^ N)
     * Space: O(M * 2 ^ N)
     * */
    private static final int MOD = (int)1e9 + 7;
    public int numberWays(List<List<Integer>> hats) {
        int n = hats.size();

        int[] h2p = new int[41];

        for(int i = 0; i < n; i++) {
            List<Integer> l = hats.get(i);
            for(int h : l) {
                h2p[h] |= (1 << i);
            }
        }

        return dfs(h2p, n, 0, 0, new Integer[41][1 << n]);
    }

    private int dfs(int[] h2p, int n, int i, int state, Integer[][] memo) {
        if(state == (1 << n) - 1) {
            return 1;
        }
        if(i == h2p.length) {
            return 0;
        }

        if(memo[i][state] != null) {
            return memo[i][state];
        }

        int cnt = dfs(h2p, n, i + 1, state, memo); // skip current hat.

        for(int j = 0; j < n; j++) {
            if(((state & (1 << j)) == 0) && (h2p[i] & (1 << j)) != 0) {
                cnt = (cnt + dfs(h2p, n, i + 1, state | (1 << j), memo)) % MOD;
            }
        }

        return memo[i][state] = cnt;
    }
}
