package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Range Sum of Sorted Subarray Sums",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.SORT,
        url = "https://leetcode.com/problems/range-sum-of-sorted-subarray-sums/"
)
public class Q1508 {
    /**
     * Time:  O(N ^ 2 * log(N ^ 2))
     * Space: O(N ^ 2)
     * */
    private static final int MOD = (int)1e9 + 7;
    public int rangeSum(int[] nums, int n, int left, int right) {
        long[] sum = new long[n * (n + 1) / 2];

        for(int i = 0, k = 0; i < n; i++) {
            sum[k++] = nums[i];
            long x = nums[i];
            for(int j = i + 1; j < n; j++) {
                x += nums[j];
                sum[k++] = x;
            }
        }

        Arrays.sort(sum);

        long res = 0;
        for(int i = left - 1; i < right; i++) {
            res += sum[i];
        }

        return (int)(res % MOD);
    }
}
