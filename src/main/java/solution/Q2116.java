package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Check if a Parentheses String Can Be Valid",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/check-if-a-parentheses-string-can-be-valid/"
)
public class Q2116 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public boolean canBeValid(String s, String locked) {
        int n = s.length();
        if(n % 2 != 0) return false;
        int l = n / 2;
        int r = n / 2;

        char[] arr = s.toCharArray();

        for(int i = 0; i < n; i++) {
            if(locked.charAt(i) == '1') {
                if(s.charAt(i) == '(') l--;
                if(s.charAt(i) == ')') r--;
            }
        }

        if(l < 0 || r < 0) return false;

        int cnt = 0;
        for(int i = 0; i < n; i++) {
            if(locked.charAt(i) == '0') {
                if(l > 0) {
                    arr[i] = '(';
                    l--;
                } else {
                    arr[i] = ')';
                    r--;
                }
                if(r < 0) return false;
            }
            if(arr[i] == '(') cnt++;
            else cnt--;
            if(cnt < 0) return false;
        }

        return cnt == 0;
    }

    /**
     * [cmin, cmax] denotes possible "balance so far"
     * i.e. [0, 2] means,
     * 0: It's totally balanced.
     * 1: one unclosed open parentheses.
     * 2: two unclosed open parentheses.
     *
     * if s[i] is locked "(" -> cmin++ & cmax++
     * if s[i] is locked ")" -> cmin-- & cmax--
     * if s[i] is unlocked   -> cmin-- (it can be a close) && cmax++ (it can be a open)
     * */
    public boolean countSol(String s, String locked) {
        int n = s.length();
        if(n % 2 != 0) return false;

        int cmin = 0;
        int cmax = 0;
        for(int i = 0; i < n; i++) {
            char c = s.charAt(i);

            if(locked.charAt(i) == '0') {
                cmin = Math.max(0, cmin - 1);
                cmax++;
            } else {
                if(c == '(') {
                    cmin++;
                    cmax++;
                } else {
                    cmin = Math.max(0, cmin - 1);
                    cmax--;
                }
            }

            if(cmax < 0) return false;
        }

        return cmin == 0;
    }
}
