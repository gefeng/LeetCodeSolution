package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Number of Subsequences That Satisfy the Given Sum Condition",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TWO_POINTERS,
        url = "https://leetcode.com/problems/number-of-subsequences-that-satisfy-the-given-sum-condition/"
)
public class Q1498 {
    /**
     * Time:  O(N * logN)
     * Space: O(logN)
     * */
    private static final int MOD = (int)1e9 + 7;
    public int numSubseq(int[] nums, int target) {
        int n = nums.length;
        int res = 0;

        Arrays.sort(nums);

        for(int num : nums) {
            res = num * 2 <= target ? res + 1 : res;
        }

        int l = 0;
        int r = n - 1;
        while(l < r) {
            int sum = nums[l] + nums[r];
            if(nums[l] + nums[r] <= target) {
                res = (res + (powMod(2, r - l) - 1 + MOD) % MOD) % MOD;
                l++;
            } else {
                r--;
            }
        }

        return res;
    }

    private int powMod(long x, long y) {
        long res = 1;
        while(y > 0) {
            if(y % 2 == 1) {
                res = (res * x) % MOD;
            }

            x = (x * x) % MOD;
            y = y / 2;
        }

        return (int)(res % MOD);
    }
}
