package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Number of Sub-arrays of Size K and Average Greater than or Equal to Threshold",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.SLIDING_WINDOW,
        url = "https://leetcode.com/problems/number-of-sub-arrays-of-size-k-and-average-greater-than-or-equal-to-threshold/"
)
public class Q1343 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int numOfSubarrays(int[] arr, int k, int threshold) {
        int ans = 0;
        int sum = 0;
        int n = arr.length;

        for(int l = 0, r = 0; r < n; r++) {
            sum += arr[r];

            if(r - l + 1 > k) {
                sum -= arr[l++];
            }

            if(r - l + 1 == k) {
                ans += (sum >= threshold * k) ? 1 : 0;
            }
        }

        return ans;
    }
}
