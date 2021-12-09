package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Leaf-Similar Trees",
        difficulty = QDifficulty.EASY,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/leaf-similar-trees/"
)
public class Q872 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();

        dfs(root1, l1);
        dfs(root2, l2);

        return l1.equals(l2);
    }

    private void dfs(TreeNode root, List<Integer> l) {
        if(root == null) {
            return;
        }

        if(root.left == null && root.right == null) {
            l.add(root.val);
        }

        dfs(root.left, l);
        dfs(root.right, l);
    }
}
