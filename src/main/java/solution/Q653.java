package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashSet;

@Problem(
        title = "Two Sum IV - Input is a BST",
        difficulty = QDifficulty.EASY,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/two-sum-iv-input-is-a-bst/"
)
public class Q653 {
    public boolean findTarget(TreeNode root, int k) {
        return traverse(root, k, new HashSet<>());
    }

    private boolean traverse(TreeNode root, int k, HashSet<Integer> set) {
        if(root == null)
            return false;

        if(set.contains(k - root.val))
            return true;
        set.add(root.val);

        return traverse(root.left, k, set) || traverse(root.right, k, set);
    }
}
