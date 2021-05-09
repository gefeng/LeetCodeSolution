package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Queue;

@Problem(
        title = "Minimum Depth of Binary Tree",
        difficulty = QDifficulty.EASY,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/minimum-depth-of-binary-tree/"
)
public class Q111 {
    /*
    * bfs is better for this problem
    * */
    public int minDepth(TreeNode root) {
        return postorder(root);
    }

    private int postorder(TreeNode root) {
        if(root == null) {
            return 0;
        }

        int lmin = minDepth(root.left);
        int rmin = minDepth(root.right);

        return (lmin == 0 || rmin == 0) ? lmin + rmin + 1: Math.min(lmin, rmin) + 1;
    }

    private int bfs(TreeNode root) {
        if(root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        int depth = 1;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                if(curr.left == null && curr.right == null) {
                    return depth;
                }
                if(curr.left != null) {
                    queue.offer(curr.left);
                }
                if(curr.right != null) {
                    queue.offer(curr.right);
                }
            }
            depth++;
        }

        return -1;
    }
}
