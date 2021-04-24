package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Problem(
        title = "Queue Reconstruction by Height",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/queue-reconstruction-by-height/"
)
public class Q406 {
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (a, b) -> {
            if(a[0] == b[0]) {
                return a[1] - b[1];
            }
            return b[0] - a[0];
        });

        List<int[]> reorder = new ArrayList<>();
        for(int[] p : people) {
            reorder.add(p[1], p);
        }

        return reorder.toArray(new int[people.length][2]);
    }
}
