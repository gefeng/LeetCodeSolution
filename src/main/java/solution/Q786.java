package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

@Problem(
        title = "K-th Smallest Prime Fraction",
        difficulty = QDifficulty.HARD,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/k-th-smallest-prime-fraction/"
)
public class Q786 {
    /**
     * Manipulate on index.
     *
     * Time:  O(K * logN)
     * Space: O(N)
     * */
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        int n = arr.length;
        Queue<int[]> pq = new PriorityQueue<>(Comparator.comparingDouble(a -> (double)arr[a[0]] / arr[a[1]]));

        for(int i = 1; i < n; i++) {
            pq.offer(new int[] {0, i});
        }

        while(k > 1) {
            int[] cur = pq.poll();
            if(arr[++cur[0]] < arr[cur[1]]) {
                pq.offer(cur);
            }

            k--;
        }

        return new int[] {arr[pq.peek()[0]], arr[pq.peek()[1]]};
    }
}
