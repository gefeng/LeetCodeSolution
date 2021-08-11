package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Most Visited Sector in a Circular Track",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/most-visited-sector-in-a-circular-track/"
)
public class Q1560 {
    /**
     * s------
     * -------
     * -------
     * ------e
     *
     *    s--
     * -------
     * -------
     * --e
     *
     * Time:  O(N)
     * Space: O(N)
     * */
    public List<Integer> mostVisited(int n, int[] rounds) {
        int start = rounds[0];
        int end = rounds[rounds.length - 1];
        List<Integer> res = new ArrayList<>();

        if(start <= end) {
            for(int i = start; i <= end; i++) {
                res.add(i);
            }
        } else {
            for(int i = 1; i <= end; i++) {
                res.add(i);
            }
            for(int i = start; i <= n; i++) {
                res.add(i);
            }
        }

        return res;
    }
}
