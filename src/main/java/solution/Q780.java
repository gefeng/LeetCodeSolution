package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Reaching Points",
        difficulty = QDifficulty.HARD,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/reaching-points/"
)
public class Q780 {
    /**
     * Time:  O(log(max(tx, ty))) similar to GCD
     * Space: O(1)
     * */
    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        int x = tx;
        int y = ty;
        while(true) {
            if(x == sx && y == sy) return true;
            if(x == y) return false;
            if(x < sx || y < sy) return false;

            if(x < y) {
                if(x == sx) {
                    if((y - sy) % x == 0) {
                        y = sy;
                        continue;
                    }
                }
                y %= x;
            } else {
                if(y == sy) {
                    if((x - sx) % y == 0) {
                        x = sx;
                        continue;
                    }
                }
                x %= y;
            }
        }
    }
}
