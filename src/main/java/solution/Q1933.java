package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Check if String Is Decomposable Into Value-Equal Substrings",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/check-if-string-is-decomposable-into-value-equal-substrings/"
)
public class Q1933 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public boolean isDecomposable(String s) {
        int n = s.length();
        boolean two = false;
        for(int i = 0; i < n; ) {
            int j = i;
            while(j < n && s.charAt(j) == s.charAt(i)) {
                j++;
            }
            if(j - i < 2) return false;
            if((j - i) % 3 == 2) {
                if(two) return false;
                two = true;
            } else if((j - i) % 3 != 0) {
                return false;
            }
            i = j;
        }

        return two;
    }
}
