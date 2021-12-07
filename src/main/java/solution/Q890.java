package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Problem(
        title = "Find and Replace Pattern",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/find-and-replace-pattern/"
)
public class Q890 {
    /**
     * Time:  O(N * L)
     * Space: O(N * L)
     * */
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> ans = new ArrayList<>();

        for(int i = 0; i < words.length; i++) {
            if(isMatch(words[i], pattern)) {
                ans.add(words[i]);
            }
        }

        return ans;
    }

    private boolean isMatch(String s, String p) {
        int[] map = new int[26];
        Arrays.fill(map, -1);

        int n = s.length();
        for(int i = 0; i < n; i++) {
            int cs = s.charAt(i) - 'a';
            int cp = p.charAt(i) - 'a';

            if(map[cs] != -1 && map[cs] != cp) {
                return false;
            }

            map[cs] = cp;
        }

        boolean[] seen = new boolean[26];
        for(int i = 0; i < 26; i++) {
            if(map[i] == -1) {
                continue;
            }
            if(seen[map[i]]) {
                return false;
            }
            seen[map[i]] = true;
        }

        return true;
    }
}
