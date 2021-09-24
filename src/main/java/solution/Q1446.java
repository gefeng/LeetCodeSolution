package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Consecutive Characters",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/consecutive-characters/"
)
public class Q1446 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int maxPower(String s) {
        int n = s.length();
        int ans = 1;

        int cnt = 1;
        for(int i = 1; i < n; i++) {
            if(s.charAt(i) == s.charAt(i - 1)) {
                cnt++;
            } else {
                cnt = 1;
            }
            ans = Math.max(ans, cnt);
        }

        return ans;
    }
}
