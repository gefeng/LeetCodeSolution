package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Minimum Number of Removals to Make Mountain Array",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/minimum-number-of-removals-to-make-mountain-array/"
)
public class Q1671 {

    public int minimumMountainRemovals(int[] nums) {
        return binarySearchLisSol(nums);
    }

    /**
     * Time:  O(N^2)
     * Space: O(N)
     * */
    private int linearScanLisSol(int[] nums) {
        int n = nums.length;
        int[] lisL = new int[n];
        int[] lisR = new int[n];

        for(int i = 0; i < n; i++) {
            lisL[i] = 1;
            for(int j = 0; j < i; j++) {
                if(nums[i] > nums[j]) {
                    lisL[i] = Math.max(lisL[i], lisL[j] + 1);
                }
            }
        }

        for(int i = n - 1; i >= 0; i--) {
            lisR[i] = 1;
            for(int j = n - 1; j > i; j--) {
                if(nums[i] > nums[j]) {
                    lisR[i] = Math.max(lisR[i], lisR[j] + 1);
                }
            }
        }

        int min = n - 3;
        for(int i = 0; i < n; i++) {
            if(lisL[i] != 1 && lisR[i] != 1) {
                min = Math.min(min, n - (lisL[i] + lisR[i] - 1));
            }
        }

        return min;
    }

    /**
     * Time:  O(NlogN)
     * Space: O(N)
     * */
    private int binarySearchLisSol(int[] nums) {
        int n = nums.length;
        int[] dp1 = new int[n];
        int[] dp2 = new int[n];
        int[] lis = new int[n];

        int len = 0;
        for(int i = 0; i < n; i++) {
            int idx = Arrays.binarySearch(lis, 0, len, nums[i]);
            idx = idx >= 0 ? idx : -idx - 1;
            len = idx == len ? len + 1 : len;

            lis[idx] = nums[i];
            dp1[i] = idx + 1;
        }

        lis = new int[n];
        len = 0;
        for(int i = n - 1; i >= 0; i--) {
            int idx = Arrays.binarySearch(lis, 0, len, nums[i]);
            idx = idx >= 0 ? idx : -idx - 1;
            len = idx == len ? len + 1 : len;

            lis[idx] = nums[i];
            dp2[i] = idx + 1;
        }

        int min = n - 3;
        for(int i = 0; i < n; i++) {
            if(dp1[i] != 1 && dp2[i] != 1) {
                min = Math.min(min, n - (dp1[i] + dp2[i] - 1));
            }
        }

        return min;
    }
}
