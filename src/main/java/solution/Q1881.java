package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Maximum Value after Insertion",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/maximum-value-after-insertion/"
)
public class Q1881 {
    public String maxValue(String n, int x) {
        int len = n.length();
        StringBuilder sb = new StringBuilder();

        if(n.charAt(0) == '-') {
            sb.append("-");
            for(int i = 1; i < len; i++) {
                char c = n.charAt(i);
                if(c - '0' > x) {
                    sb.append(x);
                    return sb.append(n, i, len).toString();
                }
                sb.append(c);
            }
        } else {
            for(int i = 0; i < len; i++) {
                char c = n.charAt(i);
                if(c - '0' < x) {
                    sb.append(x);
                    return sb.append(n, i, len).toString();
                }
                sb.append(c);
            }
        }

        return sb.append(x).toString();
    }
}
