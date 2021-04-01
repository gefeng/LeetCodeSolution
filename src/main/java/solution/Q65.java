package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Valid Number",
        difficulty = QDifficulty.HARD,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/valid-number/"
)
public class Q65 {
    public boolean isNumber(String s) {
        boolean hasE = false;
        boolean hasDot = false;
        boolean hasDigit = false;

        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if(Character.isLetter(c) && c != 'e' && c != 'E')
                return false;

            if(Character.isDigit(c))
                hasDigit = true;
            else if(c == 'e' || c == 'E') {
                if(!hasDigit)
                    return false;
                if(i == s.length() - 1)
                    return false;
                if(hasE)
                    return false;
                hasE = true;
                hasDigit = false;
            }
            else if(c == '+' || c == '-') {
                if(!hasE && i > 0)
                    return false;
                if(hasE && hasDigit)
                    return false;
                if(i == s.length() - 1)
                    return false;
            }
            else if(c == '.') {
                if(hasDot)
                    return false;
                if(hasE)
                    return false;
                if(!hasDigit && i == s.length() - 1)  // '.' or '+.'
                    return false;
                hasDot = true;
            }
        }

        return true;
    }
}
