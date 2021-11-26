package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "DI String Match",
        difficulty = QDifficulty.EASY,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/di-string-match/"
)
public class Q942 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int[] diStringMatch(String s) {
        int n = s.length();
        int[] ans = new int[n + 1];
        Arrays.fill(ans, -1);

        int cur = n;
        for(int i = 0; i < n; i++) {
            if(s.charAt(i) == 'D') {
                ans[i] = cur--;
            }
        }

        cur = 0;
        for(int i = 0; i <= n; i++) {
            if(ans[i] == -1) {
                ans[i] = cur++;
            }
        }

        return ans;
    }
}
