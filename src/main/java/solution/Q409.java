package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Longest Palindrome",
        difficulty = QDifficulty.EASY,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/longest-palindrome/"
)
public class Q409 {
    public int longestPalindrome(String s) {
        int countLowerCase[] = new int[26];
        int countUpperCase[] = new int[26];
        int longest = 0;

        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c >= 'A' && c <= 'Z')
                countUpperCase[c - 'A']++;
            else
                countLowerCase[c - 'a']++;
        }

        for(int i = 0; i < 26; i++) {
            longest += (countLowerCase[i] / 2 * 2);
            longest += (countUpperCase[i] / 2 * 2);
        }

        return longest != s.length() ? longest + 1 : longest;
    }
}
