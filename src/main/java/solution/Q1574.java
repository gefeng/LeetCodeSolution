package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Shortest Subarray to be Removed to Make Array Sorted",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/shortest-subarray-to-be-removed-to-make-array-sorted/"
)
public class Q1574 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int findLengthOfShortestSubarray(int[] arr) {
        int n = arr.length;
        int res = n;
        int l = 0;
        int r = n - 1;

        while(l + 1 < n && arr[l + 1] >= arr[l]) {
            l++;
        }

        if(l == n - 1) {
            return 0;
        }

        res = n - (l + 1);

        while(r - 1 >= 0 && arr[r - 1] <= arr[r]) {
            r--;
        }

        res = Math.min(res, r);

        int i = 0;
        int j = r;
        while(i <= l && j < n) {
            if(arr[i] <= arr[j]) {
                res = Math.min(res, j - 1 - i);
                i++;
            } else {
                j++;
            }
        }

        return  res;
    }

    private int binarySearch(List<Integer> nums, int target) {
        int lo = 0;
        int hi = nums.size() - 1;
        int res = nums.size();
        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if(nums.get(mid) > target) {
                res = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return res;
    }
}
