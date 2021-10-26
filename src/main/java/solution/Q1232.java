package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Check If It Is a Straight Line",
        difficulty = QDifficulty.EASY,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/check-if-it-is-a-straight-line/"
)
public class Q1232 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public boolean checkStraightLine(int[][] c) {
        int n = c.length;
        for(int i = 2; i < n; i++) {
            if(cross(c[1][0] - c[0][0], c[1][1] - c[0][1], c[i][0] - c[0][0], c[i][1] - c[0][1]) != 0) {
                return false;
            }
        }
        return true;
    }

    private int cross(int x1, int y1, int x2, int y2) {
        return x1 * y2 - x2 * y1;
    }
}
