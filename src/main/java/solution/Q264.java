package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

@Problem(
        title = "Ugly Number II",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/ugly-number-ii/"
)
public class Q264 {
    public int nthUglyNumber(int n) {
        return dpSol(n);
    }

    private int dpSol(int n) {
        int[] indices = new int[3];
        int[] factors = new int[] {2, 3, 5};
        int[] ugly = new int[n];
        ugly[0] = 1;

        for(int i = 1; i < n; i++) {
            int cand = Integer.MAX_VALUE;
            for(int j = 0; j < 3; j++) {
                cand = Math.min(cand, ugly[indices[j]] * factors[j]);
            }
            for(int j = 0; j < 3; j++) {
                if(ugly[indices[j]] * factors[j] == cand) {
                    indices[j]++;
                }
            }

            ugly[i] = cand;
        }

        return ugly[n - 1];
    }

    private int heapSol(int n) {
        int[] factors = new int[] {2, 3, 5};
        Queue<Long> minHeap = new PriorityQueue<>();
        Set<Long> seen = new HashSet<>();
        minHeap.offer(1L);
        seen.add(1L);

        long nth = 0;
        while(n > 0) {
            nth = minHeap.poll();

            for(int f : factors) {
                if(!seen.contains(nth * f)) {
                    minHeap.offer(nth * f);
                    seen.add(nth * f);
                }
            }

            n--;
        }

        return (int)nth;
    }
}
