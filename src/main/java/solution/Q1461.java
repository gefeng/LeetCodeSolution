package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashSet;
import java.util.Set;

@Problem(
        title = "Check If a String Contains All Binary Codes of Size K",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ROLLING_HASH,
        url = "https://leetcode.com/problems/check-if-a-string-contains-all-binary-codes-of-size-k/"
)
public class Q1461 {
    /**
     * Hash the window of size k and check every bit mask in [0, 1 << k)
     *
     * Time:  O(max(N, 1 << k))
     * Space: O(max(N, 1 << k))
     * */
    public boolean hasAllCodes(String s, int k) {
        int n = s.length();
        Set<Integer> seen = new HashSet<>();

        int base = 1;
        int hash = 0;
        for(int l = 0, r = 0; r < n; r++) {
            hash += base * (s.charAt(r) - '0');

            if(r - l + 1 <= k) {
                base = base * 2;
            } else if(r - l + 1 > k) {
                hash >>= 1;
                l++;
            }

            if(r - l + 1 == k) {
                seen.add(hash);
            }
        }

        for(int i = 0; i < (1 << k); i++) {
            if(!seen.contains(i)) {
                return false;
            }
        }

        return true;
    }
}
