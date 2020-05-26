package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

import java.util.Stack;

@Problem(
        title = "Path Sum",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/path-sum/"
)
public class Q112 {
    public boolean hasPathSumRecursive(TreeNode root, int sum) {
        if(root == null)
            return false;
        sum -= root.val;
        if(root.left == null && root.right == null)
            return sum == 0;

        return hasPathSumRecursive(root.left, sum) || hasPathSumRecursive(root.right, sum);
    }

    public boolean hasPathSumIterative(TreeNode root, int sum) {
        if(root == null)
            return false;

        Stack<TreeNode> nodeStack = new Stack<>();
        Stack<Integer> sumStack = new Stack<>();
        nodeStack.push(root);
        sumStack.push(root.val);
        while(!nodeStack.isEmpty()) {
            TreeNode currNode = nodeStack.pop();
            int currSum = sumStack.pop();
            if(currNode.left == null && currNode.right == null && currSum == sum)
                return true;
            if(currNode.right != null) {
                nodeStack.push(currNode.right);
                sumStack.push(currSum + currNode.right.val);
            }
            if(currNode.left != null) {
                nodeStack.push(currNode.left);
                sumStack.push(currSum + currNode.left.val);
            }
        }
        return false;
    }
}
