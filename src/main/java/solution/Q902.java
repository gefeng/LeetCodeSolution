package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Problem(
        title = "Numbers At Most N Given Digit Set",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/numbers-at-most-n-given-digit-set/"
)
public class Q902 {
    /**
     * Time:  O(logN) (base10)
     * Space: O(logN)
     * */
    public int atMostNGivenDigitSet(String[] digits, int n) {
        int ans = 0;
        int m = digits.length;

        char[] c = Integer.toString(n).toCharArray();

        // count numbers with the same number of digits of n
        for(int i = 0; i < c.length; i++) {
            int k = -1;
            for(int j = 0; j < m; j++) {
                if(digits[j].charAt(0) == c[i]) {
                    k = j;
                    break;
                }
                if(digits[j].charAt(0) < c[i]) {
                    ans += Math.pow(m, c.length - i - 1);
                }
            }

            if(k == -1) {
                break;
            }

            // x == n
            if(i == c.length - 1) {
                ans++;
            }
        }

        // count numbers with less digits than n
        for(int i = 1; i < c.length; i++) {
            ans += Math.pow(m, i);
        }

        return ans;
    }
}
