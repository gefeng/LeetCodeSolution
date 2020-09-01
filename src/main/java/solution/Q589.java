package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

@Problem(
        title = "N-ary Tree Preorder Traversal",
        difficulty = QDifficulty.EASY,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/n-ary-tree-preorder-traversal/"
)
public class Q589 {
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
    public List<Integer> preorderIterative(Node root) {
        if(root == null)
            return new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        Node curr = root;
        stack.push(curr);
        while(!stack.isEmpty()) {
            curr = stack.pop();
            ans.add(curr.val);
            for(int i = curr.children.size() - 1; i >= 0; i--)
                stack.push(curr.children.get(i));
        }

        return ans;
    }

    public List<Integer> preorderRecursive(Node root) {
        List<Integer> ans = new ArrayList<>();
        traversal(root, ans);
        return ans;
    }

    private void traversal(Node node, List<Integer> ans) {
        if(node == null)
            return;

        ans.add(node.val);

        for(Node child : node.children)
            traversal(child, ans);
    }
}
