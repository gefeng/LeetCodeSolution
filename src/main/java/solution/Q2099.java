package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

@Problem(
        title = "Find Subsequence of Length K With the Largest Sum",
        difficulty = QDifficulty.EASY,
        tag = QTag.SORT,
        url = "https://leetcode.com/problems/find-subsequence-of-length-k-with-the-largest-sum/"
)
public class Q2099 {
    /**
     * Time:  O(N * logN)
     * Space: O(N)
     * */
    public int[] maxSubsequence(int[] nums, int k) {
        int n = nums.length;
        int[][] ans = new int[k][2];

        Queue<int[]> pq = new PriorityQueue<>(Comparator.comparing(a -> a[0], Comparator.reverseOrder()));

        for(int i = 0; i < n; i++) {
            pq.offer(new int[] {nums[i], i});
        }

        int idx = 0;
        int len = k;
        while(len != 0) {
            int[] x = pq.poll();
            ans[idx++] = x;
            len--;
        }

        Arrays.sort(ans, Comparator.comparingInt(a -> a[1]));

        int[] ret = new int[k];
        for(int i = 0; i < k; i++) {
            ret[i] = ans[i][0];
        }

        return ret;
    }
}
