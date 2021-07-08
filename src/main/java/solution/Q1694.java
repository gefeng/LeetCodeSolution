package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Deque;

@Problem(
        title = "Reformat Phone Number",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/reformat-phone-number/"
)
public class Q1694 {
    public String reformatNumber(String number) {
        int n = number.length();
        StringBuilder sb = new StringBuilder();
        Deque<Character> deque = new ArrayDeque<>();

        for(int i = 0; i < n; i++) {
            char c = number.charAt(i);
            if(c != ' ' && c != '-') {
                deque.offerLast(c);
            }
        }

        while(deque.size() > 4) {
            for(int i = 0; i < 3; i++) {
                sb.append(deque.pollFirst());
            }
            sb.append('-');
        }

        if(deque.size() == 4) {
            sb.append(deque.pollFirst());
            sb.append(deque.pollFirst());
            sb.append('-');
            sb.append(deque.pollFirst());
            sb.append(deque.pollFirst());
        } else {
            while(!deque.isEmpty()) {
                sb.append(deque.pollFirst());
            }
        }

        return sb.toString();
    }
}
