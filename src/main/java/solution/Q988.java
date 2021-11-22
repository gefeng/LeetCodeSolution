package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Smallest String Starting From Leaf",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/smallest-string-starting-from-leaf/"
)
public class Q988 {
    /**
     * Time:  O(N ^ 2)
     * Space: O(N)
     * */
    String ans = "";
    public String smallestFromLeaf(TreeNode root) {
        dfs(root, new StringBuilder());
        return ans;
    }

    private void dfs(TreeNode root, StringBuilder path) {
        if(root == null) {
            return;
        }

        path.append((char)(root.val + 'a'));

        if(root.left == null && root.right == null) {
            String cand = new StringBuilder(path).reverse().toString();

            if(ans.isEmpty() || cand.compareTo(ans) < 0) {
                ans = cand;
            }
        } else {
            dfs(root.left, path);
            dfs(root.right, path);
        }

        path.deleteCharAt(path.length() - 1);
    }
}
