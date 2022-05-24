package solution.biweekly78;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.Comparator;

@Problem(
        title = "Maximum White Tiles Covered by a Carpet",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/contest/biweekly-contest-78/problems/maximum-white-tiles-covered-by-a-carpet/"
)
public class Q2271 {
    /**
     * Time:  O(N * logN)
     * Space: O(N)
     * */
    public int maximumWhiteTiles(int[][] tiles, int carpetLen) {
        int n = tiles.length;
        long ans = 0;

        Arrays.sort(tiles, Comparator.comparingInt(a -> a[0]));

        long[] psum = new long[n + 1];

        for(int i = 1; i <= n; i++) {
            psum[i] = psum[i - 1] + tiles[i - 1][1] - tiles[i - 1][0] + 1;
        }

        for(int i = 0; i < n; i++) {
            int[] t = tiles[i];
            int r = t[0] + carpetLen - 1;
            int idx = bs(tiles, i, r);
            long tot = 0;

            if(tiles[idx][1] <= r) {
                tot = psum[idx + 1] - psum[i];
            } else {
                tot = psum[idx] - psum[i] + r - tiles[idx][0] + 1;
            }

            ans = Math.max(ans, tot);
        }

        return (int)ans;
    }

    private int bs(int[][] a, int st, int t) {
        int lo = st;
        int hi = a.length - 1;
        int idx = st;
        while(lo <= hi) {
            int mid = lo + hi >> 1;
            if(a[mid][0] <= t) {
                idx = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return idx;
    }
}
