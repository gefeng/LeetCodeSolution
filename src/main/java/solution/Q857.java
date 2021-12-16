package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

@Problem(
        title = "Minimum Cost to Hire K Workers",
        difficulty = QDifficulty.HARD,
        tag = QTag.HEAP,
        url = "https://leetcode.com/problems/minimum-cost-to-hire-k-workers/"
)
public class Q857 {
    /**
     * Time:  O(N * logN)
     * Space: O(N)
     * */
    public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        double ans = 0;
        int n = quality.length;
        int[][] p = new int[n][2];

        for(int i = 0; i < n; i++) {
            p[i] = new int[] {wage[i], quality[i]};
        }

        // sort array by WAGE PER QUALITY, the overall pay will determined by the max wage per quality.
        Arrays.sort(p, Comparator.comparingDouble(a -> (double)a[0] / a[1]));

        // the worker with maximum quality has the highest pay, so remove him if group size reaches k.
        Queue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        int sumq = 0;
        for(int i = 0; i < n; i++) {
            int[] cur = p[i];
            if(pq.size() == k) {
                sumq -= pq.poll();
                sumq += cur[1];
                ans = Math.min(ans, sumq * ((double)cur[0] / cur[1]));
            } else {
                sumq += cur[1];
                ans = sumq * ((double)cur[0] / cur[1]);
            }

            pq.offer(cur[1]);
        }

        return ans;
    }
}
