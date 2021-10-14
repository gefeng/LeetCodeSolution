package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Decrypt String From Alphabet to Integer Mapping",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/decrypt-string-from-alphabet-to-integer-mapping/"
)
public class Q1309 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public String freqAlphabets(String s) {
        int n = s.length();
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < n; i++) {
            if(i + 2 < n && s.charAt(i + 2) == '#') {
                String ss = s.substring(i, i + 2);
                sb.append((char)(Integer.parseInt(ss) - 1 + 'a'));
                i += 2;
            } else {
                sb.append((char)(s.charAt(i) - '0' - 1 + 'a'));
            }
        }

        return sb.toString();
    }
}
