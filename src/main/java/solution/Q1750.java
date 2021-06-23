package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimum Length of String After Deleting Similar Ends",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TWO_POINTERS,
        url = "https://leetcode.com/problems/minimum-length-of-string-after-deleting-similar-ends/"
)
public class Q1750 {
    public int minimumLength(String s) {
        int n = s.length();
        int l = 0;
        int r = n - 1;
        while(l < r && s.charAt(l) == s.charAt(r)) {
            char c = s.charAt(l);
            while(l < n && s.charAt(l) == c) {
                l++;
            }

            while(r >= 0 && s.charAt(r) == c) {
                r--;
            }
        }

        return l > r ? 0 : r - l + 1;
    }
}
