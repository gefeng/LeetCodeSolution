package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Peak Index in a Mountain Array",
        difficulty = QDifficulty.EASY,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/peak-index-in-a-mountain-array/"
)
public class Q852 {
    /**
     * Time:  (logN)
     * Space: O(1)
     * */
    public int peakIndexInMountainArray(int[] arr) {
        int lo = 1;
        int hi = arr.length - 2;
        int peak = 0;

        while(lo <= hi) {
            int mid = lo + hi >> 1;

            if(arr[mid] > arr[mid - 1]) {
                peak = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return peak;
    }
}
