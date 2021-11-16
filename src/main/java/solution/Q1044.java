package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashSet;
import java.util.Set;

@Problem(
        title = "Longest Duplicate Substring",
        difficulty = QDifficulty.HARD,
        tag = QTag.ROLLING_HASH,
        url = "https://leetcode.com/problems/longest-duplicate-substring/"
)
public class Q1044 {
    /**
     * Time:  O(N * logN)
     * Space: O(N)
     * */
    /*
    * we should check collision in real life.
    * Modulus won't solve collision eventually.
    * */
    private static final long BASE = 26;
    private static final long MOD = (long)1e12 + 7;
    public String longestDupSubstring(String s) {
        int n = s.length();
        int lo = 1;
        int hi = n - 1;
        String ans = "";

        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            Set<Long> hs = new HashSet<>();
            long hash = 0;
            long d = 1;
            String candidate = "";
            for(int l = 0, r = 0; r < n; r++) {
                hash = ((hash * BASE) % MOD + s.charAt(r) - 'a') % MOD;

                if(r - l + 1 > mid) {
                    hash = (MOD + hash - (s.charAt(l++) - 'a') * d % MOD) % MOD;
                } else {
                    d = d * BASE % MOD;
                }

                if(r - l + 1 == mid) {
                    if(hs.contains(hash)) {
                        candidate = s.substring(l, r + 1);
                    } else {
                        hs.add(hash);
                    }
                }
            }

            if(!candidate.isEmpty()) {
                ans = candidate;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return ans;
    }
}
