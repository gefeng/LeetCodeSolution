package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

@Problem(
        title = "Boundary of Binary Tree",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/boundary-of-binary-tree/"
)
public class Q545 {
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> boundary = new ArrayList<>();
        if(root == null)
            return boundary;

        boundary.add(root.val);
        leftBoundary(root, boundary);
        bottomBoundary(root.left, boundary);
        bottomBoundary(root.right, boundary);
        rightBoundary(root, boundary);

        return boundary;
    }

    private void leftBoundary(TreeNode root, List<Integer> boundary) {
        TreeNode curr = root.left;
        while(curr != null) {
            if(curr.left != null || curr.right != null)
                boundary.add(curr.val);
            curr = curr.left == null ? curr.right : curr.left;
        }
    }

    private void rightBoundary(TreeNode root, List<Integer> boundary) {
        TreeNode curr = root.right;
        int size = boundary.size();
        while(curr != null) {
            if(curr.left != null || curr.right != null)
                boundary.add(size, curr.val);
            curr = curr.right == null ? curr.left : curr.right;
        }
    }

    private void bottomBoundary(TreeNode root, List<Integer> leaves) {
        if(root == null)
            return;

        if(root.left == null && root.right == null)
            leaves.add(root.val);
        bottomBoundary(root.left, leaves);
        bottomBoundary(root.right, leaves);
    }
}
