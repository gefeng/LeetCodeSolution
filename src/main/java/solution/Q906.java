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
    public int superpalindromesInRange(String left, String right) {
        int cnt = 0;
        for(int i = 0; i < 10; i++) {
            cnt += dfs(left, right, Integer.toString(i));
        }

        for(int i = 0; i < 100; i += 11) {
            cnt += dfs(left, right, i == 0 ? "00" : Integer.toString(i));
        }

        return cnt;
    }

    private int dfs(String left, String right, String seed) {
        int cnt = 0;

        // 99999999 r = 7 / 2 = 3
        // 10000

        if(seed.length() * 2 - 1 > right.length()) {
            return 0;
        }
        if(seed.charAt(0) != '0') {
            String sqr = getSqr(seed);

            if(compare(sqr, right) > 0) {
                return 0;
            }

            cnt = compare(sqr, left) >= 0 && isPalindrome(sqr) ? 1 : 0;
        }

        for(char i = '0'; i <= '9' ; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(i).append(seed).append(i);
            cnt += dfs(left, right, sb.toString());
        }

        return cnt;
    }

    private String getSqr(String s) {
        if(s.equals("0") || s.equals("1")) {
            return s;
        }
        int n = s.length();
        int[] d = new int[n];
        int[] dd = new int[2 * n];

        for(int i = 0; i < n; i++) {
            d[i] = s.charAt(i) - '0';
        }

        for(int i = n - 1; i >= 0; i--) {
            for(int j = n - 1; j >= 0; j--) {
                dd[i + j + 1] += d[i] * d[j];
            }
        }

        int carry = 0;
        for(int i = 2 * n - 1; i >= 0; i--) {
            int val = dd[i] + carry;
            dd[i] = val % 10;
            carry = val / 10;
        }

        StringBuilder sb = new StringBuilder();
        int i = 0;
        while(dd[i] == 0) {
            i++;
        }
        while(i != dd.length) {
            sb.append(dd[i++]);
        }

        return sb.toString();
    }

    private boolean isPalindrome(String s) {
        int lo = 0;
        int hi = s.length() - 1;
        while(lo < hi) {
            if(s.charAt(lo++) != s.charAt(hi--)) {
                return false;
            }
        }
        return true;
    }

    private int compare(String x, String y) {
        int m = x.length();
        int n = y.length();
        if(m > n) {
            return 1;
        }
        if(m < n) {
            return -1;
        }

        int i = 0;
        int j = 0;
        while(i < m) {
            char c1 = x.charAt(i);
            char c2 = y.charAt(j);
            if(c1 > c2) {
                return 1;
            }
            if(c1 < c2) {
                return -1;
            }
            i++;
            j++;
        }
        return 0;
    }
}
