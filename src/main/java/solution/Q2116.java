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
}
