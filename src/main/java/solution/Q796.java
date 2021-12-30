package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Rotate String",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/rotate-string/"
)
public class Q796 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public boolean rotateString(String s, String goal) {
        int n = s.length();
        if(n != goal.length()) return false;

        for(int i = 0; i < n; i++) {
            boolean ok = true;
            for(int j = 0; j < n; j++) {
                if(s.charAt(j) != goal.charAt((j + i) % n)) {
                    ok = false;
                    break;
                }
            }
            if(ok) return true;
        }

        return false;
    }
}
