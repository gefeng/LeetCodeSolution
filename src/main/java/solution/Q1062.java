package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashSet;
import java.util.Set;

@Problem(
        title = "Longest Repeating Substring",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ROLLING_HASH,
        url = "https://leetcode.com/problems/longest-repeating-substring/"
)
public class Q1062 {
    private static final long BASE = 26;
    private static final long MOD = (long)1e9 + 7;
    public int longestRepeatingSubstring(String s) {
        int n = s.length();
        int lo = 1;
        int hi = n - 1;
        int ans = 0;
        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if(hasRepeat(s, mid)) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return ans;
    }

    private boolean hasRepeat(String s, int len) {
        int n = s.length();
        Set<Long> hs = new HashSet<>();
        long hash = 0;
        long d = 1;
        for(int l = 0, r = 0; r < n; r++) {
            hash = (hash * BASE % MOD + s.charAt(r) - 'a') % MOD;

            if(r - l + 1 > len) {
                hash = (MOD + hash - (s.charAt(l++) - 'a') * d % MOD) % MOD;
            } else {
                d = d * BASE % MOD;
            }

            if(r - l + 1 == len && hs.contains(hash)) {
                return true;
            }
            hs.add(hash);
        }

        return false;
    }
}
