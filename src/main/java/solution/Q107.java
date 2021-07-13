package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Binary Tree Level Order Traversal II",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/binary-tree-level-order-traversal-ii/"
)
public class Q107 {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if(root == null) {
            return new ArrayList<>();
        }

        List<List<Integer>> ans = new ArrayList<>();
        Deque<List<Integer>> stack = new ArrayDeque<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while(!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();

            for(int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                level.add(curr.val);

                if(curr.left != null) {
                    queue.offer(curr.left);
                }

                if(curr.right != null) {
                    queue.offer(curr.right);
                }
            }

            stack.push(level);
        }

        while(!stack.isEmpty()) {
            ans.add(stack.pop());
        }
        return ans;
    }
}
