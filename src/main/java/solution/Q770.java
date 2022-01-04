package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Basic Calculator IV",
        difficulty = QDifficulty.HARD,
        tag = QTag.STACK,
        url = "https://leetcode.com/problems/basic-calculator-iv/"
)
public class Q770 {
    /**
     * Time:  O(N ^ 2)
     * Space: O(N ^ 2)
     * */
    Map<String, Integer> map = new HashMap<>();
    public List<String> basicCalculatorIV(String expression, String[] evalvars, int[] evalints) {
        for(int i = 0; i < evalvars.length; i++) {
            map.put(evalvars[i], evalints[i]);
        }

        Deque<Expression> s1 = new ArrayDeque<>();
        Deque<Character> s2 = new ArrayDeque<>();

        int n = expression.length();
        for(int i = 0; i < n; i++) {
            char c = expression.charAt(i);
            if(c == ' ') continue;
            if(Character.isDigit(c)) {
                int num = 0;
                while(i < n && Character.isDigit(expression.charAt(i))) {
                    num = num * 10 + expression.charAt(i++) - '0';
                }
                Expression e = new Expression(num);
                s1.push(e);
                i--;
            } else if(Character.isLetter(c)) {
                StringBuilder sb = new StringBuilder();
                while(i < n && Character.isLetter(expression.charAt(i))) {
                    sb.append(expression.charAt(i++));
                }
                Expression e = new Expression(sb.toString());
                s1.push(e);
                i--;
            } else if(c == '(') {
                s2.push('(');
            } else if(c == ')') {
                while(s2.peek() != '(') {
                    char op = s2.pop();
                    Expression e2 = s1.pop();
                    Expression e1 = s1.pop();
                    s1.push(cal(e1, e2, op));
                }
                s2.pop();
            } else if(isOp(c)) {
                while(!s2.isEmpty() && priority(s2.peek()) >= priority(c)) {
                    char op = s2.pop();
                    Expression e2 = s1.pop();
                    Expression e1 = s1.pop();
                    s1.push(cal(e1, e2, op));
                }
                s2.push(c);
            }
        }

        while(!s2.isEmpty()) {
            char op = s2.pop();
            Expression e2 = s1.pop();
            Expression e1 = s1.pop();
            s1.push(cal(e1, e2, op));
        }

        return s1.pop().toList();
    }

    private Expression cal(Expression e1, Expression e2, char op) {
        if(op == '+') {
            return e1.add(e2, 1);
        } else if(op == '-') {
            return e1.add(e2, -1);
        } else {
            return e1.mul(e2);
        }
    }

    private int priority(char c) {
        if(c == '+' || c == '-') return 1;
        if(c == '*') return 2;
        return -1;
    }

    private boolean isOp(char c) {
        return c == '*' || c == '+' || c == '-';
    }

    private class Term {
        int coe = 1;
        List<String> vars = new ArrayList<>();

        Term(int coe) {
            this.coe = coe;
        }
        Term(String var) {
            if(map.containsKey(var)) this.coe = map.get(var);
            else vars.add(var);
        }
        Term(Term t) {
            this.coe = t.coe;
            for(String var : t.vars) this.vars.add(new String(var));
        }

        Term mul(Term t) {
            Term res = new Term(this.coe * t.coe);
            for(String var : this.vars) res.vars.add(new String(var));
            for(String var : t.vars) res.vars.add(new String(var));
            Collections.sort(res.vars);
            return res;
        }

        boolean equals(Term t) {
            if(vars.size() != t.vars.size()) return false;
            for(int i = 0; i < vars.size(); i++) {
                if(!vars.get(i).equals(t.vars.get(i))) return false;
            }
            return true;
        }

        int compareTo(Term t) {
            if(vars.size() > t.vars.size()) return -1;
            if(vars.size() < t.vars.size()) return 1;
            for(int i = 0; i < vars.size(); i++) {
                int res = vars.get(i).compareTo(t.vars.get(i));
                if(res < 0) return -1;
                if(res > 0) return 1;
            }
            return 0;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            if(coe == 0) return "";
            sb.append(coe);
            if(!vars.isEmpty()) {
                sb.append("*");
            }
            for(int i = 0; i < vars.size(); i++) {
                sb.append(vars.get(i));
                if(i < vars.size() - 1) sb.append("*");
            }
            return sb.toString();
        }
    }

    private class Expression {
        List<Term> l = new ArrayList<>();
        Expression(int x) {
            l.add(new Term(x));
        }

        Expression(String s) {
            l.add(new Term(s));
        }

        Expression(List<Term> l) {
            this.l = l;
        }

        Expression add(Expression e, int sign) {
            List<Term> res = new ArrayList<>();
            for(Term t : l) res.add(new Term(t));
            for(Term t : e.l) {
                Term tt = new Term(t);
                tt.coe *= sign;
                res.add(tt);
            }

            res = combine(res);

            return new Expression(res);
        }

        Expression mul(Expression e) {
            List<Term> res = new ArrayList<>();
            for(Term x : l) {
                for(Term y : e.l) {
                    res.add(x.mul(y));
                }
            }

            res = combine(res);

            return new Expression(res);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for(Term t : l) {
                sb.append(t.toString());
            }
            return sb.toString();
        }

        List<String> toList() {
            List<String> res = new ArrayList<>();
            for(Term t : l) {
                if(t.coe == 0) continue;
                res.add(t.toString());
            }
            return res;
        }

        List<Term> combine(List<Term> x) {
            Collections.sort(x, (a, b) -> { return a.compareTo(b); });
            List<Term> res = new ArrayList<>();
            for(int i = 0; i < x.size(); i++) {
                if(!res.isEmpty() && res.get(res.size() - 1).equals(x.get(i))) {
                    res.get(res.size() - 1).coe += x.get(i).coe;
                } else {
                    res.add(x.get(i));
                }
            }

            return res;
        }
    }
}
