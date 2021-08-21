package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Random;

@Problem(
        title = "Random Point in Non-overlapping Rectangle",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.RESERVOIR_SAMPLING,
        url = "https://leetcode.com/problems/random-point-in-non-overlapping-rectangles/"
)
public class Q497 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    private Random rand;
    private int[][] rects;
    private int[] areas;
    public Q497(int[][] rects) {
        int n = rects.length;

        this.rects = rects;
        this.areas = new int[n];
        this.rand = new Random();

        for(int i = 0; i < n; i++) {
            int[] r = rects[i];
            areas[i] = (r[2] - r[0] + 1) * (r[3] - r[1] + 1);
        }
    }

    public int[] pick() {
        int u = 0;
        int v = 0;
        int n = rects.length;

        int k = 0;
        int total = 0;
        for(int i = 0; i < n; i++) {
            total += areas[i];

            if(rand.nextInt(total) < areas[i]) {
                k = i;
            }

            // why not work????
            // if(rand.nextDouble() < areas[i] / total) {
            //     k = i;
            // }
        }

        int[] r = rects[k];
        u = r[0] + rand.nextInt(r[2] - r[0] + 1);
        v = r[1] + rand.nextInt(r[3] - r[1] + 1);

        return new int[] {u, v};
    }
}
