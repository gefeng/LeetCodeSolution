package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

@Problem(
        title = "Parse Lisp Expression",
        difficulty = QDifficulty.HARD,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/parse-lisp-expression/"
)
public class Q736 {
    /**
     * Time:  O(N ^ 2)
     * Space: O(N)
     * */
    Queue<String> q;
    public int evaluate(String expression) {
        q = tokenize(expression);

        return parse(new HashMap<>());
    }

    private int parse(Map<String, Integer> map) {
        int res = 0;

        q.poll(); // remove "("

        String op = q.poll();

        if(op.equals("let")) {
            Map<String, Integer> nMap = new HashMap<>(map);

            while(!q.peek().equals(")")) {
                // last expression
                if(q.peek().equals("(")) {
                    res = parse(nMap);
                } else {
                    String token = q.poll();
                    if(q.peek().equals(")")) {
                        res = parseToken(token, nMap);
                    } else {
                        int val = q.peek().equals("(") ? parse(nMap) : parseToken(q.poll(), nMap);
                        nMap.put(token, val);
                    }
                }
            }

        } else if(op.equals("add")) {
            int opr1 = q.peek().equals("(") ? parse(map) : parseToken(q.poll(), map);
            int opr2 = q.peek().equals("(") ? parse(map) : parseToken(q.poll(), map);
            res = opr1 + opr2;
        } else {
            int opr1 = q.peek().equals("(") ? parse(map) : parseToken(q.poll(), map);
            int opr2 = q.peek().equals("(") ? parse(map) : parseToken(q.poll(), map);
            res = opr1 * opr2;
        }

        q.poll(); // remove ")"

        return res;
    }

    private int parseToken(String token, Map<String, Integer> map) {
        if(isVar(token)) {
            return map.get(token);
        }
        return Integer.parseInt(token);
    }

    private boolean isVar(String token) {
        return token.charAt(0) >= 'a' && token.charAt(0) <= 'z';
    }

    private Queue<String> tokenize(String e) {
        Queue<String> q = new ArrayDeque<>();
        int n = e.length();

        for(int i = 0; i < n; i++) {
            char c = e.charAt(i);
            if(c == ' ') continue;

            if(c == '(' || c == ')') {
                q.offer("" + c);
            } else {
                StringBuilder sb = new StringBuilder();
                int j = i;
                while(j < n && e.charAt(j) != ' ' && e.charAt(j) != ')') {
                    sb.append(e.charAt(j++));
                }
                q.offer(sb.toString());
                i = j - 1;
            }
        }

        return q;
    }
}
