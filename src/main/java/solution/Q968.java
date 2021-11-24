package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Binary Tree Cameras",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/binary-tree-cameras/"
)
public class Q968 {
    /*
        state:
            s[0]: minimum cameras root has camera
            s[1]: minimum cameras root has no camera but get covered
            s[2]: minimum cameras root has no camera not get covered
        transition:
            s[0] = min(l[0], l[1], l[2]) + min(r[0], r[1], r[2]) + 1
            s[1] = min(l[0] + r[0], l[0] + r[1], r[0] + l[1])
            s[2] = l[1] + r[1]

        if root == null then
            s[0] = MAX
            s[1] = 0
            s[2] = 0
    */
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    private static final int MAX = 1001;
    public int minCameraCover(TreeNode root) {
        int[] s = helper(root);
        return Math.min(s[0], s[1]);
    }

    private int[] helper(TreeNode root) {
        if(root == null) {
            return new int[] {MAX, 0, 0};
        }

        int[] l = helper(root.left);
        int[] r = helper(root.right);

        int[] s = new int[3];
        s[0] = Math.min(l[0], Math.min(l[1], l[2])) + Math.min(r[0], Math.min(r[1], r[2])) + 1;
        s[1] = Math.min(l[0] + r[0], Math.min(l[0] + r[1], r[0] + l[1]));
        s[2] = l[1] + r[1];

        return s;
    }
}
