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
    public TreeNode bstFromPreorder(int[] preorder) {
        Queue<Integer> queue = new ArrayDeque<>();
        for(int val : preorder)
            queue.offer(val);
        return recover(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE, queue);
    }

    private TreeNode recover(int[] preorder, int floor, int ceiling, Queue<Integer> queue) {
        if(queue.isEmpty())
            return null;

        int val = queue.peek();
        if(val <= floor || val >= ceiling)
            return null;

        queue.poll();
        TreeNode node = new TreeNode(val);
        node.left = recover(preorder, floor, node.val, queue);
        node.right = recover(preorder, node.val, ceiling, queue);
        return node;
    }
}
