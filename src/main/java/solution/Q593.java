package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Valid Square",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/valid-square/"
)
public class Q593 {
    /**
     * Time:  O(1)
     * Space: O(1)
     * */
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        int[] dist = new int[6];
        int[][] points = new int[][] {p1, p2, p3, p4};

        for(int i = 0, k = 0; i < 3; i++) {
            for(int j = i + 1; j < 4; j++) {
                dist[k++] = dist(points[i], points[j]);
            }
        }

        Arrays.sort(dist);

        if(dist[0] == 0) {
            return false;
        }

        return dist[0] == dist[1] && dist[0] == dist[2] && dist[0] == dist[3] && dist[4] == dist[5];
    }

    private int dist(int[] x, int[] y) {
        return (x[0] - y[0]) * (x[0] - y[0]) + (x[1] - y[1]) * (x[1] - y[1]);
    }
}
