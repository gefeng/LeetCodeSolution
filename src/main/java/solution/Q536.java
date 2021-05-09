package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Construct Binary Tree from String",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/construct-binary-tree-from-string/"
)
public class Q536 {
    int idx = 0;
    public TreeNode str2tree(String s) {
        return preorderBuild(s);
    }

    private TreeNode preorderBuild(String s) {
        if(idx == s.length() || s.charAt(idx) == ')') {
            return null;
        }

        if(s.charAt(idx) == '(') {
            idx++;
        }

        // parse sign
        int sign = 1;
        if(s.charAt(idx) == '-') {
            sign = -1;
            idx++;
        }

        // parse number
        int num = 0;
        while(idx < s.length() && Character.isDigit(s.charAt(idx))) {
            num = num * 10 + s.charAt(idx++) - '0';
        }

        TreeNode root = new TreeNode(num * sign);

        root.left = preorderBuild(s);
        root.right = preorderBuild(s);

        idx++;
        return root;
    }
}
