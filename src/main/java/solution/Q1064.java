package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Fixed Point",
        difficulty = QDifficulty.EASY,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/fixed-point/"
)
public class Q1064 {
    /**
     * Time:  O(logN)
     * Space: O(1)
     * */
    public int fixedPoint(int[] arr) {
        int lo = 0;
        int hi = arr.length - 1;
        int ans = -1;

        while(lo <= hi) {
            int mid = lo + hi >> 1;

            if(arr[mid] == mid) {
                ans = mid;
                hi = mid - 1;
            } else if(arr[mid] > mid) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return ans;
    }
}
