package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Construct String from Binary Tree",
        difficulty = QDifficulty.EASY,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/construct-string-from-binary-tree/"
)
public class Q606 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public String tree2str(TreeNode t) {
        if(t == null) return "";

        String res = t.val + "";
        String l = tree2str(t.left);
        String r = tree2str(t.right);

        if(l.isEmpty() && r.isEmpty()) {
            return res;
        }
        if(r.isEmpty()) {
            return res + "(" + l + ")";
        }

        return res + "(" + l + ")" + "(" + r + ")";
    }
}
