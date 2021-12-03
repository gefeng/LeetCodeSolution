package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Super Palindromes",
        difficulty = QDifficulty.HARD,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/super-palindromes/"
)
public class Q906 {
    /**
     * Generate all the palindrome number which has at most 9 digits.
     * Increase counter if a palindrome number's square is palindrome and it is within range.
     *
     * Time:  O(N) where N is the total number of palindrome number with [1..9] digits.
     * Space: O(N)
     * */
    int ans;
    long lo;
    long hi;
    public int superpalindromesInRange(String left, String right) {
        lo = Long.parseLong(left);
        hi = Long.parseLong(right);
        ans = 0;

        for(int i = 1; i < 10; i++) {
            dfs(i, 0, 0, 0, 1L);
        }

        return ans;
    }

    private void dfs(int n, int len, long a, long b, long d) {
        if(len == n / 2) {
            if(n % 2 == 0) {
                long x = a * d + b;
                if(x * x >= lo && x * x <= hi && isPal(x * x)) {
                    ans++;
                }
            } else {
                for(int i = 0; i < 10; i++) {
                    long x = (a * 10 + i) * d + b;
                    if(x * x >= lo && x * x <= hi && isPal(x * x)) {
                        ans++;
                    }
                }
            }
            return;
        }

        int st = len == 0 ? 1 : 0;
        for(int i = st; i < 10; i++) {
            dfs(n, len + 1, a * 10 + i, i * d + b, d * 10);
        }
    }

    private boolean isPal(long x) {
        String s = Long.toString(x);
        for(int i = 0, j = s.length() - 1; i < j; i++, j--) {
            if(s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }
}
