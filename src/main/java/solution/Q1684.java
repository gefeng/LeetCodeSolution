package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Count the Number of Consistent Strings",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/count-the-number-of-consistent-strings/"
)
public class Q1684 {
    public int countConsistentStrings(String allowed, String[] words) {
        int[] cnt = new int[26];
        int m = allowed.length();
        int ans = 0;

        for(int i = 0; i < m; i++) {
            cnt[allowed.charAt(i) - 'a']++;
        }

        for(String word : words) {
            int n = word.length();
            boolean isCons = true;
            for(int i = 0; i < n; i++) {
                if(cnt[word.charAt(i) - 'a'] == 0) {
                    isCons = false;
                    break;
                }
            }
            ans = isCons ? ans + 1 : ans;
        }

        return ans;
    }
}
