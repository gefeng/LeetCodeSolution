package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Inorder Successor in BST II",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/inorder-successor-in-bst-ii/"
)
public class Q510 {
    class Node {
        int val;
        Node left;
        Node right;
        Node parent;
    }
    public Node inorderSuccessor(Node node) {
        if(node == null)
            return null;

        Node curr = node;
        if(node.right != null) {
            curr = node.right;
            while(curr.left != null) {
                curr = curr.left;
            }
            return curr;
        }
        else {
            while(curr.parent != null && curr == curr.parent.right) {
                curr = curr.parent;
            }
            return curr.parent;
        }
    }
}
