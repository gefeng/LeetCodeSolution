package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Maximum Score After Splitting a String",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/maximum-score-after-splitting-a-string/"
)
public class Q1422 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int maxScore(String s) {
        int n = s.length();
        int ans = 0;

        int cntOnes = 0;
        for(int i = 0; i < n; i++) {
            cntOnes = s.charAt(i) == '1' ? cntOnes + 1 : cntOnes;
        }

        int cnt1 = 0;
        int cnt2 = 0;
        for(int i = 0; i < n - 1; i++) {
            char c = s.charAt(i);
            if(c == '0') {
                cnt1++;
            } else {
                cnt2++;
            }

            ans = Math.max(ans, cnt1 + cntOnes - cnt2);
        }

        return ans;
    }
}
