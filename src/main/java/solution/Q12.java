package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;

@Problem(
        title = "Integer to Roman",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/integer-to-roman/"
)
public class Q12 {
    public String intToRoman(int num) {
        HashMap<Integer, Character> lookup = new HashMap<>() {
            {
                put(1, 'I');
                put(5, 'V');
                put(10, 'X');
                put(50, 'L');
                put(100, 'C');
                put(500, 'D');
                put(1000, 'M');
            }
        };

        StringBuilder roman = new StringBuilder();
        for(int i = 1000; i > 0; i /= 10) {
            int digit = num / i;
            if(digit >= 1 && digit <= 3) {
                for(int j = 0; j < digit; j++)
                    roman.append(lookup.get(i));
            } else if(digit == 4) {
                roman.append(lookup.get(i));
                roman.append(lookup.get(5 * i));
            } else if(digit >= 5 && digit <= 8) {
                roman.append(lookup.get(5 * i));
                for(int j = 5; j < digit; j++)
                    roman.append(lookup.get(i));
            } else if(digit == 9) {
                roman.append(lookup.get(i));
                roman.append(lookup.get(10 * i));
            }
            num = num - digit * i;
        }
        return roman.toString();
    }
}
