package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Deque;

@Problem(
        title = "Smallest K-Length Subsequence With Occurrences of a Letter",
        difficulty = QDifficulty.HARD,
        tag = QTag.STACK,
        url = "https://leetcode.com/problems/smallest-k-length-subsequence-with-occurrences-of-a-letter/"
)
public class Q2030 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public String smallestSubsequence(String s, int k, char letter, int repetition) {
        int total = 0;
        int n = s.length();

        for(int i = 0; i < n; i++) {
            if(s.charAt(i) == letter) {
                total++;
            }
        }

        Deque<Character> stack = new ArrayDeque<>();
        int left = total;
        int used = 0;
        for(int i = 0; i < n; i++) {
            char c = s.charAt(i);
            while(!stack.isEmpty() && stack.peek() > c && n - i + stack.size() > k) {
                if(stack.peek() == letter && (left + used) <= repetition) {
                    break;
                }

                if(stack.pop() == letter) {
                    used--;
                }
            }

            stack.push(c);
            if(c == letter) {
                used++;
                left--;
            }
        }

        // truncate tail
        while(stack.size() > k) {
            if(stack.peek() == letter && used == repetition) {
                break;
            }

            if(stack.pop() == letter) {
                used--;
            }
        }

        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            char c = stack.pop();
            if(c == letter) {
                sb.append(c);
            } else {
                if(sb.length() + stack.size() + 1 <= k) {
                    sb.append(c);
                }
            }
        }

        return sb.reverse().toString();
    }
}
