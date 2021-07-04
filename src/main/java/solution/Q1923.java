package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashSet;
import java.util.Set;

@Problem(
        title = "Longest Common Subpath",
        difficulty = QDifficulty.HARD,
        tag = QTag.ROLLING_HASH,
        url = "https://leetcode.com/problems/longest-common-subpath/"
)
public class Q1923 {
    /*
    * Rolling Hash a.k.a Rabin Karp algorithm
    * */
    private static final long BASE = 100001;
    private static final long MOD = (long)1e12 + 7;
    public int longestCommonSubpath(int n, int[][] paths) {
        int m = paths.length;
        int lo = 1;
        int hi = getMinPath(paths);
        int ans = 0;
        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if(isOk(paths, mid)) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return ans;
    }

    private int getMinPath(int[][] paths) {
        int min = Integer.MAX_VALUE;
        for(int[] path : paths) {
            min = Math.min(min, path.length);
        }
        return min;
    }

    // 0 1 2 3 4  len = 3   0 1 2 -> 1 2 3
    private boolean isOk(int[][] paths, int len) {
        Set<Long> currSet = new HashSet<>();
        int m = paths.length;

        for(int i = 0; i < m; i++) {
            int[] path = paths[i];
            long hash = 0;
            long d = 1;
            Set<Long> nextSet = new HashSet<>();
            for(int l = 0, r = 0; r < path.length; r++) {
                hash = ((hash * BASE) % MOD + path[r]) % MOD;

                if(r - l + 1 > len) {
                    hash = (MOD + hash - (path[l++] * d) % MOD) % MOD;
                } else {
                    d = d * BASE % MOD;
                }

                if(r - l + 1 == len && (i == 0 || currSet.contains(hash))) {
                    nextSet.add(hash);
                }
            }

            if(nextSet.size() == 0) {
                return false;
            }

            currSet = nextSet;
        }

        return true;
    }
}
