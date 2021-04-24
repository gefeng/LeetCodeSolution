package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.Map;

@Problem(
        title = "Fraction to Recurring Decimal",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/fraction-to-recurring-decimal/"
)
public class Q166 {
    public String fractionToDecimal(int numerator, int denominator) {
        if(numerator == 0)
            return "0";

        StringBuilder fraction = new StringBuilder();
        if((numerator < 0 && denominator > 0) || (numerator > 0 && denominator < 0)) {
            fraction.append("-");
        }

        long x = Math.abs((long)numerator);
        long y = Math.abs((long)denominator);
        fraction.append(x / y);

        long r = x % y;
        if(r == 0) {
            return fraction.toString();
        }

        fraction.append(".");
        Map<Long, Integer> posMap = new HashMap<>();
        while(r != 0) {
            if(posMap.containsKey(r)) {
                fraction.insert(posMap.get(r), "(");
                fraction.append(")");
                break;
            }
            posMap.put(r, fraction.length());
            r *= 10;
            fraction.append(String.valueOf(r / y));
            r = r % y;
        }

        return fraction.toString();
    }
}
