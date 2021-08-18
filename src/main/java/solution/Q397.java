package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.Map;

@Problem(
        title = "Integer Replacement",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/integer-replacement/"
)
public class Q397 {
    /**
     * Time:  O(logN)
     * Space: O(logN)
     * */
    public int integerReplacement(int n) {
        return dfs(n, new HashMap<>());
    }

    private int dfs(long n, Map<Long, Integer> memo) {
        if(n == 1) {
            return 0;
        }

        if(memo.containsKey(n)) {
            return memo.get(n);
        }

        int cnt = 0;

        if(n % 2 == 0) {
            cnt = dfs(n / 2, memo) + 1;
        } else {
            cnt = Math.min(dfs(n + 1, memo), dfs(n - 1, memo)) + 1;
        }

        memo.put(n, cnt);

        return cnt;
    }
}
