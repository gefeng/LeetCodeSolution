package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Sum Root of Leaf Numbers",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/sum-root-to-leaf-numbers/"
)
public class Q129 {
    int sum = 0;
    public int sumNumbers(TreeNode root) {
        dfs(root, 0);
        return sum;
    }

    private void dfs(TreeNode root, int num) {
        if(root == null) {
            return;
        }

        num = num * 10 + root.val;

        if(root.left == null && root.right == null) {
            sum += num;
        }

        dfs(root.left, num);
        dfs(root.right, num);
    }
}
