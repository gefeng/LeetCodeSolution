package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Ways to split Array Into Three Subarrays",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/ways-to-split-array-into-three-subarrays/"
)
public class Q1712 {
    private static final int MOD = (int)1e9 + 7;
    public int waysToSplit(int[] nums) {
        int n = nums.length;
        int[] prefixSum = new int[n + 1];
        for(int i = 1; i < n + 1; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }

        int cnt = 0;
        for(int l = 1; l <= n - 2; l++) {
            int minr = binarySearch(nums, prefixSum, l + 1, n - 1, true);
            int maxr = binarySearch(nums, prefixSum, l + 1, n - 1, false);
            cnt = minr == -1 ? cnt : (cnt + (maxr - minr + 1)) % MOD;
        }

        return cnt;
    }

    private int binarySearch(int[] nums, int[] prefixSum, int start, int end, boolean min) {
        int n = nums.length;
        int lo = start, hi = end;
        int sumL, sumR, sumM = 0;
        int ans = -1;

        sumL = prefixSum[start - 1];
        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            sumM = prefixSum[mid] - prefixSum[start - 1];
            sumR = prefixSum[n] - prefixSum[mid];

            if(min) {
                if(sumM >= sumL && sumR >= sumM) {
                    ans = mid;
                    hi = mid - 1;
                } else {
                    if(sumM < sumL) {
                        lo = mid + 1;
                    } else {
                        hi = mid - 1;
                    }
                }
            } else {
                if(sumM >= sumL && sumR >= sumM) {
                    ans = mid;
                    lo = mid + 1;
                } else {
                    if(sumM < sumL) {
                        lo = mid + 1;
                    } else {
                        hi = mid - 1;
                    }
                }
            }
        }
        return ans;
    }
}
