package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

@Problem(
        title = "Two Sum BSTs",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BINARY_SEARCH_TREE,
        url = "https://leetcode.com/problems/two-sum-bsts/"
)
public class Q1214 {
    /**
     * Time:  O(N + M)
     * Space: O(N)
     * */
    public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
        Set<Integer> set = new HashSet<>();
        dfs1(root1, set);

        return dfs2(root2, set, target);
    }

    private void dfs1(TreeNode root, Set<Integer> set) {
        if(root == null) {
            return;
        }

        set.add(root.val);
        dfs1(root.left, set);
        dfs1(root.right, set);
    }

    private boolean dfs2(TreeNode root, Set<Integer> set, int t) {
        if(root == null) {
            return false;
        }

        return set.contains(t - root.val) || dfs2(root.left, set, t) || dfs2(root.right, set, t);
    }
}
