package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Diameter of Binary Tree",
        difficulty = QDifficulty.EASY,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/diameter-of-binary-tree/"
)
public class Q543 {
    int ans = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        findLongest(root);
        return ans;
    }
    private int findLongest(TreeNode node) {
        if(node == null) return 0;
        int lPathLength = findLongest(node.left);
        int rPathLength = findLongest(node.right);
        ans = Math.max(ans, lPathLength + rPathLength);
        return Math.max(lPathLength, rPathLength) + 1;
    }
}
