package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Count Good Nodes in Binary Tree",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/count-good-nodes-in-binary-tree/"
)
public class Q1448 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int goodNodes(TreeNode root) {
        return preorder(root, Integer.MIN_VALUE);
    }

    private int preorder(TreeNode root, int max) {
        if(root == null) {
            return 0;
        }

        int cnt = 0;
        if(root.val >= max) {
            cnt++;
            max = root.val;
        }

        cnt += preorder(root.left, max);
        cnt += preorder(root.right, max);

        return cnt;
    }
}
