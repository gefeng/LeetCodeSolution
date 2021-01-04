package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

import java.util.Stack;

@Problem(
        title = "Sum of Root To Leaf Binary Numbers",
        difficulty = QDifficulty.EASY,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/sum-of-root-to-leaf-binary-numbers/"
)
public class Q1022 {
    int sum = 0;
    public int sumRootToLeaf(TreeNode root) {
        preorder(root, 0);
        return sum;
    }

    private void preorder(TreeNode root, int val) {
        if(root == null)
            return;

        val = (val << 1) + root.val;

        if(root.left == null && root.right == null)
            sum+=val;

        preorder(root.left, val);
        preorder(root.right, val);
    }

    private int interative(TreeNode root) {
        int sum = 0;
        if(root == null)
            return sum;
        Stack<TreeNode> stackNode = new Stack<>();
        Stack<Integer> stackValue = new Stack<>();
        stackNode.push(root);
        stackValue.push(0);
        while(!stackNode.isEmpty()) {
            TreeNode curr = stackNode.pop();
            int val = stackValue.pop();
            val = (val << 1) + curr.val;

            if(curr.left == null && curr.right == null)
                sum += val;

            if(curr.right != null) {
                stackNode.push(curr.right);
                stackValue.push(val);
            }
            if(curr.left != null) {
                stackNode.push(curr.left);
                stackValue.push(val);
            }
        }

        return sum;
    }
}
