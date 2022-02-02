package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashSet;
import java.util.Set;

@Problem(
        title = "Number of Distinct Substrings in a String",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ROLLING_HASH,
        url = "https://leetcode.com/problems/number-of-distinct-substrings-in-a-string/"
)
public class Q1698 {
    /**
     * Time:  O(N ^ 2)
     * Space: O(N)
     * */
    public int countDistinct(String s) {
        int ans = 0;
        int n = s.length();
        long base = 26L, mod = (long)1e13 + 7;

        for(int k = 1; k <= n; k++) {
            long hash = 0L;
            long d = 1L;
            Set<Long> seen = new HashSet<>();

            for(int l = 0, r = 0; r < n; r++) {
                hash = (hash * base % mod + s.charAt(r) - 'a') % mod;

                if(r - l + 1 > k) {
                    hash = (mod + hash - d * (s.charAt(l++) - 'a') % mod) % mod;
                } else {
                    d = d * base % mod;
                }

                if(r - l + 1 == k) {
                    seen.add(hash);
                }
            }

            ans += seen.size();
        }

        return ans;
    }
}
