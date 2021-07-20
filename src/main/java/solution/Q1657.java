package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Determine if Two Strings Are Close",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/determine-if-two-strings-are-close/"
)
public class Q1657 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public boolean closeStrings(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        if(m != n) {
            return false;
        }

        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];

        for(int i = 0; i < m; i++) {
            cnt1[word1.charAt(i) - 'a']++;
            cnt2[word2.charAt(i) - 'a']++;
        }

        for(int i = 0; i < 26; i++) {
            if(cnt1[i] != cnt2[i] && (cnt1[i] == 0 || cnt2[i] == 0)) {
                return false;
            }
        }

        Arrays.sort(cnt1);
        Arrays.sort(cnt2);
        for(int i = 0; i < 26; i++) {
            if(cnt1[i] != cnt2[i]) {
                return false;
            }
        }

        return true;
    }
}
