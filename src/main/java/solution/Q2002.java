package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Maximum Product of the Length of Two Palindromic Subsequences",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/maximum-product-of-the-length-of-two-palindromic-subsequences/"
)
public class Q2002 {
    /**
     * Time:  O(2 ^ N * 2 ^ N * N)
     * Space: O(2 ^ N)
     * */
    public int maxProduct(String s) {
        int n = s.length();
        int ans = 0;
        boolean[] dp = new boolean[1 << n];

        for(int i = 1; i < dp.length; i++) {
            if(isPal(i, s)) {
                dp[i] = true;
            }
        }

        for(int i = 0; i < (1 << n); i++) {
            if(!dp[i]) {
                continue;
            }
            boolean valid = true;

            for(int j = 0; j < (1 << n); j++) {
                if(!dp[j]) {
                    continue;
                }

                if((i & j) == 0) {
                    ans = Math.max(ans, Integer.bitCount(i) * Integer.bitCount(j));
                }
            }
        }

        return ans;
    }

    private boolean isPal(int mask, String s) {
        int l = 0;
        int r = s.length() - 1;
        while(l < r) {
            int bit1 = mask & (1 << l);
            int bit2 = mask & (1 << r);

            if(bit1 != 0 && bit2 != 0) {
                if(s.charAt(l++) != s.charAt(r--)) {
                    return false;
                }
            } else {
                l = bit1 == 0 ? l + 1 : l;
                r = bit2 == 0 ? r - 1 : r;
            }
        }

        return true;
    }
}
