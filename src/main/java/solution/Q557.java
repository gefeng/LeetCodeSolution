package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Reverse Words in a String III",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/reverse-words-in-a-string-iii/"
)
public class Q557 {
    public String reverseWords(String s) {
        char[] chars = s.toCharArray();
        int length = s.length();
        int start = 0;
        int end = 0;

        for(int i = 0; i < length; i++) {
            if(i == 0 || chars[i - 1] == ' ')
                start = i;
            if(i == length - 1 || chars[i + 1] == ' ') {
                end = i;

                while (start < end) {
                    char temp = chars[start];
                    chars[start++] = chars[end];
                    chars[end--] = temp;
                }
            }
        }

        return new String(chars);
    }
}
