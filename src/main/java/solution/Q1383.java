package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.PriorityQueue;

@Problem(
        title = "Maximum Performance of a Team",
        difficulty = QDifficulty.HARD,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/maximum-performance-of-a-team/"
)
public class Q1383 {
    /**
     * Time:  O(N * logN)
     * Space: O(N)
     * */
    private static final int MOD = (int)1e9 + 7;
    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        int[][] pairs = new int[n][2];
        for(int i = 0; i < n; i++) {
            pairs[i][0] = speed[i];
            pairs[i][1] = efficiency[i];
        }

        // sort by efficiency in decreasing order
        Arrays.sort(pairs, (a, b) -> Integer.compare(b[1], a[1]));

        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        long max = 0;
        long currSpeed = 0;
        int currEfficiency = 0;
        for(int i = 0; i < n; i++) {
            minHeap.offer(pairs[i]);
            currSpeed += pairs[i][0];

            if(minHeap.size() > k) {
                int[] prev = minHeap.poll();
                currSpeed -= prev[0];
                currEfficiency = prev != pairs[i] ? pairs[i][1] : currEfficiency;
            } else {
                currEfficiency = pairs[i][1];
            }

            max = Math.max(max, (long)currSpeed * currEfficiency);
        }

        return (int)(max % MOD);
    }
}
