package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Reverse String II",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING
)
public class Q541 {
    public String reverseStr(String s, int k) {
        char[] chars = s.toCharArray();
        for(int start = 0; start < s.length(); start += 2 * k) {
            int i = start;
            int j = Math.min(start + k - 1, s.length() - 1);
            while(i < j) {
                char temp = chars[i];
                chars[i++] = chars[j];
                chars[j--] = temp;
            }
        }
        return new String(chars);
    }
}
