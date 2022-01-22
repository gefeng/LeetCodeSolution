package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Problem(
        title = "Scramble String",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/scramble-string/"
)
public class Q87 {
    /**
     * Time:  O(N ^ 2)
     * Space: O(N ^ 2)
     * */
    public boolean isScramble(String s1, String s2) {
        return dfs(s1, s2, new HashMap<>());
    }

    private boolean dfs(String s1, String s2, Map<String, Boolean> memo) {
        int n = s1.length();
        if(s1.equals(s2)) return true;
        if(n == 1) return false;

        if(memo.containsKey(s1 + s2)) {
            return memo.get(s1 + s2);
        }

        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];
        for(int i = 0; i < n; i++) {
            cnt1[s1.charAt(i) - 'a']++;
            cnt2[s2.charAt(i) - 'a']++;
        }

        if(!Arrays.equals(cnt1, cnt2)) return false;

        for(int i = 1; i < n; i++) {
            String prefix1 = s1.substring(0, i);
            String prefix2 = s2.substring(0, i);
            String suffix1 = s1.substring(i, n);
            String suffix2 = s2.substring(i, n);

            if(dfs(prefix1, prefix2, memo) && dfs(suffix1, suffix2, memo)) {
                return true;
            }
            if(dfs(suffix1, s2.substring(0, n - i), memo) && dfs(prefix1, s2.substring(n - i, n), memo)) {
                return true;
            }
        }

        memo.put(s1 + s2, false);
        return false;
    }
}
