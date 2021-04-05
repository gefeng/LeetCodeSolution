package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

import java.util.Stack;

@Problem(
        title = "Recover Binary Search Tree",
        difficulty = QDifficulty.HARD,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/recover-binary-search-tree/"
)
public class Q99 {
    /*
    * 这题有个从sorted array里面找一个swapped的pair的算法
    * */
    TreeNode prev = null;
    TreeNode bad1 = null;
    TreeNode bad2 = null;
    public void recoverTree(TreeNode root) {
        inorder(root);

        int temp = bad1.val;
        bad1.val = bad2.val;
        bad2.val = temp;
    }

    private void inorder(TreeNode root) {
        if(root == null)
            return;

        inorder(root.left);

        if(prev != null) {
            if(root.val < prev.val) {
                if(bad1 == null)
                    bad1 = prev;
                bad2 = root;
            }
        }

        prev = root;
        inorder(root.right);
    }

    private void iterativeSolution(TreeNode root) {
        TreeNode x = null;
        TreeNode y = null;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode prev = null;
        TreeNode curr = root;
        while(curr != null || !stack.isEmpty()) {
            while(curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            if(prev != null) {
                if(curr.val < prev.val) {
                    x = x == null ? prev : x;
                    y = curr;
                }
            }

            prev = curr;
            curr = curr.right;
        }

        int temp = x.val;
        x.val = y.val;
        y.val = temp;
    }
}
