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
     * 这轮contest败在这题上面，惨痛的教训，greedy算法需要加强
     *
     * Time:  O(N * logN)
     * Space: O(logN)
     * */
    public long numberOfWeeks(int[] milestones) {
        int n = milestones.length;

        if(n == 1) {
            return 1;
        }

        Arrays.sort(milestones);

        long sum = 0;
        for(int i = 0; i < n; i++) {
            sum += milestones[i];
        }

        return sum - milestones[n - 1] >= milestones[n - 1] ? sum : (sum - milestones[n - 1]) * 2 + 1;
    }
}
