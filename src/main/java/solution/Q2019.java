package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

@Problem(
        title = "The Score of Students Solving Math Expression",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/the-score-of-students-solving-math-expression/"
)
public class Q2019 {
    /**
     * Time:  O(N ^ 3 * 1000 ^ 2)
     * Space: O(N ^ 2 * 1000)
     * */
    public int scoreOfStudents(String s, int[] answers) {
        int ans = 0;
        int n = s.length();
        int res = eval(s);
        Set<Integer> incorrect = solve(s, 0, n - 1, new Set[n][n]);

        for(int a : answers) {
            if(a == res) {
                ans += 5;
            } else if(incorrect.contains(a)) {
                ans += 2;
            }
        }

        return ans;
    }

    private int eval(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        int n = s.length();
        int o = '+';
        for(int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if(c == '*' || c == '+') {
                o = c;
            } else {
                if(o == '*') {
                    stack.push(stack.pop() * (c - '0'));
                } else {
                    stack.push(c - '0');
                }
            }
        }

        int ans = 0;
        while(!stack.isEmpty()) {
            ans += stack.pop();
        }
        return ans;
    }

    private Set<Integer> solve(String s, int l, int r, Set<Integer>[][] memo) {
        Set<Integer> ret = new HashSet<>();

        if(l == r) {
            ret.add(s.charAt(l) - '0');
            return ret;
        }

        if(memo[l][r] != null) {
            return memo[l][r];
        }

        for(int i = l; i <= r; i++) {
            char c = s.charAt(i);
            if(c == '+' || c == '*') {
                Set<Integer> a = solve(s, l, i - 1, memo);
                Set<Integer> b = solve(s, i + 1, r, memo);
                for(int x : a) {
                    for(int y : b) {
                        int res = c == '+' ? x + y : x * y;
                        if(res <= 1000) {
                            ret.add(res);
                        }
                    }
                }
            }
        }

        return memo[l][r] = ret;
    }
}
