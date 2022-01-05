package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Pyramid Transition Matrix",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DEPTH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/pyramid-transition-matrix/"
)
public class Q756 {
    Map<String, Map<String, Boolean>> dp = new HashMap<>();
    public boolean pyramidTransition(String bottom, List<String> allowed) {
        Set<String> set = new HashSet<>(allowed);

        return dfs(bottom, "", set);
    }

    private boolean dfs(String pre, String cur, Set<String> allowed) {
        int m = pre.length();
        int n = cur.length();

        if(m == 1) return true;
        if(n + 1 == m) return dfs(cur, "", allowed);

        if(dp.containsKey(pre) && dp.get(pre).containsKey(cur)) {
            return dp.get(pre).get(cur);
        }

        for(char c = 'A'; c <= 'F'; c++) {
            String p = "" + pre.charAt(n) + pre.charAt(n + 1) + c;
            if(allowed.contains(p)) {
                if(dfs(pre, cur + c, allowed)) {
                    return true;
                }
            }
        }

        dp.computeIfAbsent(pre, k -> new HashMap<>()).put(cur, false);

        return false;
    }
}
