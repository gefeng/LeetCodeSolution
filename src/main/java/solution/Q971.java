package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Problem(
        title = "Flip Binary Tree To Match Preorder Traversal",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/flip-binary-tree-to-match-preorder-traversal/"
)
public class Q971 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    int pos = 0;
    List<Integer> ans = new ArrayList<>();
    public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
        if(dfs(root, voyage)) {
            return ans;
        }

        return Arrays.asList(-1);
    }

    private boolean dfs(TreeNode root, int[] v) {
        if(root.val != v[pos]) {
            return false;
        }

        pos++;

        if(root.left == null && root.right == null) {
            return true;
        }

        if(root.left == null) {
            return dfs(root.right, v);
        }
        if(root.right == null) {
            return dfs(root.left, v);
        }

        if(root.left.val == v[pos]) {
            return dfs(root.left, v) && dfs(root.right, v);
        } else {
            ans.add(root.val);
            return dfs(root.right, v) && dfs(root.left, v);
        }
    }
}
