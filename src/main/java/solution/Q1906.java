package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimum Absolute Difference Queries",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/minimum-absolute-difference-queries/"
)
public class Q1906 {
    /*
        since 1 <= nums[i] <= 100
        we save frequency lookup table for range 0 to i where i < n
        we can do existing lookup for a range in O(100) time
    */
    public int[] minDifference(int[] nums, int[][] queries) {
        int m = nums.length;
        int n = queries.length;
        int[] ans = new int[n];

        int[][] freq = new int[m + 1][101];
        for(int i = 1; i < m + 1; i++) {
            for(int j = 0; j < 101; j++) {
                freq[i][j] = freq[i - 1][j];
            }
            freq[i][nums[i - 1]]++;
        }

        for(int i = 0; i < n; i++) {
            int[] q = queries[i];
            int minGap = Integer.MAX_VALUE;
            int[] l = freq[q[0]];
            int[] r = freq[q[1] + 1];

            int prev = 0;
            for(int j = 0; j < 101; j++) {
                int f = r[j] - l[j];
                if(f == 0) {
                    continue;
                }

                if(prev != 0) {
                    minGap = Math.min(minGap, j - prev);
                }

                prev = j;
            }

            ans[i] = minGap == Integer.MAX_VALUE ? -1 : minGap;
        }

        return ans;
    }
}
