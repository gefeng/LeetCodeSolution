package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Problem(
        title = "Delete Nodes And Return Forest",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/delete-nodes-and-return-forest/"
)
public class Q1110 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    List<TreeNode> ans = new ArrayList<>();
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        Set<Integer> set = new HashSet<>();
        for(int x : to_delete) {
            set.add(x);
        }

        dfs(root, set, true);

        return ans;
    }

    private TreeNode dfs(TreeNode root, Set<Integer> set, boolean isRoot) {
        if(root == null) {
            return null;
        }

        boolean deleted = set.contains(root.val);

        if(isRoot && !deleted) {
            ans.add(root);
        }

        root.left = dfs(root.left, set, deleted);
        root.right = dfs(root.right, set, deleted);

        return deleted ? null : root;
    }
}
