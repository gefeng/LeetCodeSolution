package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimum Difficulty of a Job Schedule",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/minimum-difficulty-of-a-job-schedule/"
)
public class Q1335 {
    public int minDifficulty(int[] jobDifficulty, int d) {
        if(d > jobDifficulty.length)
            return -1;

        return dfsMinDifficulty(jobDifficulty, d, 0, new Integer[d + 1][jobDifficulty.length]);
    }

    private int dfsMinDifficulty(int[] jobDifficulty, int d, int start, Integer[][] memo) {
        if(d == 0 && start == jobDifficulty.length)
            return 0;
        if(d == 0 || start == jobDifficulty.length)
            return Integer.MAX_VALUE;

        if(memo[d][start] != null)
            return memo[d][start];

        int ret = Integer.MAX_VALUE;
        int scheduleMin = Integer.MAX_VALUE;
        int dayMax = 0;
        for(int cut = start + 1; cut <= jobDifficulty.length; cut++) {
            dayMax = Math.max(dayMax, jobDifficulty[cut - 1]);
            scheduleMin = dfsMinDifficulty(jobDifficulty, d - 1, cut, memo);
            if(scheduleMin == Integer.MAX_VALUE)
                continue;
            ret = Math.min(ret, scheduleMin + dayMax);
        }

        memo[d][start] = ret;
        return ret;
    }
}
