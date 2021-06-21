package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Check if Binary String Has at Most One Segment of Ones",
        difficulty = QDifficulty.EASY,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/check-if-binary-string-has-at-most-one-segment-of-ones/"
)
public class Q1784 {
    public boolean checkOnesSegment(String s) {
        int n = s.length();
        int i = 0;
        while(i < n && s.charAt(i) == '1') {
            i++;
        }

        while(i < n) {
            if(s.charAt(i) == '1') {
                return false;
            }
            i++;
        }

        return true;
    }
}
