package solution.weekly292;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Count Nodes Equal to Average of Subtree",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/contest/weekly-contest-292/problems/count-nodes-equal-to-average-of-subtree/"
)
public class Q2265 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int averageOfSubtree(TreeNode root) {
        return dfs(root)[0];
    }

    private int[] dfs(TreeNode root) {
        if(root == null) {
            return new int[] {0, 0, 0};
        }

        int[] l = dfs(root.left);
        int[] r = dfs(root.right);

        int sum = l[1] + r[1] + root.val;
        int tot = l[2] + r[2] + 1;
        int res = l[0] + r[0];

        if(tot > 0 && root.val == sum / tot) {
            res++;
        }

        return new int[] {res, sum, tot};
    }
}
