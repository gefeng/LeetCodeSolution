package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashSet;
import java.util.Set;

@Problem(
        title = "Unique Substrings With Equal Digit Frequency",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ROLLING_HASH,
        url = "https://leetcode.com/problems/unique-substrings-with-equal-digit-frequency/"
)
public class Q2168 {
    /**
     * Time:  O(N ^ 2)
     * Space: O(N)
     * */
    public int equalDigitFrequency(String s) {
        int n = s.length();
        int ans = 0;

        for(int len = 1; len <= n; len++) {
            int[] cnt = new int[10];
            Set<Long> seen = new HashSet<>();
            long base = 10;
            long d = 1;
            long hash = 0;
            long mod = (long)1e13 + 7;
            for(int l = 0, r = 0; r < n; r++) {
                int c = s.charAt(r) - '0';

                cnt[c]++;
                hash = (hash * base % mod + c) % mod;

                if(r - l + 1 > len) {
                    int cc = s.charAt(l++) - '0';
                    hash = (hash - d * cc % mod + mod) % mod;
                    cnt[cc]--;
                } else {
                    d = d * base % mod;
                }

                if(r - l + 1 == len) {
                    int freq = -1;
                    boolean isOk = true;
                    for(int i = 0; i < 10; i++) {
                        if(cnt[i] > 0) {
                            if(freq == -1) freq = cnt[i];
                            else {
                                if(freq != cnt[i]) {
                                    isOk = false;
                                    break;
                                }
                            }
                        }
                    }

                    if(isOk && !seen.contains(hash)) {
                        seen.add(hash);
                        ans++;
                    }
                }
            }
        }

        return ans;
    }
}
