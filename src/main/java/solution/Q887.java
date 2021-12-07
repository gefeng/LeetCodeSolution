package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Supper Egg Drop",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/super-egg-drop/"
)
public class Q887 {
    /**
     * state:
     *  dp(k, n) k eggs left and n floors left maximum number of moves to find out f.
     * transition:
     *  drop from floor x,
     *  if egg survives then => (k, n - x)
     *  if egg breaks then => (k - 1, x - 1)
     *  dp(k, n) = min(max(dp(k, n - x), dp(k - 1, x - 1)) + 1) for each x
     * Iterate over 1 to n for x takes O(N) time leading to a overall O(K * N^2).
     * Since dp(k, n - x) is monotonic decreasing and dp(k - 1, x - 1) is monotonic increasing along with increasing i,
     * we can apply binary search to address x.
     *
     * Time:  O(K * N * logN)
     * Space: O(K * N)
     * */
    public int superEggDrop(int k, int n) {
        return dfs(k, n, new Integer[k + 1][n + 1]);
    }

    private int dfs(int k, int n, Integer[][] memo) {
        if(n == 0) return 0;
        if(k == 1) return n; // test from the bottom and f could be the nth floor

        if(memo[k][n] != null) {
            return memo[k][n];
        }

        int lo = 1;
        int hi = n;
        int best = n;
        while(lo <= hi) {
            int mid = lo + hi >> 1;
            int save = dfs(k, n - mid, memo);
            int lost = dfs(k - 1, mid - 1, memo);
            best = Math.max(save, lost) + 1;
            if(save == lost) {
                break;
            } else if(save > lost) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return memo[k][n] = best;
    }
}
