package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Word Subsets",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/word-subsets/"
)
public class Q916 {
    /**
     * Time:  O(M * L + N * L)
     * Space: O(M * L)
     * */
    public List<String> wordSubsets(String[] words1, String[] words2) {
        List<String> ans = new ArrayList<>();
        int[] cnt = new int[26];

        for(String s : words2) {
            int len = s.length();
            int[] local = new int[26];
            for(int i = 0; i < len; i++) {
                local[s.charAt(i) - 'a']++;
            }
            for(int i = 0; i < 26; i++) {
                cnt[i] = Math.max(cnt[i], local[i]);
            }
        }

        for(String s : words1) {
            int len = s.length();
            int[] local = new int[26];
            for(int i = 0; i < len; i++) {
                local[s.charAt(i) - 'a']++;
            }

            boolean isOk = true;
            for(int i = 0; i < 26; i++) {
                if(cnt[i] > local[i]) {
                    isOk = false;
                    break;
                }
            }
            if(isOk) {
                ans.add(s);
            }
        }

        return ans;
    }
}
