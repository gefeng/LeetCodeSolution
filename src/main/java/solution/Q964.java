package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.Map;

@Problem(
        title = "Least Operators to Express Number",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/least-operators-to-express-number/"
)
public class Q964 {
    /**
     * Time:  O(logT * logT)
     * Space: O(logT)
     * */
    long x;
    Map<Long, Long> memo;
    public int leastOpsExpressTarget(int x, int target) {
        this.x = x;
        this.memo = new HashMap<>();

        return (int)dfs(target);
    }

    private long dfs(long t) {
        if(t == 0 || t == x) {
            return 0;
        }
        if(t == 1) {
            return 1;
        }

        if(memo.containsKey(t)) {
            return memo.get(t);
        }

        long cnt = 0;
        long cur = 1;
        while(cur * x <= t) {
            cur *= x;
            cnt++;
        }

        // if x < target then append + x/x which requires 2 moves
        long guess = cur == t ? cnt - 1 : dfs(t - cur) + (cnt == 0 ? 2 : cnt);

        if(cur < t && cur * x - t < t) {
            guess = Math.min(guess, (cnt - 1) + dfs(cur * x - t) + 2);
        }

        memo.put(t, guess);
        return guess;
    }
}
