package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Binary Tree Tilt",
        difficulty = QDifficulty.EASY,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/binary-tree-tilt/"
)
public class Q563 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int findTilt(TreeNode root) {
        return postorder(root)[0];
    }

    private int[] postorder(TreeNode root) {
        if(root == null) {
            return new int[] {0, 0};
        }

        int[] l = postorder(root.left);
        int[] r = postorder(root.right);

        int sum = l[0] + r[0] + Math.abs(l[1] - r[1]);

        return new int[] {l[0] + r[0] + Math.abs(l[1] - r[1]), l[1] + r[1] + root.val};
    }
}
