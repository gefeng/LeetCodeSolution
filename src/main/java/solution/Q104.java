package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

import java.util.Stack;

@Problem(
        title = "Maximum Depth of Binary Tree",
        difficulty = QDifficulty.EASY,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/maximum-depth-of-binary-tree/"
)
public class Q104 {
    public int maxDepthRecursive(TreeNode root) {
        if(root == null)
            return 0;
        int depthLeft = maxDepthRecursive(root.left);
        int depthRight = maxDepthRecursive(root.right);
        return Math.max(depthLeft, depthRight) + 1;
    }

    public int maxDepthIterative(TreeNode root) {
        if(root == null)
            return 0;
        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> depth = new Stack<>();
        int maxDepth = 0;
        stack.push(root);
        depth.push(1);
        while(!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            int currDepth = depth.pop();
            if(curr.right != null) {
                stack.push(curr.right);
                depth.push(currDepth + 1);
            }
            if(curr.left != null) {
                stack.push(curr.left);
                depth.push(currDepth + 1);
            }
            if(curr.left == null && curr.right == null)
                maxDepth = currDepth > maxDepth ? currDepth : maxDepth;
        }
        return maxDepth;
    }
}
