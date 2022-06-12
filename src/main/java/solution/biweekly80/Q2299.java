package solution.biweekly80;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Strong Password Checker II",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/contest/biweekly-contest-80/problems/strong-password-checker-ii/"
)
public class Q2299 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public boolean strongPasswordCheckerII(String password) {
        String spe = "!@#$%^&*()-+";
        int n = password.length();
        int flag = 0;

        for(int i = 0; i < n; i++) {
            char c = password.charAt(i);

            if(c >= 'a' && c <= 'z') flag |= 1;
            if(c >= 'A' && c <= 'Z') flag |= 2;
            if(c >= '0' && c <= '9') flag |= 4;
            if(spe.indexOf(c) >= 0) flag |= 8;

            if(i > 0 && password.charAt(i) == password.charAt(i - 1)) {
                return false;
            }
        }

        return n >= 8 && flag == (1 << 4) - 1;
    }
}
