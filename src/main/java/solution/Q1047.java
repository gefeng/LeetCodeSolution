package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Deque;

@Problem(
        title = "Remove All Adjacent Duplicates In String",
        difficulty = QDifficulty.EASY,
        tag = QTag.STACK,
        url = "https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/"
)
public class Q1047 {
    public String removeDuplicates(String s) {
        int n = s.length();
        Deque<Character> deque = new ArrayDeque<>();

        for(int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if(!deque.isEmpty() && deque.peekLast() == c) {
                deque.pollLast();
            } else {
                deque.offerLast(c);
            }
        }

        StringBuilder sb = new StringBuilder();
        while(!deque.isEmpty()) {
            sb.append(deque.pollFirst());
        }
        return sb.toString();
    }
}
