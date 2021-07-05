package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Problem(
        title = "Find Minimum Time to Finish All Jobs",
        difficulty = QDifficulty.HARD,
        tag = QTag.BACKTRACKING,
        url = "https://leetcode.com/problems/find-minimum-time-to-finish-all-jobs/"
)
public class Q1723 {
    /*
        split array into k subsequence minimize maximum sum
        brute force with pruning
    */

    public int minimumTimeRequired(int[] jobs, int k) {
        return backtrackSolution(jobs, k);
    }

    private int res = 0;
    private int backtrackSolution(int[] jobs, int k) {
        // we can pre-process by sorting array in decending order and
        // calculate a better initial candidate.
        Arrays.sort(jobs);
        int n = jobs.length;
        for(int i = n - 1; i >= 0; i -= k) {
            res += jobs[i];
        }
        backtrack(jobs, new int[k], 0);
        return res;
    }

    private void backtrack(int[] jobs, int[] workers, int idx) {
        if(idx == jobs.length) {
            int max = 0;
            for(int w : workers) {
                max = Math.max(max, w);
            }
            res = Math.min(res, max);
            return;
        }

        Set<Integer> seen = new HashSet<>();
        for(int i = 0; i < workers.length; i++) {
            // 1,2,2,2,2 assign to workers 1 to 4 leads to the same result
            if(seen.contains(workers[i])) {
                continue;
            }
            // if current working hour is larger than the answer so far, no point to go further
            if(workers[i] + jobs[idx] >= res) {
                continue;
            }
            workers[i] += jobs[idx];
            backtrack(jobs, workers, idx + 1);
            workers[i] -= jobs[idx];

            seen.add(workers[i]);
        }
    }

    private int bottomUpDpSolution(int[] jobs, int k) {
        int n = jobs.length;
        return dfs(jobs, k, 1, (1 << n) - 1, new Integer[k + 1][1 << n]);
    }

    private int dfs(int[] jobs, int k, int w, int mask, Integer[][] memo) {
        if(w == k) {
            return getTime(jobs, mask);
        }

        if(memo[w][mask] != null) {
            return memo[w][mask];
        }

        int min = Integer.MAX_VALUE;

        for(int s = mask; s > 0; s = (s - 1) & mask) {
            int time = Math.max(getTime(jobs, s), dfs(jobs, k, w + 1, mask - s, memo));
            min = Math.min(min, time);
        }

        return memo[w][mask] = min;
    }

    private int getTime(int[] jobs, int s) {
        int n = jobs.length;
        int time = 0;
        for(int i = 0; i < n; i++) {
            time = ((1 << i) & s) != 0 ? time + jobs[i] : time;
        }
        return time;
    }
}
