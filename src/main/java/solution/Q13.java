package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Roman to Integer",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/roman-to-integer/"
)
public class Q13 {
    public int romanToInt(String s) {
        int retVal = 0;
        if(s.indexOf("IV") != -1) retVal -= 2;
        if(s.indexOf("IX") != -1) retVal -= 2;
        if(s.indexOf("XL") != -1) retVal -= 20;
        if(s.indexOf("XC") != -1) retVal -= 20;
        if(s.indexOf("CD") != -1) retVal -= 200;
        if(s.indexOf("CM") != -1) retVal -= 200;

        for(int i = 0; i < s.length(); ++i) {
            char rc = s.charAt(i);
            if(rc == 'I') retVal += 1;
            else if(rc == 'V') retVal += 5;
            else if(rc == 'X') retVal += 10;
            else if(rc == 'L') retVal += 50;
            else if(rc == 'C') retVal += 100;
            else if(rc == 'D') retVal += 500;
            else if(rc == 'M') retVal += 1000;
        }

        return retVal;
    }
}
