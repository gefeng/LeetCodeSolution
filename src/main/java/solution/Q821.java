package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Shortest Distance to a Character",
        difficulty = QDifficulty.EASY,
        tag = QTag.TWO_POINTERS,
        url = "https://leetcode.com/problems/shortest-distance-to-a-character/"
)
public class Q821 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int[] shortestToChar(String s, char c) {
        int n = s.length();
        int[] ans = new int[n];

        int[] l = new int[n];

        int pre = -1;
        for(int i = 0; i < n; i++) {
            if(s.charAt(i) == c) {
                pre = i;
            }

            l[i] = pre;
        }

        pre = -1;
        for(int i = n - 1; i >= 0; i--) {
            if(s.charAt(i) == c) {
                pre = i;
            }

            if(l[i] == -1) {
                ans[i] = pre - i;
            } else if(pre == -1) {
                ans[i] = i - l[i];
            } else {
                ans[i] = pre - i > i - l[i] ? i - l[i] : pre - i;
            }
        }

        return ans;
    }
}
