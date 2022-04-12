package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Cutting Ribbons",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/cutting-ribbons/"
)
public class Q1891 {
    /**
     * Time:  O(N * logN)
     * Space: O(1)
     * */
    public int maxLength(int[] ribbons, int k) {
        int lo = 1;
        int hi = (int)1e5;
        int ans = 0;

        while(lo <= hi) {
            int mid = lo + hi >> 1;

            if(canSplit(ribbons, k, mid)) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return ans;
    }

    private boolean canSplit(int[] ribbons, int k, int t) {
        int cnt = 0;

        for(int r : ribbons) {
            cnt += r / t;
        }

        return cnt >= k;
    }
}
