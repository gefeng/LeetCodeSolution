package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Deque;

@Problem(
        title = "Remove Duplicate Letters",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STACK,
        url = "https://leetcode.com/problems/remove-duplicate-letters/"
)
public class Q316 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public String removeDuplicateLetters(String s) {
        int n = s.length();
        int[] last = new int[26];
        boolean[] seen = new boolean[26];

        Deque<Integer> stack = new ArrayDeque<>();

        for(int i = 0; i < n; i++) {
            last[s.charAt(i) - 'a'] = i;
        }

        for(int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';

            if(seen[c]) {
                continue;
            }

            while(!stack.isEmpty() && stack.peek() > c && last[stack.peek()] > i) {
                seen[stack.pop()] = false;
            }

            stack.push(c);
            seen[c] = true;
        }

        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            sb.append((char)(stack.pop() + 'a'));
        }
        return sb.reverse().toString();
    }
}
