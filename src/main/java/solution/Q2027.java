package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimum Moves to Converting String",
        difficulty = QDifficulty.EASY,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/minimum-moves-to-convert-string/"
)
public class Q2027 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int minimumMoves(String s) {
        int n = s.length();
        int ans = 0;

        int l = 0;
        int r = 2;
        while(l < n) {
            if(s.charAt(l) == 'X') {
                ans++;
                l += 3;
                r += 3;
            } else {
                l += 1;
                r += 1;
            }
        }

        return ans;
    }
}
