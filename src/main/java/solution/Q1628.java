package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

@Problem(
        title = "Design an Expression Tree With Evaluate Function",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DESIGN,
        url = "https://leetcode.com/problems/design-an-expression-tree-with-evaluate-function/"
)
public class Q1628 {
    /*
    * 这题是个很好的oo design
    * */
    abstract class Node {
        public abstract int evaluate();
        // define your fields here
        String symbol;
        public Node left;
        public Node right;
    };

    public class OperandNode extends Node {
        OperandNode(String symbol) {
            this.symbol = symbol;
        }

        public int evaluate() {
            return Integer.parseInt(symbol);
        }
    }

    public class OperatorNode extends Node {
        private BiFunction<Integer, Integer, Integer> operatorFunc;

        OperatorNode(String symbol, BiFunction<Integer, Integer, Integer> func) {
            this.symbol = symbol;
            this.operatorFunc = func;
        }

        public int evaluate() {
            int x = left.evaluate();
            int y = right.evaluate();

            return this.operatorFunc.apply(x, y);
        }
    }

    private static final BiFunction<Integer, Integer, Integer> ADD = (x, y) -> (x + y);
    private static final BiFunction<Integer, Integer, Integer> SUB = (x, y) -> (x - y);
    private static final BiFunction<Integer, Integer, Integer> MUL = (x, y) -> (x * y);
    private static final BiFunction<Integer, Integer, Integer> DIV = (x, y) -> (x / y);

    private static final Map<String, BiFunction<Integer, Integer, Integer>> OPERATOR_MAP = new HashMap<>();
    static {
        OPERATOR_MAP.put("+", ADD);
        OPERATOR_MAP.put("-", SUB);
        OPERATOR_MAP.put("*", MUL);
        OPERATOR_MAP.put("/", DIV);
    };


    /**
     * This is the TreeBuilder class.
     * You can treat it as the driver code that takes the postinfix input
     * and returns the expression tree represnting it as a Node.
     */
    Node buildTree(String[] postfix) {
        Deque<Node> stack = new ArrayDeque<>();

        Node curr = null;
        for(int i = 0; i < postfix.length; i++) {
            String symbol = postfix[i];

            BiFunction<Integer, Integer, Integer> func = OPERATOR_MAP.get(symbol);
            if(func != null) {
                curr = new OperatorNode(symbol, func);
                curr.right = stack.pop();
                curr.left = stack.pop();
            } else {
                curr = new OperandNode(symbol);
            }

            stack.push(curr);
        }

        return curr;
    }
}
