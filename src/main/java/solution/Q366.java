package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Find Leaves of Binary Tree",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/find-leaves-of-binary-tree/"
)
public class Q366 {
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();

        collectLeaves(root, ans);
        return ans;
    }

    private int collectLeaves(TreeNode root, List<List<Integer>> ans) {
        if(root == null) {
            return -1;
        }

        int l = collectLeaves(root.left, ans);
        int r = collectLeaves(root.right, ans);

        int h = Math.max(l, r) + 1;
        if(ans.size() < h + 1) {
            ans.add(new ArrayList<>());
        }

        ans.get(h).add(root.val);

        return h;
    }
}
