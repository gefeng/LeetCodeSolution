package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Count Univalue Subtrees",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/count-univalue-subtrees/"
)
public class Q250 {
    /*特别小心判断时候的顺序，先判断subtree是unival再比较父节点和子节点的值*/
    int count = 0;
    private boolean isUnivalSubtree(TreeNode node) {
        if(node.left == null && node.right == null) {
            count++;
            return true;
        }

        boolean isUnival = true;
        if(node.left != null)
            isUnival = isUnivalSubtree(node.left) && (node.val == node.left.val);
        if(node.right != null)
            isUnival = isUnivalSubtree(node.right) && isUnival && (node.val == node.right.val);

        if(isUnival)
            count++;

        return isUnival;
    }
    public int countUnivalSubtrees(TreeNode root) {
        if(root == null)
            return 0;
        isUnivalSubtree(root);
        return count;
    }
}
