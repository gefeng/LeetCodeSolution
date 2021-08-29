package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.Comparator;

@Problem(
        title = "Maximum Profit in Job Scheduling",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/maximum-profit-in-job-scheduling/"
)
public class Q1235 {
    /**
     * Time:  O(N * logN)
     * Space: O(N)
     * */
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        int[][] jobs = new int[n][3];
        for(int i = 0; i < n; i++) {
            jobs[i] = new int[] { startTime[i], endTime[i], profit[i] };
        }

        Arrays.sort(jobs, Comparator.comparingInt(a -> a[0]));

        return dfs(jobs, 0, new Integer[n]);
    }

    private int dfs(int[][] jobs, int i, Integer[] memo) {
        if(i == jobs.length) {
            return 0;
        }

        if(memo[i] != null) {
            return memo[i];
        }

        return memo[i] = Math.max(dfs(jobs, i + 1, memo),
                dfs(jobs, next(jobs, i), memo) + jobs[i][2]);
    }

    private int next(int[][] jobs, int i) {
        int n = jobs.length;
        int lo = 0;
        int hi = n - 1;
        int next = n;

        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if(jobs[mid][0] >= jobs[i][1]) {
                next = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return next;
    }
}
