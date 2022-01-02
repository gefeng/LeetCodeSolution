package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Check if All A's Appears Before All B's",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/check-if-all-as-appears-before-all-bs/"
)
public class Q2124 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public boolean checkString(String s) {
        int n = s.length();

        boolean seenb = false;
        for(int i = 0; i < n; i++) {
            if(s.charAt(i) == 'a') {
                if(seenb) return false;
            } else {
                seenb = true;
            }
        }

        return true;
    }
}
