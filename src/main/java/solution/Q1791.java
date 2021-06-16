package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Find Center of Star Graph",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GRAPH,
        url = "https://leetcode.com/problems/find-center-of-star-graph/"
)
public class Q1791 {
    public int findCenter(int[][] edges) {
        int[] e1 = edges[0];
        int[] e2 = edges[1];

        int center = e1[0];
        if(e2[0] == center || e2[1] == center) {
            return center;
        }
        return e1[1];
    }
}
