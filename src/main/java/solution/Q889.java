package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Construct Binary Tree from Preorder and Postorder Traversal",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/"
)
public class Q889 {
    /**
     * Time:  O(N ^ 2)
     * Space: O(N)
     * */
    int[] pre;
    int[] pos;
    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        this.pre = preorder;
        this.pos = postorder;
        int n = pre.length;
        return dfs(0, n - 1, 0, n - 1);
    }

    private TreeNode dfs(int l1, int r1, int l2, int r2) {
        if(l1 > r1) {
            return null;
        }

        TreeNode root = new TreeNode(pre[l1]);

        if(l1 == r1) {
            return root;
        }

        int mid = 0;
        for(int i = l2; i <= r2; i++) {
            if(pre[l1 + 1] == pos[i]) {
                mid = i;
                break;
            }
        }

        root.left = dfs(l1 + 1, l1 + 1 + mid - l2, l2, mid);
        root.right = dfs(l1 + 1 + mid - l2 + 1, r1, mid + 1, r2 - 1);

        return root;
    }
}
