package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Find First Palindromic String in the Array",
        difficulty = QDifficulty.EASY,
        tag = QTag.TWO_POINTERS,
        url = "https://leetcode.com/problems/find-first-palindromic-string-in-the-array/"
)
public class Q2108 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public String firstPalindrome(String[] words) {
        for(String s : words) {
            boolean isPal = true;
            for(int l = 0, r = s.length() - 1; l < r; l++, r--) {
                if(s.charAt(l) != s.charAt(r)) {
                    isPal = false;
                    break;
                }
            }

            if(isPal) return s;
        }

        return "";
    }
}
