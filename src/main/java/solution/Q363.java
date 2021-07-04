package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.TreeSet;

@Problem(
        title = "Max Sum of Rectangle No Larger Than K",
        difficulty = QDifficulty.HARD,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/max-sum-of-rectangle-no-larger-than-k/"
)
public class Q363 {
    /*
    * currSum - prevSum <= k -> prevSum >= currSum - k (looking for ceiling)
    * */
    public int maxSumSubmatrix(int[][] matrix, int k) {
        return slidingWindowOnRow(matrix, k);
    }

    private int slidingWindowOnRow(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        int max = Integer.MIN_VALUE;

        int[] col = new int[n];
        for(int l = 0; l < m; l++) {
            Arrays.fill(col, 0);
            for(int r = l; r < m; r++) {
                for(int i = 0; i < n; i++) {
                    col[i] += matrix[r][i];
                }

                TreeSet<Integer> seen = new TreeSet<>();
                seen.add(0);
                int sum = 0;
                for(int num : col) {
                    sum += num;
                    Integer ceiling = seen.ceiling(sum - k);
                    if(ceiling != null) {
                        max = Math.max(max, sum - ceiling);
                    }
                    seen.add(sum);
                }

                if(max == k) {
                    return max;
                }
            }
        }

        return max;
    }
}
