package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Magical String",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/magical-string/"
)
public class Q481 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int magicalString(int n) {
        if(n <= 3) {
            return 1;
        }

        int res = 1;
        StringBuilder sb = new StringBuilder();

        sb.append("122");
        int i = 2;

        while(sb.length() < n) {
            char c = sb.charAt(i);

            if(c == '1') {
                if(sb.charAt(sb.length() - 1) == '1') {
                    sb.append("2");
                } else {
                    sb.append("1");
                    res++;
                }
            } else {
                if(sb.charAt(sb.length() - 1) == '1') {
                    sb.append("22");
                } else {
                    sb.append("11");
                    res += 2;
                }
            }

            i++;
        }

        if(sb.length() == n) {
            return res;
        } else {
            return sb.charAt(sb.length() - 1) == '1' ? res - 1 : res;
        }
    }
}
