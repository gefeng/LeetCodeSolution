package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Palindrome Permutation",
        difficulty = QDifficulty.EASY,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/palindrome-permutation/"
)
public class Q266 {
    public boolean canPermutePalindrome(String s) {
        int[] count = new int[26];
        int countOdd = 0;

        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            count[c - 'a']++;
            if(count[c - 'a'] % 2 != 0) {
                countOdd++;
            } else {
                countOdd--;
            }
        }

        return countOdd < 2;
    }
}
