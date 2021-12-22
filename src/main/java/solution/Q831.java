package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Masking Personal Information",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/masking-personal-information/"
)
public class Q831 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public String maskPII(String s) {
        int n = s.length();
        int at = s.indexOf("@");
        if(at >= 0) {
            s = s.toLowerCase();
            return s.charAt(0) + "*****" + s.charAt(at - 1) + s.substring(at,  n);
        } else {
            int digits = 0;
            String last = "";

            for(int i = n - 1; i >= 0; i--) {
                if(Character.isDigit(s.charAt(i))) {
                    digits++;
                    if(last.length() < 4) {
                        last = s.charAt(i) + last;
                    }
                }
            }

            if(digits == 10) {
                return "***-***-" + last;
            } else {
                String country = "+";
                for(int i = 0; i < digits - 10; i++) {
                    country += "*";
                }

                return  country + "-***-***-" + last;
            }
        }
    }
}
