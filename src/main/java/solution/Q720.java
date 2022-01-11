package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashSet;
import java.util.Set;

@Problem(
        title = "Longest Word in Dictionary",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/longest-word-in-dictionary/"
)
public class Q720 {
    /**
     * Time:  O(N * M ^ 2)
     * Space: O(N * M)
     * */
    public String longestWord(String[] words) {
        Set<String> set = new HashSet<>();
        String ans = "";

        for(String w : words) {
            set.add(w);
        }

        for(String w : words) {
            int n = w.length();
            boolean ok = true;
            for(int i = 1; i <= n; i++) {
                if(!set.contains(w.substring(0, i))) {
                    ok = false;
                    break;
                }
            }

            if(ok) {
                if(n > ans.length() || (n == ans.length() && w.compareTo(ans) < 0)) {
                    ans = w;
                }
            }
        }

        return ans;
    }
}
