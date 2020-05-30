package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Binary Tree Zigzag Level Order Traversal",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/"
)
public class Q103 {
    public List<List<Integer>> zigzagLevelOrderBFS(TreeNode root) {
        List<List<Integer>> zigzagValues = new ArrayList<>();
        if(root == null)
            return zigzagValues;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean leftToRight = true;
        while(!queue.isEmpty()) {
            List<Integer> values = new LinkedList<>();
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                if(leftToRight)
                    values.add(curr.val);
                else
                    values.add(0, curr.val);

                if(curr.left != null)
                    queue.offer(curr.left);
                if(curr.right != null)
                    queue.offer(curr.right);
            }
            zigzagValues.add(values);
            leftToRight = !leftToRight;
        }

        return zigzagValues;
    }

    private void dfs(TreeNode node, int level, List<List<Integer>> zigzagValues) {
        if(node == null)
            return;
        if(zigzagValues.size() < level) {
            List<Integer> values = new LinkedList<>();
            zigzagValues.add(values);
        }

        if(level % 2 != 0) {
            zigzagValues.get(level - 1).add(node.val);
        } else
            zigzagValues.get(level - 1).add(0, node.val);

        dfs(node.left, level + 1, zigzagValues);
        dfs(node.right, level + 1, zigzagValues);
    }
    public List<List<Integer>> zigzagLevelOrderDFS(TreeNode root) {
        List<List<Integer>> zigzagValues = new ArrayList<>();
        dfs(root, 1, zigzagValues);
        return zigzagValues;
    }
}
