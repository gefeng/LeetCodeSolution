package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimum Add to Make Parentheses Valid",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STACK,
        url = "https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/"
)
public class Q921 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int minAddToMakeValid(String s) {
        int n = s.length();
        int cnt = 0;
        int ans = 0;

        for(int i = 0; i < n; i++) {
            if(s.charAt(i) == '(') {
                cnt++;
            } else {
                if(cnt == 0) {
                    ans++;
                } else {
                    cnt--;
                }
            }
        }

        return ans + cnt;
    }
}
