package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Backspace String Compare",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/backspace-string-compare/"
)
public class Q844 {
    public boolean backspaceCompare(String s, String t) {
        return removeBackspace(s).equals(removeBackspace(t));
    }

    private String removeBackspace(String s) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == '#') {
                if(sb.length() != 0) {
                    sb.deleteCharAt(sb.length() - 1);
                }
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    private boolean constantSpaceSolution(String s, String t) {
        int i = s.length() - 1;
        int j = t.length() - 1;
        int skipS = 0;
        int skipT = 0;

        while(i >= 0 || j >= 0) {
            if(i < 0) {
                return skipT > j;
            }
            if(j < 0) {
                return skipS > i;
            }

            char cs = s.charAt(i);
            char ct = t.charAt(j);

            if(cs != '#' && ct != '#') {
                if(cs != ct) {
                    return false;
                }
                i--;
                j--;
            }

            if(cs == '#') {
                skipS++;
                i--;
            } else {
                i -= skipS;
                skipS = 0;
            }
            if(ct == '#') {
                skipT++;
                j--;
            } else {
                j -= skipT;
                skipT = 0;
            }
        }

        return true;
    }
}
