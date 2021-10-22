package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Encode Number",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/encode-number/"
)
public class Q1256 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public String encode(int num) {
        StringBuilder sb = new StringBuilder();

        num = num + 1;
        while(num > 1) {
            sb.append(num % 2);
            num >>= 1;
        }

        return sb.reverse().toString();
    }
}
