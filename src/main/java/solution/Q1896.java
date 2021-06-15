package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

@Problem(
        title = "Minimum Cost to Change the Final Value of Expression",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/minimum-cost-to-change-the-final-value-of-expression/"
)
public class Q1896 {
    /*
        Given x &| y, we can recursively solve x, y and return,
        1. the final value of expression
        2. minimum cost to flip the final value of expression
        then we can solve x &| y.

        q1: how to handle parentheses?
            Iterate through the whole expression and save each pairs in map.
            This helps us to quickly lookup a nested expression if we find a ')'.

        q2: how to solve base case x &| y?
            if &:
                x = 0, y = 0 [0, min(cost(x) + 1, cost(y) + 1)]
                x = 0, y = 1 [0, 1]
                x = 1, y = 1 [1, min(cost(x), cost(y))]
                x = 1, y = 0 [0, 1]
            if |:
                x = 0, y = 0 [0, min(cost(x), cost(y))]
                x = 0, y = 1 [1, 1]
                x = 1, y = 1 [1, min(cost(x) + 1, cost(y) + 1)]
                x = 1, y = 0 [1, 1]
    */
    public int minOperationsToFlip(String expression) {
        int n = expression.length();
        Map<Integer, Integer> pMap = new HashMap<>();
        Deque<Integer> pStack = new ArrayDeque<>();

        // Save positions of each pair of parentheses
        for(int i = 0; i < n; i++) {
            char c = expression.charAt(i);
            if(c == '(') {
                pStack.push(i);
            } else if(c == ')') {
                pMap.put(i, pStack.pop());
            }
        }

        return dfs(expression, 0, n - 1, pMap)[1];
    }

    /*
        solve the expression backwards based on left-to-right rule.
        ret[0]: result of expression
        ret[1]: minimum cost to flip result
    */
    private int[] dfs(String expression, int start, int end, Map<Integer, Integer> pMap) {
        if(start > end) {
            return null;
        }

        // handle case like (0) or (1)
        if(start == end) {
            return new int[] { expression.charAt(start) - '0', 1 };
        }

        int[] lRes;
        int[] rRes;
        char op;
        char c = expression.charAt(end);
        if(c == ')') {
            int open = pMap.get(end);
            lRes = dfs(expression, start, open - 2, pMap);
            rRes = dfs(expression, open + 1, end - 1, pMap);
            op = lRes != null ? expression.charAt(open - 1) : '?';
        } else {
            lRes = dfs(expression, start, end - 2, pMap);
            rRes = new int[] { c - '0', 1 };
            op = expression.charAt(end - 1);
        }

        // handle case like (x & y), no left expression
        if(lRes == null) {
            return rRes;
        }

        return solve(lRes, rRes, op);
    }

    private int[] solve(int[] lRes, int[] rRes, char op) {
        int[] res = new int[2];
        int x = lRes[0];
        int y = rRes[0];
        int minCost = Math.min(lRes[1], rRes[1]);

        if(op == '&') {
            if(x != y) {
                res[0] = 0;
                res[1] = 1;
            } else {
                res[0] = x;
                res[1] = x == 0 ? minCost + 1 : minCost;
            }
        } else {
            if(x != y) {
                res[0] = 1;
                res[1] = 1;
            } else {
                res[0] = x;
                res[1] = x == 0 ? minCost : minCost + 1;
            }
        }

        return res;
    }
}
