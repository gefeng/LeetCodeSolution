package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Latest Time by Replacing Hidden Digits",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/latest-time-by-replacing-hidden-digits/"
)
public class Q1736 {
    public String maximumTime(String time) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 5; i++) {
            char c = time.charAt(i);
            if(c == '?') {
                if(i == 0) {
                    sb.append(time.charAt(i + 1) < '4' || time.charAt(i + 1) == '?' ? '2' : '1');
                } else if(i == 1) {
                    sb.append(sb.charAt(0) == '2' ? '3' : '9');
                } else if(i == 3) {
                    sb.append('5');
                } else if(i == 4) {
                    sb.append('9');
                }
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }
}
