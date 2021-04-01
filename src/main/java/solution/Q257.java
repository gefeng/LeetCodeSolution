package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Binary Tree Paths",
        difficulty = QDifficulty.EASY,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/binary-tree-paths/"
)
public class Q257 {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> ans = new ArrayList<>();
        preorder(root, new StringBuilder(), ans);
        return ans;
    }

    private void preorder(TreeNode root, StringBuilder currPath, List<String> ans) {
        if(root == null)
            return;

        int len = currPath.length();

        if(currPath.length() == 0)
            currPath.append(root.val);
        else
            currPath.append("->").append(root.val);

        if(root.left == null && root.right == null)
            ans.add(currPath.toString());

        preorder(root.left, currPath, ans);
        preorder(root.right, currPath, ans);

        currPath.delete(len, currPath.length());
    }
}
