package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "One Edit Distance",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/one-edit-distance/"
)
public class Q161 {
    public boolean isOneEditDistance(String s, String t) {
        int lenS = s.length();
        int lenT = t.length();
        if(lenT > lenS) {
            return isOneEditDistance(t, s);
        }

        if(lenS - lenT > 1) {
            return false;
        }

        for(int i = 0; i < lenT; i++) {
            char sc = s.charAt(i);
            char tc = t.charAt(i);
            if(sc != tc) {
                if(lenS == lenT) { // replace
                    return s.substring(i + 1, lenS).equals(t.substring(i + 1, lenT));
                } else {  // delete
                    return s.substring(i + 1, lenS).equals(t.substring(i, lenT));
                }
            }
        }

        return lenS - lenT == 1; // two same strings are not one edit distance away
    }
}
