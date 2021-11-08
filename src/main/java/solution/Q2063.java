package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Vowels of All Substrings",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/vowels-of-all-substrings/"
)
public class Q2063 {
    /**
     * For each vowel character it could be within a substring [s[x], s[y]].
     * There are i + 1 choices for position x and n - i choices for y.
     *
     * Time:  O(N)
     * Space: O(1)
     * */
    public long countVowels(String word) {
        long ans = 0;
        int n = word.length();
        String v = "aeiou";

        for(int i = 0; i < n; i++) {
            int idx = v.indexOf(word.charAt(i));

            if(idx >= 0) {
                ans += ((long)i + 1) * (n - i);
            }
        }

        return ans;
    }
}
