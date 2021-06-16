package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Check if One String Swap Can Make Strings Equal",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/check-if-one-string-swap-can-make-strings-equal/"
)
public class Q1790 {
    public boolean areAlmostEqual(String s1, String s2) {
        int n = s1.length();
        int i = -1;
        int j = -1;
        for(int k = 0; k < n; k++) {
            if(s1.charAt(k) != s2.charAt(k)) {
                if(i == -1) {
                    i = k;
                } else if(j == -1) {
                    j = k;
                } else {
                    return false;
                }
            }
        }
        return (i == -1 && j == -1) || (i != -1 && j != -1 && s1.charAt(i) == s2.charAt(j) && s1.charAt(j) == s2.charAt(i));
    }
}
