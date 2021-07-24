package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Check if All Characters Have Equal Number of Occurrences",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/check-if-all-characters-have-equal-number-of-occurrences/"
)
public class Q1941 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public boolean areOccurrencesEqual(String s) {
        int n = s.length();
        int max = 0;
        int[] cnt = new int[26];

        for(int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            cnt[c]++;
            max = Math.max(max, cnt[c]);
        }

        for(int i = 0; i < 26; i++) {
            if(cnt[i] != 0 && cnt[i] != max) {
                return false;
            }
        }

        return true;
    }
}
