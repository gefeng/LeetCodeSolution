package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Split BST",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BINARY_SEARCH_TREE,
        url = "https://leetcode.com/problems/split-bst/"
)
public class Q776 {
    /**
     * Time:  O(H)
     * space: O(H)
     * */
    public TreeNode[] splitBST(TreeNode root, int target) {
        if(root == null) return new TreeNode[] {null, null};

        if(root.val > target) {
            TreeNode[] res = splitBST(root.left, target);
            root.left = res[1];
            res[1] = root;
            return res;
        } else {
            TreeNode[] res = splitBST(root.right, target);
            root.right = res[0];
            res[0] = root;
            return res;
        }
    }
}
