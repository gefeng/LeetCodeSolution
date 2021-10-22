package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Find Elements in a Contaminated Binary Tree",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/find-elements-in-a-contaminated-binary-tree/"
)
public class Q1261 {
    /**
     * Time:  O(N) + O(1)
     * Space: O(N)
     * */
    int[] arr;
    public Q1261(TreeNode root) {
        arr = new int[1 << 20];
        Arrays.fill(arr, -1);

        dfs(root, 0);
    }

    public boolean find(int target) {
        return arr[target] != -1;
    }

    private void dfs(TreeNode root, int idx) {
        if(root == null) {
            return;
        }

        arr[idx] = idx;

        dfs(root.left, 2 * idx + 1);
        dfs(root.right, 2 * idx + 2);
    }
}
