package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimum Changes To Make Alternating Binary String",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/minimum-changes-to-make-alternating-binary-string/"
)
public class Q1758 {
    public int minOperations(String s) {
        return Math.min(cntSteps(s, '0'), cntSteps(s, '1'));
    }

    private int cntSteps(String s, char init) {
        int n = s.length();
        int cnt = 0;
        for(int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if(c != init) {
                cnt++;
            }
            init = init == '0' ? '1' : '0';
        }
        return cnt;
    }
}
