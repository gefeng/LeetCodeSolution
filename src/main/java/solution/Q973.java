package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.Random;

@Problem(
        title = "K Closest Points to Origin",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DIVIDE_AND_CONQUER,
        url = "https://leetcode.com/problems/k-closest-points-to-origin/"
)
public class Q973 {
    Random rand = new Random();
    public int[][] kClosest(int[][] points, int K) {
        return selection(points, 0, points.length - 1, K);
    }

    private int partition(int[][] points, int lo, int hi) {
        int pIndex = lo + rand.nextInt(hi + 1 - lo);
        int[] pivot = points[pIndex];
        int pDist = pivot[0] * pivot[0] + pivot[1] * pivot[1];

        points[pIndex] = points[hi];
        points[hi] = pivot;

        pIndex = 0;
        for(int i = 0; i < hi; i++) {
            int dist = points[i][0] * points[i][0] + points[i][1] * points[i][1];
            if(dist < pDist) {
                int[] temp = points[i];
                points[i] = points[pIndex];
                points[pIndex++] = temp;
            }
        }

        points[hi] = points[pIndex];
        points[pIndex] = pivot;

        return pIndex;
    }

    private int[][] selection(int[][] points, int lo, int hi, int K) {
        if(lo == hi)
            return Arrays.copyOfRange(points, 0, K);

        int pIndex = partition(points, lo, hi);
        if(K == pIndex + 1)
            return Arrays.copyOfRange(points, 0, K);
        else if(K < pIndex + 1)
            return selection(points, lo, pIndex - 1, K);
        else
            return selection(points, pIndex + 1, hi, K);
    }
}
