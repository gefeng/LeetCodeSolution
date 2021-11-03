package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Distance Between Bus Stops",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/distance-between-bus-stops/"
)
public class Q1184 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int distanceBetweenBusStops(int[] distance, int start, int destination) {
        int s = Math.min(start, destination);
        int d = Math.max(start, destination);

        int tot = 0;
        for(int x : distance) {
            tot += x;
        }

        int r1 = 0;
        for(int i = s; i < d; i++) {
            r1 += distance[i];
        }

        return Math.min(r1, tot - r1);
    }
}
