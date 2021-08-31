package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Range Addition II",
        difficulty = QDifficulty.EASY,
        tag = QTag.MATRIX,
        url = "https://leetcode.com/problems/range-addition-ii/"
)
public class Q598 {
    /**
     * Time:  O(M * N)
     * Space: O(1)
     * */
    public int maxCount(int m, int n, int[][] ops) {
        int minw = m;
        int minh = n;

        for(int[] op : ops) {
            minw = Math.min(minw, op[0]);
            minh = Math.min(minh, op[1]);
        }

        return minw * minh;
    }
}
