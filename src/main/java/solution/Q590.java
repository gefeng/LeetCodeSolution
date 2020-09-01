package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

@Problem(
        title = "N-ary Tree Postorder Traversal",
        difficulty = QDifficulty.EASY,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/n-ary-tree-postorder-traversal/"
)
public class Q590 {
    class Node {
        int val;
        List<Node> children;
        Node(int val) {
            this.val = val;
        }
        Node(int val, List<Node> children) {
            this.val = val;
            this.children = children;
        }
    };
    public List<Integer> postorderIterativeReverse(Node root) {
        List<Integer> ans = new LinkedList<>();
        if(root == null)
            return ans;

        Stack<Node> stack = new Stack<>();
        Node curr = root;
        stack.push(curr);
        while(!stack.isEmpty()) {
            curr = stack.pop();
            ans.add(0, curr.val);
            for(Node child : curr.children)
                stack.push(child);
        }
        return ans;
    }

    /*public List<Integer> postorderIterative(Node root) {
        List<Integer> ans = new LinkedList<>();
        if(root == null)
            return ans;

        Stack<Node> stack = new Stack<>();
        Node curr = root;
        stack.push(curr);
        while(!stack.isEmpty()) {
            curr = stack.peek();
            for(Node child : curr.children) {
                while(child != null) {
                    stack.push(child);
                    child =
                }
            }
        }
    }*/

    public List<Integer> postorderRecursive(Node root) {
        List<Integer> ans = new ArrayList<>();
        traversal(root, ans);
        return ans;
    }

    private void traversal(Node node, List<Integer> ans) {
        if(node == null)
            return;

        for(Node child : node.children) {
            traversal(child, ans);
        }

        ans.add(node.val);
    }
}
