package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.PriorityQueue;
import java.util.Queue;

@Problem(
        title = "Find Kth Largest XOR Coordinate Value",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/find-kth-largest-xor-coordinate-value/"
)
public class Q1738 {
    public int kthLargestValue(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;

        int[][] dp = new int[m][n];

        Queue<Integer> minHeap = new PriorityQueue<>();

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(i == 0 && j == 0) {
                    dp[i][j] = matrix[i][j];
                } else if(i == 0) {
                    dp[i][j] = (dp[i][j - 1] ^ matrix[i][j]);
                } else if(j == 0) {
                    dp[i][j] = (dp[i - 1][j] ^ matrix[i][j]);
                } else {
                    dp[i][j] = (dp[i - 1][j] ^ dp[i][j - 1] ^ dp[i - 1][j - 1] ^ matrix[i][j]);
                }

                minHeap.offer(dp[i][j]);
                if(minHeap.size() > k) {
                    minHeap.poll();
                }
            }
        }

        return minHeap.peek();
    }
}
