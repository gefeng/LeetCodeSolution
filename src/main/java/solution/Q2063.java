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
     *
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
