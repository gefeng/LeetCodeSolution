package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

@Problem(
        title = "Construct Binary Search Tree from Preorder Traversal",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/"
)
public class Q1008 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    int[] v;
    int pos;
    int len;
    public TreeNode bstFromPreorder(int[] preorder) {
        v = preorder;
        pos = 0;
        len = v.length;

        return dfs(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private TreeNode dfs(int lb, int ub) {
        if(pos == len) {
            return null;
        }

        int val = v[pos];
        if(val < lb || val > ub) {
            return null;
        }

        pos++;

        TreeNode root = new TreeNode(val);
        root.left = dfs(lb, val);
        root.right = dfs(val, ub);

        return root;
    }
}
