package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "3Sum Smaller",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TWO_POINTERS,
        url = "https://leetcode.com/problems/3sum-smaller/"
)
public class Q259 {
    public int threeSumSmaller(int[] nums, int target) {
        return linearScanSol(nums, target);
    }

    private int linearScanSol(int[] nums, int target) {
        int n = nums.length;
        int cnt = 0;

        for(int i = 0; i < n - 2; i++) {
            for(int j = n - 1, k = i + 1; j > k; j--) {
                while(nums[i] + nums[j] + nums[k] < target) {
                    k++;
                }
                cnt += (k - i - 1);
            }
        }

        return cnt;
    }

    private int binarySearchSol(int[] nums, int target) {
        int n = nums.length;
        int cnt = 0;

        Arrays.sort(nums);

        for(int i = 0; i < n - 2; i++) {
            for(int j = i + 1; j < n - 1; j++) {
                int k = binarySearch(nums, j + 1, n - 1, target - nums[i] - nums[j]);
                k = k == -1 ? n : k;
                cnt += (k - j - 1);
            }
        }

        return cnt;
    }

    private int binarySearch(int[] nums, int start, int end, int target) {
        int lo = start;
        int hi = end;
        int ans = -1;
        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if(nums[mid] >= target) {
                ans = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return ans;
    }
}
