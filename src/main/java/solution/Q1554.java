package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashSet;
import java.util.Set;

@Problem(
        title = "Strings Differ by One Character",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/strings-differ-by-one-character/"
)
public class Q1554 {
    /**
     * Time:  O(M * N)
     * Space: O(M * N)
     * */
    private static final long MOD = (long)1e13 + 7;
    private static final long BASE = 27;
    public boolean differByOne(String[] dict) {
        Set<Long> set = new HashSet<>();

        for(String s : dict) {
            int n = s.length();
            long hash = 0;
            long d = 1;

            for(int i = 0; i < n; i++) {
                long c = s.charAt(i) - 'a';
                hash = (hash + c * d % MOD) % MOD;
                d = (d * BASE) % MOD;
            }

            d = 1;
            for(int i = 0; i < n; i++) {
                long c = s.charAt(i) - 'a';
                long nhash = ((hash - c * d % MOD + MOD) % MOD + 26 * d % MOD) % MOD;
                if(set.contains(nhash)) return true;
                set.add(nhash);
                d = (d * BASE) % MOD;
            }
        }

        return false;
    }
}
