package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Convert to Base -2",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/convert-to-base-2/"
)
public class Q1017 {
    /**
     * Time:  O(1)
     * Space: O(1)
     * */
    public String baseNeg2(int n) {
        StringBuilder sb = new StringBuilder();

        while(n != 0) {
            int rem = n % (-2);
            n /= -2;

            if(rem < 0) {
                rem += 2;
                n += 1;
            }

            sb.append(rem);
        }

        return sb.length() == 0 ? "0" : sb.reverse().toString();
    }
}
