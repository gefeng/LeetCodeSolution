package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Elimination Game",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/elimination-game/"
)
public class Q390 {
    /**
     * Time:  O(logN)
     * Space: O(1)
     * */
    public int lastRemaining(int n) {
        int l = 1;
        int r = n;
        int k = 1;
        int len = r - l + 1;
        boolean forward = true;

        while(l < r) {
            if(forward) {
                l += k;
                r = len % 2 == 0 ? r : r - k;
            } else {
                l = len % 2 == 0 ? l : l + k;
                r -= k;
            }

            k *= 2;
            len /= 2;
            forward = !forward;
        }

        return l;
    }
}
