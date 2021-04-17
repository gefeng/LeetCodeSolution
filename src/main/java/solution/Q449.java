package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Queue;

@Problem(
        title = "Serialize and Deserialize BST",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/serialize-and-deserialize-bst/"
)
public class Q449 {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        preorder(root, sb);
        return sb.toString();
    }

    private void preorder(TreeNode root, StringBuilder sb) {
        if(root == null)
            return;

        sb.append(root.val).append(" ");

        preorder(root.left, sb);
        preorder(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data.isEmpty())
            return null;

        String[] values = data.split("\\s");
        Queue<String> queue = new ArrayDeque<>();
        for(String val : values) {
            queue.offer(val);
        }
        return build(queue, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private TreeNode build(Queue<String> queue, int floor, int ceiling) {
        if(queue.isEmpty())
            return null;

        int val = Integer.parseInt(queue.peek());
        if(val <= floor || val >= ceiling)
            return null;

        queue.poll();
        TreeNode node = new TreeNode(val);
        node.left = build(queue, floor, val);
        node.right = build(queue, val, ceiling);
        return node;
    }
}
