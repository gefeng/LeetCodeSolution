package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Maximum Number of Weeks for Which You Can Work",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/maximum-number-of-weeks-for-which-you-can-work/"
)
public class Q1953 {
    /**
     * 这轮contest败在这题上面，惨痛的教训，greedy算法需要加强.
     * EDF (earliest deadline first) problem. The task with max frequency dominate
     * the result.
     *
     * Time:  O(N * logN)
     * Space: O(logN)
     * */
    public long numberOfWeeks(int[] milestones) {
        long sum = 0;
        int max = 0;

        for(int m : milestones) {
            max = Math.max(max, m);
            sum += m;
        }

        return sum - max >= max ? sum : (sum - max) * 2 + 1;
    }
}
