package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashSet;
import java.util.Set;

@Problem(
        title = "Distinct Echo Substrings",
        difficulty = QDifficulty.HARD,
        tag = QTag.ROLLING_HASH,
        url = "https://leetcode.com/problems/distinct-echo-substrings/"
)
public class Q1316 {
    /**
     * Time:  O(N ^ 2)
     * Space: O(N ^ 2)
     * */
    private static final long MOD = (long)1e9 + 1L;
    private static final long BASE = 26L;
    public int distinctEchoSubstrings(String text) {
        int n = text.length();
        long[] hash = new long[n + 1];
        long[] pow = new long[n + 1];
        pow[0] = 1;

        for(int i = 1; i <= n; i++) {
            hash[i] = (hash[i - 1] * BASE % MOD + text.charAt(i - 1) - 'a' + 1) % MOD;
            pow[i] = pow[i - 1] * BASE % MOD;
        }

        Set<Long> cand = new HashSet<>();
        for(int i = 0; i < n; i++) {
            for(int sz = 2; i + sz <= n; sz += 2) {
                int mid = i + sz / 2;
                long h1 = getHash(hash, pow, i, mid);
                long h2 = getHash(hash, pow, mid, i + sz);
                if(h1 == h2) {
                    cand.add(h1);
                }
            }
        }

        return cand.size();
    }

    private long getHash(long[] hash, long[] pow, int l, int r) {
        return (hash[r] - hash[l] * pow[r - l] % MOD + MOD) % MOD;
    }
}
