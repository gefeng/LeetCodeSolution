package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

@Problem(
        title = "Most Profit Assigning Work",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.SORT,
        url = "https://leetcode.com/contest/weekly-contest-82/problems/most-profit-assigning-work/"
)
public class Q826 {
    /**
     * Time:  O(M * logM + N * logN + M + N)
     * Space: O(logM + N)
     * */
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int n = difficulty.length;
        int m = worker.length;
        int ans = 0;
        int[][] jobs = new int[n][2];

        for(int i = 0; i < n; i++) {
            jobs[i][0] = difficulty[i];
            jobs[i][1] = profit[i];
        }

        Arrays.sort(worker);
        Arrays.sort(jobs, Comparator.comparingInt(a -> a[0]));

        int best = -1;
        for(int i = 0, j = 0; i < m; i++) {
            while(j < n && jobs[j][0] <= worker[i]) {
                best = Math.max(best, jobs[j][1]);
                j++;
            }

            if(best != -1) {
                ans += best;
            }
        }

        return ans;
    }
}
