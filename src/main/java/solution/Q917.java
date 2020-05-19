package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Reverse Only Letters",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING
)
public class Q917 {
    public String reverseOnlyLetters(String S) {
        int head = 0;
        int tail = S.length() - 1;
        char[] chars = S.toCharArray();
        while(head < tail) {
            if(!Character.isLetter(chars[head])) {
                head++;
                continue;
            }
            if(!Character.isLetter(chars[tail])) {
                tail--;
                continue;
            }
            char temp = chars[head];
            chars[head++] = chars[tail];
            chars[tail--] = temp;
        }
        return new String(chars);
    }
}
