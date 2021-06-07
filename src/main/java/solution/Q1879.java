package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimum XOR Sum of Two Arrays",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/minimum-xor-sum-of-two-arrays/"
)
public class Q1879 {
    /*
        state:
            dp[state][i] means minimum xor sum by using elements in nums2 denoted by state and first i elements in nums1
        transition
            dp[state][i] = min(dp[state - (1 << j)][i - 1] + nums1[i - 1] ^ nums2[j]) for each bit j == 1

        actually no need to memo i because state mask already indicate how many elements should be used.
        i.e. 101 means we pick 2 from nums1 and nums2

        state:
            dp[state] means minimum xor sum by using i = cntOneBits(state) elements from nums1 and nums2
        transition:
            dp[state] = min(dp[state ^ (1 << j)] + (nums1[i - 1] ^ nums2[j])) for each bit j == 1
    */
    public int minimumXORSum(int[] nums1, int[] nums2) {
        return dpOptimized(nums1, nums2);
    }

    private int dpSolution(int[] nums1, int[] nums2) {
        int n = nums1.length;

        int[][] dp = new int[1 << n][n + 1];

        for(int mask = 1; mask < (1 << n); mask++) {
            int i = cntBits(mask, n);

            int minSum = Integer.MAX_VALUE;
            for(int j = 0; j < n; j++) {
                if(((1 << j) & mask) == 0) {
                    continue;
                }

                minSum = Math.min(minSum, dp[mask - (1 << j)][i - 1] + (nums1[i - 1] ^ nums2[j]));
            }
            dp[mask][i] = minSum;
        }

        return dp[(1 << n) - 1][n];
    }

    private int dpOptimized(int[] nums1, int[] nums2) {
        int n = nums1.length;

        int[] dp = new int[1 << n];

        for(int mask = 1; mask < (1 << n); mask++) {
            int i = cntBits(mask, n);

            int minSum = Integer.MAX_VALUE;
            for(int j = 0; j < n; j++) {

                if(((1 << j) & mask) == 0) {
                    continue;
                }

                minSum = Math.min(minSum, dp[mask ^ (1 << j)] + (nums1[i - 1] ^ nums2[j]));
            }
            dp[mask] = minSum;
        }

        return dp[(1 << n) - 1];
    }

    private int cntBits(int mask, int n) {
        int cnt = 0;
        for(int i = 0; i < n; i++) {
            if((mask & (1 << i)) != 0) {
                cnt++;
            }
        }
        return cnt;
    }
}
