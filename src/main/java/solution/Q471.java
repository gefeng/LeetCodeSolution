package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.Map;

@Problem(
        title = "Encode String with Shortest Length",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/encode-string-with-shortest-length/"
)
public class Q471 {
    /**
     * Time:  O(N ^ 4)
     * Space: O(N ^ 3)
     * */
    public String encode(String s) {
        int n = s.length();
        return dfs(s, new HashMap<>());
    }

    private String dfs(String s, Map<String, String> memo) {
        int n = s.length();
        if(n < 5) return s;
        if(memo.containsKey(s)) return memo.get(s);

        String best = s;

        for(int i = 0; i < n - 1; i++) {
            String cand = dfs(s.substring(0, i + 1), memo) + dfs(s.substring(i + 1, n), memo);
            best = cand.length() < best.length() ? cand : best;
        }

        for(int k = 1; k <= n / 2; k++) {
            String p = s.substring(0, k);
            if(s.replaceAll(p, "").length() == 0) {
                int rep = n / k;
                String cand = rep + "[" + dfs(p, memo) + "]";
                best = cand.length() < best.length() ? cand : best;
            }
        }

        memo.put(s, best);

        return best;
    }
}
