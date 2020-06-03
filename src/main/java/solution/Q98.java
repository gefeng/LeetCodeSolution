package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

import java.util.Stack;

@Problem(
        title = "Validate Binary Search Tree",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/validate-binary-search-tree/"
)
public class Q98 {
    public boolean dfs(TreeNode node, Integer floor, Integer ceiling) {
        if(node == null)
            return true;
        if(floor != null && node.val <= floor)
            return false;
        if(ceiling != null && node.val >= ceiling)
            return false;

        if(!dfs(node.left, floor, node.val))
            return false;
        if(!dfs(node.right, node.val, ceiling))
            return false;

        return true;
    }
    public boolean isValidBST(TreeNode root) {
        return dfs(root, null, null);
    }

    public boolean isValidBSTInorder(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        Integer inorder = null;
        while(!stack.isEmpty() || curr != null) {
            while(curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            curr = stack.pop();
            if(inorder != null && curr.val <= inorder)
                return false;

            inorder = curr.val;
            curr = curr.right;
        }
        return true;
    }
}
