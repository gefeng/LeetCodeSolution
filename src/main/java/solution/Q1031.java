package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Maximum Sum of Two Non-Overlapping Subarrays",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/maximum-sum-of-two-non-overlapping-subarrays/"
)
public class Q1031 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int maxSumTwoNoOverlap(int[] A, int L, int M) {
        int n = A.length;
        if(n == 0)
            return 0;

        int[] prefixSum = new int[n + 1];

        prefixSum[0] = 0;
        for(int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + A[i - 1];
        }

        return Math.max(findMaxSum(prefixSum, L, M), findMaxSum(prefixSum, M, L));
    }

    private int findMaxSum(int[] prefixSum, int L, int M) {
        int maxL = 0;
        int maxSum = 0;
        for(int i = L + M; i < prefixSum.length; i++) {
            maxL = Math.max(maxL, prefixSum[i - M] - prefixSum[i - M - L]);
            maxSum = Math.max(maxSum, maxL + prefixSum[i] - prefixSum[i - M]);
        }

        return maxSum;
    }
}
