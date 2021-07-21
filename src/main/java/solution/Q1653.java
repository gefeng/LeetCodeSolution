package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimum Deletions to Make String Balanced",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/minimum-deletions-to-make-string-balanced/"
)
public class Q1653 {
    /**
     * for each character, store # a to the right and # b to the left
     * Time:  O(N)
     * Space: O(1)
     * */
    public int minimumDeletions(String s) {
        int n = s.length();
        int cntA = 0;
        int cntB = 0;
        int ans = Integer.MAX_VALUE;

        for(int i = 0; i < n; i++) {
            if(s.charAt(i) == 'a') {
                cntA++;
            }
        }

        for(int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if(c == 'a') {
                cntA--;
            }

            ans = Math.min(ans, cntA + cntB);

            if(c == 'b') {
                cntB++;
            }
        }

        return ans;
    }
}
