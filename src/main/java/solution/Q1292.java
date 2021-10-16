package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Maximum Side Length of a Square with Sum Less than or Equal to Threshold",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/maximum-side-length-of-a-square-with-sum-less-than-or-equal-to-threshold/"
)
public class Q1292 {
    /**
     * Time:  O(M * N * log(min(M, N)))
     * Space: O(M * N)
     * */
    public int maxSideLength(int[][] mat, int threshold) {
        int ans = 0;
        int m = mat.length;
        int n = mat[0].length;
        int[][] pre = new int[m + 1][n + 1];

        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                pre[i][j] = pre[i - 1][j] + pre[i][j - 1] - pre[i - 1][j - 1] + mat[i - 1][j - 1];
            }
        }

        int lo = 1;
        int hi = Math.min(m, n);

        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            boolean isOk = false;
            for(int i = 0; i + mid < m; i++) {
                for(int j = 0; j + mid < n; j++) {
                    int sum = pre[i + mid + 1][j + mid + 1] - pre[i + mid + 1][j] - pre[i][j + mid + 1] + pre[i][j];

                    if(sum <= threshold) {
                        isOk = true;
                        break;
                    }
                }
            }

            if(isOk) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return ans == 0 ? 0 : ans + 1;
    }
}
