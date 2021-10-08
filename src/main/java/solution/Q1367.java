package solution;

import annotations.Problem;
import data_structure.ListNode;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Linked List in Binary Tree",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/linked-list-in-binary-tree/"
)
public class Q1367 {
    /**
     * Time:  O(N * M)
     * Space: O(N)
     * */
    public boolean isSubPath(ListNode head, TreeNode root) {
        return dfs(root, head);
    }

    private boolean dfs(TreeNode root, ListNode head) {
        if(root == null) {
            return false;
        }

        if(solve(head, root)) {
            return true;
        }

        return dfs(root.left, head) || dfs(root.right, head);
    }

    private boolean solve(ListNode cur, TreeNode root) {
        if(cur == null) {
            return true;
        }
        if(root == null) {
            return false;
        }

        if(cur.val != root.val) {
            return false;
        }

        boolean l = solve(cur.next, root.left);
        boolean r = solve(cur.next, root.right);

        return l || r;
    }
}
