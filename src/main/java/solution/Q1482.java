package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimum Number of Days to Make m Bouquets",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/minimum-number-of-days-to-make-m-bouquets/"
)
public class Q1482 {
    /**
     * Time:  O(N * log(max(bloomDay)))
     * Space: O(N)
     * */
    public int minDays(int[] bloomDay, int m, int k) {
        int ans = -1;
        int n = bloomDay.length;

        int lo = 1;
        int hi = (int)1e9;
        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if(isOk(bloomDay, m, k, mid)) {
                ans = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return ans;
    }


    private boolean isOk(int[] a, int m, int k, int d) {
        int n = a.length;

        int[] b = new int[n];
        int cnt = 0;
        for(int i = 0; i < n; i++) {
            b[i] = a[i] <= d ? 1 : 0;
            if(b[i] == 1) {
                cnt++;
            } else {
                cnt = 0;
            }

            if(cnt == k) {
                m--;
                cnt = 0;
            }
        }

        return m <= 0;
    }
}
