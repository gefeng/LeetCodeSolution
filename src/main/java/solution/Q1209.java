package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Deque;

@Problem(
        title = "Remove All Adjacent Duplicates in String II",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STACK,
        url = "https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/"
)
public class Q1209 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public String removeDuplicates(String s, int k) {
        Deque<int[]> stack = new ArrayDeque<>();
        int n = s.length();

        for(int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';

            if(!stack.isEmpty() && stack.peek()[0] == c) {
                if(stack.peek()[1] + 1 == k) {
                    while(!stack.isEmpty() && stack.peek()[0] == c) {
                        stack.pop();
                    }
                } else {
                    stack.push(new int[] {c, stack.peek()[1] + 1});
                }
            } else {
                stack.push(new int[] {c, 1});
            }
        }

        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            sb.append((char)(stack.pop()[0] + 'a'));
        }

        return sb.reverse().toString();
    }
}
