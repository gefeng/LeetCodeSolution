package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Maximum Sum Obtained of Any Permutation",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.SORT,
        url = "https://leetcode.com/problems/maximum-sum-obtained-of-any-permutation/"
)
public class Q1589 {
    /**
     * Time:  O(N * logN)
     * Space: O(N)
     * */
    private static final int MOD = (int)1e9 + 7;
    public int maxSumRangeQuery(int[] nums, int[][] requests) {
        long res = 0;
        int n = nums.length;
        int[] cntIdx = new int[n];

        for (int[] req : requests) {
            cntIdx[req[0]]++;
            if (req[1] + 1 < n) {
                cntIdx[req[1] + 1]--;
            }
        }

        for (int i = 1; i < n; i++) {
            cntIdx[i] += cntIdx[i - 1];
        }

        Arrays.sort(nums);
        Arrays.sort(cntIdx);

        for (int i = n - 1; i >= 0; i--) {
            if (cntIdx[i] == 0) {
                break;
            }
            res = (res + (long) nums[i] * cntIdx[i] % MOD) % MOD;
        }

        return (int) res;
    }
}
