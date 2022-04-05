package solution.weekly287;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Maximum Candies Allocated to K Children",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/contest/weekly-contest-287/problems/maximum-candies-allocated-to-k-children/"
)
public class Q2226 {
    /**
     * Time:  O(N * logN)
     * Space: O(1)
     * */
    public int maximumCandies(int[] candies, long k) {
        int n = candies.length;
        int ans = 0;

        int lo = 1;
        int hi = (int)1e7;

        while(lo <= hi) {
            int mid = lo + hi >> 1;

            long sum = 0;
            for(int i = 0; i < n; i++) {
                sum += (long)(candies[i] / mid) * mid;
            }

            if(sum >= k * mid) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return ans;
    }
}
