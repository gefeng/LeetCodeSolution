package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashSet;
import java.util.Set;

@Problem(
        title = "Erect the Fence",
        difficulty = QDifficulty.HARD,
        tag = QTag.GEOMETRY,
        url = "https://leetcode.com/problems/erect-the-fence/"
)
public class Q587 {
    /**
     * Implement Jarvis Algorithm
     *
     * Time:  O(M * N)
     * Space: O(M)
     * */
    public int[][] outerTrees(int[][] trees) {
        int n = trees.length;
        Set<int[]> convex = new HashSet<>();

        int[] leftMost = new int[] {101, 101};
        for(int[] t : trees) {
            if(t[0] < leftMost[0]) {
                leftMost = t;
            }
        }

        int[] pointOnHull = leftMost;
        convex.add(pointOnHull);

        do {
            int[] endpoint = trees[0];

            for(int j = 1; j < n; j++) {
                if(equals(pointOnHull, trees[j])) {
                    continue;
                }
                int cross = cross(pointOnHull, endpoint, trees[j]);
                if(equals(pointOnHull, endpoint) || cross > 0 ||
                        (cross == 0 && dist(pointOnHull, endpoint) < dist(pointOnHull, trees[j]))) {
                    endpoint = trees[j];
                }
            }

            for(int j = 0; j < n; j++) {
                if(equals(pointOnHull, trees[j])) {
                    continue;
                }
                if(cross(pointOnHull, endpoint, trees[j]) == 0) {
                    convex.add(trees[j]);
                }
            }

            pointOnHull = endpoint;
        } while(!equals(pointOnHull, leftMost));

        return convex.toArray(new int[convex.size()][2]);
    }

    private boolean equals(int[] x, int[] y) {
        return x[0] == y[0] && x[1] == y[1];
    }

    private int cross(int[] x, int[] y, int[] z) {
        int[] v1 = new int[] {y[0] - x[0], y[1] - x[1]};
        int[] v2 = new int[] {z[0] - x[0], z[1] - x[1]};
        return v1[0] * v2[1] - v1[1] * v2[0];
    }

    private int dist(int[] x, int[] y) {
        return (x[0] - y[0]) * (x[0] - y[0]) + (x[1] - y[1]) * (x[1] - y[1]);
    }
}
