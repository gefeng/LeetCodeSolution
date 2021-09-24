package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Find the Kth Smallest Sum of a Matrix With Sorted Rows",
        difficulty = QDifficulty.HARD,
        tag = QTag.HEAP,
        url = "https://leetcode.com/problems/find-the-kth-smallest-sum-of-a-matrix-with-sorted-rows/"
)
public class Q1439 {
    /**
     * Time:  O(M * N * log(K * M))
     * Space: O(K * M)
     * */
    public int kthSmallest(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;
        int ans = 0;
        Queue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        Set<Long> seen = new HashSet<>();

        int[] first = new int[m + 1];
        for(int i = 0; i < m; i++) {
            first[0] += mat[i][0];
        }
        minHeap.offer(first);

        while(k != 0) {
            int[] curr = minHeap.poll();
            ans = curr[0];

            for(int i = 0; i < m; i++) {
                int[] next = Arrays.copyOf(curr, m + 1);

                if(next[i + 1] == n - 1) {
                    continue;
                }

                next[0] = next[0] - mat[i][next[i + 1]] + mat[i][next[i + 1] + 1];
                next[i + 1]++;

                long hash = 0;
                for(int x : next) {
                    hash = hash * 10 + x;  // hash collision
                }

                if(seen.add(hash)) {
                    minHeap.offer(next);
                }
            }

            k--;
        }

        return ans;
    }
}
