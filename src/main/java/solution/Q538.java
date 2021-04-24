package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Deque;

@Problem(
        title = "Convert BST to Greater Tree",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/convert-bst-to-greater-tree/"
)
public class Q538 {
    /*
    * BST inorder traversal (left - mid - right) -> sequence in ascending order
    * BST inorder traversal reversed (right - mid - left) -> sequence int descending order
    * */
    public TreeNode convertBST(TreeNode root) {
        return iterativeSolution(root);
    }

    int sum = 0;
    private TreeNode recursiveSolution(TreeNode root) {
        if(root == null)
            return root;

        recursiveSolution(root.right);

        sum += root.val;
        root.val = sum;

        recursiveSolution(root.left);

        return root;
    }

    private TreeNode iterativeSolution(TreeNode root) {
        if(root == null)
            return root;

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;
        int sum = 0;
        while(curr != null || !stack.isEmpty()) {
            while(curr != null) {
                stack.push(curr);
                curr = curr.right;
            }

            curr = stack.pop();
            sum += curr.val;
            curr.val = sum;

            curr = curr.left;
        }

        return root;
    }
}
