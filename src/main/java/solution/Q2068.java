package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Check Whether Two Strings are Almost Equivalent",
        difficulty = QDifficulty.EASY,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/check-whether-two-strings-are-almost-equivalent/"
)
public class Q2068 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public boolean checkAlmostEquivalent(String word1, String word2) {
        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];

        for(int i = 0; i < word1.length(); i++) {
            cnt1[word1.charAt(i) - 'a'] += 1;
            cnt2[word2.charAt(i) - 'a'] += 1;
        }

        for(int i = 0; i < 26; i++) {
            if(Math.abs(cnt1[i] - cnt2[i]) > 3) {
                return false;
            }
        }

        return true;
    }
}
