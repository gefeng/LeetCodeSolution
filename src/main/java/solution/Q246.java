package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Strobogrammatic Number",
        difficulty = QDifficulty.EASY,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/strobogrammatic-number/"
)
public class Q246 {
    public boolean isStrobogrammatic(String num) {
        int lo = 0;
        int hi = num.length() - 1;

        while(lo <= hi) {
            char digit1 = num.charAt(lo++);
            char digit2 = num.charAt(hi--);

            if(digit1 == '6' && digit2 == '9') {
                continue;
            }
            if(digit1 == '9' && digit2 == '6') {
                continue;
            }
            if((digit1 == digit2) && (digit1 == '0' || digit1 == '1' || digit1 == '8')) {
                continue;
            }

            return false;
        }

        return true;
    }
}
