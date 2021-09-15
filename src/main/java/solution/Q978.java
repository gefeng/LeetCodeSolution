package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Longest Turbulent Subarray",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/longest-turbulent-subarray/"
)
public class Q978 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int maxTurbulenceSize(int[] arr) {
        int n = arr.length;
        int ans = 1;
        int dp0 = 1;
        int dp1 = 1;

        for(int i = 1; i < n; i++) {
            if(arr[i] > arr[i - 1]) {
                dp0 = dp1 + 1;
                dp1 = 1;
            } else if(arr[i] < arr[i - 1]) {
                dp1 = dp0 + 1;
                dp0 = 1;
            } else {
                dp0 = 1;
                dp1 = 1;
            }

            ans = Math.max(ans, Math.max(dp0, dp1));
        }

        return ans;
    }
}
