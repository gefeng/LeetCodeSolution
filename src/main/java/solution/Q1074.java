package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.Map;

@Problem(
        title = "Number of Submatrices That Sum to Target",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/number-of-submatrices-that-sum-to-target/"
)
public class Q1074 {
    /**
     * For each column pair (i, j) we do subarray sum equals to target.
     *
     * Time:  O(N ^ 2 * M)
     * Space: O(M * N)
     * */
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int ans = 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] preSum = new int[m + 1][n + 1];

        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                preSum[i][j] = preSum[i - 1][j] + preSum[i][j - 1] - preSum[i - 1][j - 1] + matrix[i - 1][j - 1];
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = i; j < n; j++) {
                Map<Integer, Integer> map = new HashMap<>();
                map.put(0, 1);
                for(int k = 0; k < m; k++) {
                    int sum = preSum[k + 1][j + 1] - preSum[k + 1][i];

                    ans += map.getOrDefault(sum - target, 0);

                    map.put(sum, map.getOrDefault(sum, 0) + 1);
                }
            }
        }

        return ans;
    }
}
