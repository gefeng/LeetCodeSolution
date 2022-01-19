package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Print Binary Tree",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/print-binary-tree/"
)
public class Q655 {
    /**
     * Time:  O(N)
     * Space: O(H * N)
     * */
    public List<List<String>> printTree(TreeNode root) {
        List<List<String>> ans = new ArrayList<>();

        int m = getHeight(root);
        int n = (int)Math.pow(2, m) - 1;

        for(int i = 0; i < m; i++) {
            List<String> row = new ArrayList<>();
            for(int j = 0; j < n; j++) {
                row.add("");
            }
            ans.add(row);
        }

        dfs(root, 0, (n - 1) / 2, ans);

        return ans;
    }

    private int getHeight(TreeNode root) {
        if(root == null) return 0;

        int l = getHeight(root.left);
        int r = getHeight(root.right);

        return Math.max(l, r) + 1;
    }

    private void dfs(TreeNode root, int r, int c, List<List<String>> ans) {
        if(root == null) return;

        int m = ans.size();
        int n = ans.get(0).size();
        ans.get(r).set(c, Integer.toString(root.val));

        int x = (int)Math.pow(2, m - 2 - r);
        if(r + 1 < m && c - x >= 0) {
            dfs(root.left, r + 1, c - x, ans);
        }
        if(r + 1 < m && c + x < n) {
            dfs(root.right, r + 1, c + x, ans);
        }
    }
}
