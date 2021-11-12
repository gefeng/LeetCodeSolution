package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Deque;

@Problem(
        title = "Smallest Subsequence of Distinct Characters",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STACK,
        url = "https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/"
)
public class Q1081 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public String smallestSubsequence(String s) {
        int n = s.length();

        StringBuilder sb = new StringBuilder();

        int[] cnt = new int[26];
        for(int i = 0; i < n; i++) {
            cnt[s.charAt(i) - 'a'] += 1;
        }

        Deque<Character> stack = new ArrayDeque<>();
        boolean[] seen = new boolean[26];

        for(int i = 0; i < n; i++) {
            char c = s.charAt(i);
            cnt[c - 'a'] -= 1;
            if(!seen[c - 'a']) {
                while(!stack.isEmpty() && stack.peek() > c && cnt[stack.peek() - 'a'] > 0) {
                    seen[stack.pop() - 'a'] = false;
                }

                stack.push(c);
                seen[c - 'a'] = true;
            }
        }

        while(!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }
}
