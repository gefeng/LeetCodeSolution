package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Escape The Ghosts",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/escape-the-ghosts/"
)
public class Q789 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public boolean escapeGhosts(int[][] ghosts, int[] target) {
        int td = dist(target, new int[] {0, 0});

        for(int[] g : ghosts) {
            int d = dist(g, target);

            if(d <= td) return false;
        }

        return true;
    }

    private int dist(int[] x, int[] y) {
        return Math.abs(x[0] - y[0]) + Math.abs(x[1] - y[1]);
    }
}
