package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.Map;

@Problem(
        title = "Word Pattern II",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BACKTRACKING,
        url = "https://leetcode.com/problems/word-pattern-ii/"
)
public class Q291 {
    /*
        O(m^n)
    */
    public boolean wordPatternMatch(String pattern, String s) {
        return dfs(pattern, s, 0, 0, new HashMap<>(), new HashMap<>());
    }

    private boolean dfs(String p, String s, int idx1, int idx2, Map<Character, String> m1, Map<String, Character> m2) {
        int m = p.length();
        int n = s.length();

        if(idx1 == m && idx2 == n) {
            return true;
        }
        if(idx1 == m || idx2 == n) {
            return false;
        }

        char pc = p.charAt(idx1);

        if(m1.containsKey(pc)) {
            String ms = m1.get(pc);
            boolean isOk = true;
            for(int i = 0; i < ms.length(); i++) {
                if(idx2 + i > n - 1 || s.charAt(idx2 + i) != ms.charAt(i)) {
                    isOk = false;
                    break;
                }
            }

            if(!isOk) {
                return false;
            } else {
                return dfs(p, s, idx1 + 1, idx2 + ms.length(), m1, m2);
            }
        } else {
            StringBuilder sb = new StringBuilder();
            for(int i = idx2; i < n - m + 1 + idx1; i++) {
                sb.append(s.charAt(i));
                if(m2.containsKey(sb.toString())) {
                    continue;
                }

                String ms = sb.toString();
                m1.put(pc, ms);
                m2.put(ms, pc);
                if(dfs(p, s, idx1 + 1, i + 1, m1, m2)) {
                    return true;
                }
                m1.remove(pc);
                m2.remove(ms);
            }
        }

        return false;
    }
}
