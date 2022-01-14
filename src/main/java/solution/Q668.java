package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Kth Smallest Number in Multiplication Table",
        difficulty = QDifficulty.HARD,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/kth-smallest-number-in-multiplication-table/"
)
public class Q668 {
    /**
     * Time:  O(M * log(M * N))
     * Space: O(1)
     * */
    int m, n, k;
    public int findKthNumber(int m, int n, int k) {
        this.m = m;
        this.n = n;
        this.k = k;
        int lo = 1;
        int hi = (int)1e9;
        int ans = 1;

        while(lo <= hi) {
            int mid = lo + hi >> 1;

            int cnt = lessEqual(mid);

            if(cnt >= k) {
                ans = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return ans;
    }

    private int lessEqual(int t) {
        int tot = 0;
        for(int i = 1; i <= m; i++) {
            tot += Math.min(t / i, n);
        }

        return tot;
    }
}
