package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "License Key Formatting",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/license-key-formatting/"
)
public class Q482 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public String licenseKeyFormatting(String s, int k) {
        int n = s.length();
        StringBuilder sb = new StringBuilder();

        int cnt = 0;
        for(int i = n - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if(c == '-') {
                continue;
            }

            if(Character.isLetter(c)) {
                c = Character.toUpperCase(c);
            }

            if(cnt == 0 && sb.length() != 0) {
                sb.append('-');
            }

            sb.append(c);
            cnt = (cnt + 1) % k;
        }

        return sb.reverse().toString();
    }
}
