package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

@Problem(
        title = "Lowest Common Ancestor of a Binary Tree",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/"
)
public class Q236 {
    /*这题的递归解法比较独特*/
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q)
            return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        return left == null ? right : (right == null ? left : root);
    }

    public TreeNode lowestCommonAncestorIterative(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null)
            return null;
        Stack<TreeNode> stack = new Stack<>();
        HashMap<TreeNode, TreeNode> parents = new HashMap<>();

        stack.push(root);
        parents.put(root, null);
        while(!parents.containsKey(p) || !parents.containsKey(q)) {
            TreeNode curr = stack.pop();

            if(curr.right != null) {
                parents.put(curr.right, curr);
                stack.push(curr.right);
            }
            if(curr.left != null) {
                parents.put(curr.left, curr);
                stack.push(curr.left);
            }
        }

        HashSet<TreeNode> pAncestors = new HashSet<>();
        TreeNode pa = p;
        while(pa != null) {
            pAncestors.add(pa);
            pa = parents.get(pa);
        }

        TreeNode qa = q;
        while(!pAncestors.contains(qa))
            qa = parents.get(qa);

        return qa;
    }
}
