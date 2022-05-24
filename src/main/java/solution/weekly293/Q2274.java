package solution.weekly293;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Maximum Consecutive Floors Without Special Floors",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/contest/weekly-contest-293/problems/maximum-consecutive-floors-without-special-floors/"
)
public class Q2274 {
    /**
     * Time:  O(N * logN)
     * Space: O(logN)
     * */
    public int maxConsecutive(int bottom, int top, int[] special) {
        int ans = 0;
        int n = special.length;

        Arrays.sort(special);

        for(int i = 1; i < n; i++) {
            ans = Math.max(ans, special[i] - special[i - 1] - 1);
        }

        if(special[0] > bottom) {
            ans = Math.max(ans, special[0] - bottom);
        }

        if(special[n - 1] < top) {
            ans = Math.max(ans, top - special[n - 1]);
        }

        return ans;
    }
}
