package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Reverse Integer",
        difficulty = QDifficulty.EASY,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/reverse-integer/"
)
public class Q7 {
    /*
        if x > 0 and reverse > Integer.MAX_VALUE / 10 => must be overflow
        if x > 0 and reverse == Integer.MAX_VALUE / 10 and last digit > 7 => overflow
        same logic for negative x

        in Java % for negative number is negative i.e.
        -5 % 10 = -5
        5 % 10 = 5
    */
    public int reverse(int x) {
        int rx = 0;
        int maxGuard = Integer.MAX_VALUE / 10;
        int minGuard = Integer.MIN_VALUE / 10;
        while(x != 0) {
            int digit = x % 10;

            if(rx > maxGuard || rx < minGuard)
                return 0;
            if((rx == maxGuard && digit > 7) || (rx == minGuard && digit < -8))
                return 0;

            rx = digit + rx * 10;
            x = x / 10;
        }
        return rx;
    }
}
