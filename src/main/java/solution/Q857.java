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
        double ans = Double.MAX_VALUE;
        int n = quality.length;
        int[][] qw = new int[n][2];

        for(int i = 0; i < n; i++) {
            qw[i][0] = quality[i];
            qw[i][1] = wage[i];
        }

        Arrays.sort(qw, Comparator.comparingDouble(a -> (double)a[1] / a[0]));

        Queue<int[]> maxHeap = new PriorityQueue<>(Comparator.comparing(a -> a[0], Comparator.reverseOrder()));
        int[] max = null;
        int sum = 0;
        for(int i = 0; i < n; i++) {
            maxHeap.offer(qw[i]);

            sum += qw[i][0];

            if(maxHeap.size() > k) {
                int[] pre = maxHeap.poll();
                if(pre != qw[i]) {
                    max = qw[i];
                }
                sum -= pre[0];
            } else {
                max = qw[i];
            }

            if(maxHeap.size() == k) {
                ans = Math.min(ans, ((double)max[1] * sum) / max[0]);
            }
        }

        return ans;
    }
}
