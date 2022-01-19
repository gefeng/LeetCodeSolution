package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Coin Path",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/coin-path/"
)
public class Q656 {
    /**
     * Time:  O(N ^ 2)
     * Space: O(N)
     * */
    public List<Integer> cheapestJump(int[] coins, int maxJump) {
        int n = coins.length;
        List<Integer> path = new ArrayList<>();
        int[] next = new int[n];
        int ret = dfs(coins, maxJump, 0, new Integer[n], next);

        if(ret == Integer.MAX_VALUE) {
            return new ArrayList<>();
        }

        path.add(1);

        for(int i = 0; i != n - 1; ) {
            i = next[i];
            path.add(i + 1);
        }
        return path;
    }

    private int dfs(int[] coins, int maxJump, int pos, Integer[] memo, int[] next) {
        int n = coins.length;

        if(pos == n - 1) return 0;
        if(memo[pos] != null) return memo[pos];

        int min = Integer.MAX_VALUE;
        int bestPos = -1;
        for(int i = 1; i <= maxJump; i++) {
            int np = pos + i;
            if(np < n && coins[np] != -1) {
                int ret = dfs(coins, maxJump, np, memo, next);
                if(ret != Integer.MAX_VALUE) {
                    int cost = ret + coins[np];

                    if(cost < min) {
                        min = cost;
                        bestPos = np;
                    }
                }
            }
        }

        next[pos] = bestPos;
        return memo[pos] = min;
    }
}
