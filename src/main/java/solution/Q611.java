package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Valid Triangle Number",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TWO_POINTERS,
        url = "https://leetcode.com/problems/valid-triangle-number/"
)
public class Q611 {
    public int triangleNumber(int[] nums) {
        return linearScanSol(nums);
    }

    private int linearScanSol(int[] nums) {
        int n = nums.length;
        int cnt = 0;
        Arrays.sort(nums);

        for(int i = 0; i < n - 2; i++) {
            if(nums[i] == 0) {
                continue;
            }
            for(int j = i + 1, k = j + 1; j < n - 1; j++) {
                while(k < n && nums[i] + nums[j] > nums[k]) {
                    k++;
                }
                cnt += (k - j - 1);
            }
        }
        return cnt;
    }

    private int binarySearchSol(int[] nums) {
        int cnt = 0;
        int n = nums.length;

        Arrays.sort(nums);

        int k = 2;
        for(int i = 0; i < n - 2; i++) {
            if(nums[i] == 0) {
                continue;
            }
            for(int j = i + 1; j < n - 1; j++) {
                k = binarySearch(nums, j + 1, n - 1, nums[i] + nums[j]);
                if(k != -1) {
                    cnt += (k - j);
                }
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
            if(nums[mid] < target) {
                lo = mid + 1;
                ans = mid;
            } else {
                hi = mid - 1;
            }
        }

        return ans;
    }
}
