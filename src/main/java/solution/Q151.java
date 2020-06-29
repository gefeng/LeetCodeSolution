package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.Collections;

@Problem(
        title = "Reverse Words in String",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/reverse-words-in-a-string/"
)
public class Q151 {
    public String reverseWordsBuildin(String s) {
        String[] words = s.trim().split("\\s+");
        Collections.reverse(Arrays.asList(words));
        return String.join(" ", words);
    }
    /**
     * 1. reverse the whole string
     * 2. reverse each word **/
    public String reverseWords(String s) {
        char[] trimmed = trimSpace(s).toCharArray();
        reverseStr(trimmed);
        reverseWord(trimmed);

        return new String(trimmed);
    }

    private String trimSpace(String s) {
        StringBuilder sb = new StringBuilder();
        int head = 0;
        int tail = s.length() - 1;
        while(head < s.length() && s.charAt(head) == ' ') // remove leading space
            head++;
        while(tail >= 0 && s.charAt(tail) == ' ') // remove tailing space
            tail--;

        while(head <= tail) {  // reduce multiple space to single one
            char c = s.charAt(head);
            if(c != ' ')
                sb.append(c);
            else if(sb.charAt(sb.length() - 1) != ' ')
                sb.append(c);
            head++;
        }

        return sb.toString();
    }

    private void reverseStr(char[] s) {
        int head = 0;
        int tail = s.length - 1;
        while(head < tail) {
            char temp = s[head];
            s[head] = s[tail];
            s[tail] = temp;
            head++;
            tail--;
        }
    }

    private void reverseWord(char[] s) {
        int head = 0;
        int tail = 0;

        for(int i = 0; i < s.length; i++) {
            if(i - 1 >= 0 && s[i - 1] == ' ') {
                head = i;
            }
            if((i + 1 < s.length && s[i + 1] == ' ') || i == s.length - 1) {
                tail = i;
                while(head < tail) {
                    char temp = s[head];
                    s[head] = s[tail];
                    s[tail] = temp;
                    head++;
                    tail--;
                }
            }
        }
    }
}
