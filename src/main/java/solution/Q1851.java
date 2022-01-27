package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

@Problem(
        title = "Minimum Interval to Include Each Query",
        difficulty = QDifficulty.HARD,
        tag = QTag.SORT,
        url = "https://leetcode.com/problems/minimum-interval-to-include-each-query/"
)
public class Q1851 {
    /**
     * Intuition:
     *  Sort query in ascending order.
     *  Sort intervals by left in ascending order.
     *
     *  All the intervals has a left bound <= q[i] should be candidate. We can use a heap to save all candidates
     *  by their length.
     *  We can encounter 2 cases,
     *  1. a interval has right bound < q[i] which means they are non-overlapping. Simply pop it from heap as it
     *  can't be the answer for current query and the rest of the queries.
     *  2. a interval has right bound >= q[i] which means it includes q[i] and since it has the minimum size, it
     *  must be the answer for current query. We should not pop it as it could be the answer for the rest the queries.
     *
     * Time:  O(M * logM + N * logN + M * logN)
     * Space: O(M + N)
     * */
    public int[] minInterval(int[][] intervals, int[] queries) {
        int n = intervals.length;
        int m = queries.length;
        int[] ans = new int[m];
        int[][] sq = new int[m][2];

        for(int i = 0; i < m; i++) {
            sq[i] = new int[] {queries[i], i};
        }

        Arrays.fill(ans, -1);
        Arrays.sort(sq, Comparator.comparingInt(a -> a[0]));
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        Queue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1] - a[0] + 1));
        for(int i = 0, j = 0; i < m; i++) {
            while(j < n && intervals[j][0] <= sq[i][0]) {
                pq.offer(intervals[j++]);
            }

            while(!pq.isEmpty()) {
                int[] cur = pq.peek();

                if(cur[1] < sq[i][0]) {
                    pq.poll();
                } else {
                    ans[sq[i][1]] = cur[1] - cur[0] + 1;
                    break;
                }
            }
        }

        return ans;
    }
}
