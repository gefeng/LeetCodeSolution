package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Problem(
        title = "All Possible Full Binary Trees",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/all-possible-full-binary-trees/"
)
public class Q894 {
    /**
     * Time:  O(2 ^ N)
     * Space: O(2 ^ N)
     * */
    public List<TreeNode> allPossibleFBT(int n) {
        return gen(n);
    }

    private List<TreeNode> gen(int n) {
        if(n == 1) {
            return Arrays.asList(new TreeNode(0));
        }
        if(n == 2) {
            return new ArrayList<>();
        }

        List<TreeNode> ret = new ArrayList<>();
        for(int i = 1; i < n - 1; i += 2) {
            List<TreeNode> l = gen(i);
            List<TreeNode> r = gen(n - 1 - i);

            for(TreeNode x : l) {
                for(TreeNode y : r) {
                    TreeNode root = new TreeNode(0);
                    root.left = x;
                    root.right = y;
                    ret.add(root);
                }
            }
        }

        return ret;
    }
}
