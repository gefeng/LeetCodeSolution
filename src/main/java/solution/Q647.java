package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Palindromic Substrings",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/palindromic-substrings/"
)
public class Q647 {
    public int countSubstrings(String s) {
        int count = 0;
        int n = s.length();
        for(int i = 0; i < n - 1; i++) {
            int lo = i;
            int hi = i;
            while(lo >= 0 && hi < n) {
                if(s.charAt(lo--) != s.charAt(hi++)) {
                    break;
                }
                count++;
            }

            lo = i;
            hi = i + 1;
            while(lo >= 0 && hi < n) {
                if(s.charAt(lo--) != s.charAt(hi++)) {
                    break;
                }
                count++;
            }
        }

        return count + 1; // count last character
    }
}
