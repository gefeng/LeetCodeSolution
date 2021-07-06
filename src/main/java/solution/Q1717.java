package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Deque;

@Problem(
        title = "Maximum Score From Removing Substring",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STACK,
        url = "https://leetcode.com/problems/maximum-score-from-removing-substrings/"
)
public class Q1717 {
    public int maximumGain(String s, int x, int y) {
        int n = s.length();
        int res = 0;
        if(x > y) {
            String removeAB = remove(s, 'a', 'b');
            String removeBA = remove(removeAB, 'b', 'a');
            return (n - removeAB.length()) / 2 * x + (removeAB.length() - removeBA.length()) / 2 * y;
        } else {
            String removeBA = remove(s, 'b', 'a');
            String removeAB = remove(removeBA, 'a', 'b');
            return (n - removeBA.length()) / 2 * y + (removeBA.length() - removeAB.length()) / 2 * x;
        }
    }

    private String remove(String s, char x, char y) {
        Deque<Character> deque = new ArrayDeque<>();
        int n = s.length();

        for(int i = 0; i < n; i++) {
            char c = s.charAt(i);

            if(!deque.isEmpty() && deque.peekLast() == x && c == y) {
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
