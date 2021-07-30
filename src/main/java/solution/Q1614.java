package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Maximum Nesting Depth of the Parentheses",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/maximum-nesting-depth-of-the-parentheses/"
)
public class Q1614 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int maxDepth(String s) {
        int n = s.length();
        int res = 0;
        int cntL = 0;

        for(int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if(c == '(') {
                cntL++;
            } else if(c == ')') {
                cntL--;
            }

            res = Math.max(cntL, res);
        }

        return res;
    }
}
