package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimized Maximum of Products Distributed to Any Store",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/minimized-maximum-of-products-distributed-to-any-store/"
)
public class Q2064 {
    /**
     * Time:  O(M * log(max(q)))
     * Space: O(1)
     * */
    public int minimizedMaximum(int n, int[] quantities) {
        int m = quantities.length;
        int max = 0;

        for(int q : quantities) {
            max = Math.max(max, q);
        }

        int lo = 1;
        int hi = max;
        int ans = 0;
        while(lo <= hi) {
            int mid = lo + hi >> 1;

            int cnt = 0;
            for(int q : quantities) {
                cnt += (q + mid - 1) / mid;
            }

            if(cnt <= n) {
                ans = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return ans;
    }
}
