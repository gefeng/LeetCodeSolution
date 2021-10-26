package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Divide Chocolate",
        difficulty = QDifficulty.HARD,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/divide-chocolate/"
)
public class Q1231 {
    /**
     * Time:  O(N * log(max_int))
     * Space: O(1)
     * */
    public int maximizeSweetness(int[] sweetness, int k) {
        int n = sweetness.length;
        int lo = 1;
        int hi = (int)1e9;
        int ans = 0;
        while(lo <= hi) {
            int mid = lo + hi >> 1;

            if(isPossible(sweetness, k, mid)) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return ans;
    }

    private boolean isPossible(int[] s, int k, int t) {
        int n = s.length;
        int cnt = 0;
        int sum = 0;
        for(int i = 0; i < n; i++) {
            sum += s[i];
            if(sum >= t) {
                sum = 0;
                cnt++;
            }
        }

        return cnt >= k + 1;
    }
}
