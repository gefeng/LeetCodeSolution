package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Coordinate With Maximum Network Quality",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/coordinate-with-maximum-network-quality/"
)
public class Q1620 {
    /**
     * R: max radius tower can cover
     * N: number of towers
     * Time:  O(R * R * N)
     * Space: O(1)
     * */
    public int[] bestCoordinate(int[][] towers, int radius) {
        int[] res = new int[2];
        Arrays.fill(res, Integer.MAX_VALUE);
        int maxq = 0;
        for(int i = 0; i < 101; i++) {
            for(int j = 0; j < 101; j++) {
                int q = 0;
                for(int[] t : towers) {
                    double d = getDist(i, j, t[0], t[1]);
                    q += d > radius ? 0 : (int)(t[2] / (1 + d));
                }

                if(q > maxq) {
                    maxq = q;
                    res[0] = i;
                    res[1] = j;
                } else if(q == maxq) {
                    if(i < res[0] || (i == res[0] && j < res[1])) {
                        res[0] = i;
                        res[1] = j;
                    }
                }
            }
        }

        return res;
    }

    private double getDist(int x1, int y1, int x2, int y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }
}
