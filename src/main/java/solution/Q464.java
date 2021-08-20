package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.Map;

@Problem(
        title = "Can I Win",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GAME_THEORY,
        url = "https://leetcode.com/problems/can-i-win/"
)
public class Q464 {
    /**
     * I was struggling about the time/space complexity for 2d bitmask
     * dp[mask][left]. It turns out for this problem, total left already
     * being represented by dp mask since sequence of two players
     * picking number doesn't affect total sum so far. We don't care who
     * pick 1,2,3, the total left remains the same. So the dp state is
     * compressed to 1d.
     *
     * Time:  O(2 ^ N)
     * Space: O(2 ^ N)
     * */
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if(maxChoosableInteger >= desiredTotal) {
            return true;
        }

        if((maxChoosableInteger + 1) * maxChoosableInteger / 2 < desiredTotal) {
            return false;
        }

        return dfs(maxChoosableInteger, 0, desiredTotal, new HashMap<>());
    }

    private boolean dfs(int n, int mask, int total, Map<Integer, Boolean> memo) {
        if(total <= 0) {
            return false;
        }

        if(memo.containsKey(mask)) {
            return memo.get(mask);
        }

        boolean res = false;

        for(int i = n - 1; i >= 0; i--) {
            if(((1 << i) & mask) != 0) {
                continue;
            }

            if(!dfs(n, mask | (1 << i), total - i - 1, memo)) {
                res = true;
                break;
            }
        }

        memo.put(mask, res);

        return res;
    }
}
