package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Find Substring With Given Hash Value",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ROLLING_HASH,
        url = "https://leetcode.com/problems/find-substring-with-given-hash-value/"
)
public class Q2156 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public String subStrHash(String s, int power, int modulo, int k, int hashValue) {
        int n = s.length();
        String ans = "";
        long hash = 0;
        long d = 1;
        long mod = modulo;
        long base = power;
        for(int l = n - 1, r = n - 1; r >= 0; r--) {
            int c = s.charAt(r) - 'a' + 1;
            hash = (hash * base + c) % mod;
            if(l - r + 1 > k) {
                hash = (mod + hash - (s.charAt(l--) - 'a' + 1) * d % mod) % mod;
            } else {
                d = d * base % mod;
            }

            if(l - r + 1 == k) {
                if(hash == hashValue) {
                    ans = s.substring(r, l + 1);
                }
            }
        }

        return ans;
    }
}
