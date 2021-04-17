package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.PriorityQueue;
import java.util.Queue;

@Problem(
        title = "Kth Smallest Element in a Sorted Matrix",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/"
)
public class Q378 {
    public int kthSmallest(int[][] matrix, int k) {
        return binarySearchSolution(matrix, k);
    }

    private int heapSolution(int[][] matrix, int k) {
        int ans = 0;
        int n = matrix.length;
        Queue<int[]> pQueue = new PriorityQueue<>((a, b) -> (a[2] - b[2]));

        for(int i = 0; i < n; i++) {
            pQueue.offer(new int[] {i, 0, matrix[i][0]});
        }

        for(int i = 0; i < k; i++) {
            int[] smallest = pQueue.poll();
            ans = smallest[2];
            if(smallest[1] < n - 1) {
                smallest[1]++;
                smallest[2] = matrix[smallest[0]][smallest[1]];
                pQueue.offer(smallest);
            }
        }

        return ans;
    }

    private int binarySearchSolution(int[][] matrix, int k) {
        int ans = 0;
        int n = matrix.length;
        int lo = matrix[0][0];
        int hi = matrix[n - 1][n - 1];
        int mid = 0;
        while(lo <= hi) {
            mid = lo + (hi - lo) / 2;

            int[] res = countSmaller(matrix, mid);

            if(k == res[0]) {
                ans = res[1];
                break;
            } else if(k < res[0]) {
                ans = res[1];
                hi = mid - 1;
            } else if(k > res[0]) {
                lo = mid + 1;
            }
        }

        return ans;
    }

    private int[] countSmaller(int[][] matrix, int num) {
        int count = 0;
        int max = Integer.MIN_VALUE;
        int n = matrix.length;
        int r = n - 1;
        int c = 0;

        while(r >= 0 && c < n) {
            if(matrix[r][c] <= num) {
                count += (r + 1);
                max = Math.max(max, matrix[r][c]);
                c++;
            } else {
                r--;
            }
        }

        return new int[] {count, max};
    }
}
