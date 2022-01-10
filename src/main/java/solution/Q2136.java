package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.Comparator;

@Problem(
        title = "Earliest Possible Day of Full Bloom",
        difficulty = QDifficulty.HARD,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/earliest-possible-day-of-full-bloom/"
)
public class Q2136 {
    /**
     * Time:  O(N * logN)
     * Space: O(N)
     * */
    public int earliestFullBloom(int[] plantTime, int[] growTime) {
        int n = plantTime.length;
        int[][] p = new int[n][2];

        for(int i = 0; i < n; i++) {
            p[i][0] = plantTime[i];
            p[i][1] = growTime[i];
        }

        Arrays.sort(p, Comparator.comparing(a -> a[1], Comparator.reverseOrder()));

        int cur = 0;
        int bloom = 0;
        for(int i = 0; i < n; i++) {
            cur += p[i][0];
            bloom = Math.max(bloom, cur + p[i][1]);
        }

        return bloom;
    }
}
