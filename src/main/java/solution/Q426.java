package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Stack;

@Problem(
        title = "Convert Binary Search Tree to Sorted Doubly Linked List",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/"
)
public class Q426 {
    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }

    Node prev = null;
    Node head = null;
    public Node treeToDoublyList(Node root) {
        traverse(root);

        if(head != null) {
            head.left = prev;
            prev.right = head;
        }
        return head;
    }

    private void traverse(Node root) {
        if(root == null)
            return;

        traverse(root.left);
        if(prev == null)
            head = root;
        else {
            prev.right = root;
            root.left = prev;
        }
        prev = root;
        traverse(root.right);
    }

    public Node treeToDoublyListIterative(Node root) {
        Stack<Node> stack = new Stack<>();
        Node head = null;
        Node prev = null;
        Node curr = root;
        while(curr != null || !stack.isEmpty()) {
            while(curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            if(prev == null)
                head = curr;
            else {
                curr.left = prev;
                prev.right = curr;
            }
            prev = curr;
            curr = curr.right;
        }

        if(head != null) {
            head.left = prev;
            prev.right = head;
        }
        return head;
    }
}
