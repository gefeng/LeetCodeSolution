package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

@Problem(
        title = "Serialize and Deserialize Binary Tree",
        difficulty = QDifficulty.HARD,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/serialize-and-deserialize-binary-tree/"
)
public class Q297 {
    // Encodes a tree to a single string.
    // i.e [1,2,3,4,5] or [1,2,null,3,4,5]
    public String serializeBFS(TreeNode root) {
        if(root == null)
            return "[]";

        Queue<TreeNode> queue = new LinkedList<>();
        StringBuilder sb = new StringBuilder();

        sb.append('[');
        queue.offer(root);

        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                if(curr == null)
                    sb.append("null,");
                else {
                    sb.append(curr.val + ",");
                    queue.offer(curr.left);
                    queue.offer(curr.right);
                }
            }
        }

        while(sb.charAt(sb.length() - 1) < '0' || sb.charAt(sb.length() - 1) > '9')
            sb.deleteCharAt(sb.length() - 1);
        sb.append(']');

        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserializeBFS(String data) {
        if(data.equals("[]"))
            return null;

        ArrayList<TreeNode> nodes = new ArrayList<>();
        for(String s : data.substring(1, data.length() - 1).split(","))
            nodes.add(s.equals("null") ? null : new TreeNode(Integer.valueOf(s)));

        int rootIndex = 0;
        int childIndex = 1;
        while(childIndex < nodes.size()) {
            if(nodes.get(rootIndex) != null) {
                nodes.get(rootIndex).left = nodes.get(childIndex++);
                if (childIndex < nodes.size())
                    nodes.get(rootIndex++).right = nodes.get(childIndex++);
            } else
                rootIndex++;
        }

        return nodes.get(0);
    }

    private void serializeRecursive(TreeNode node, StringBuilder sb) {
        if(node == null) {
            sb.append("null,");
            return;
        }
        sb.append(node.val + ",");
        serializeRecursive(node.left, sb);
        serializeRecursive(node.right, sb);
    }
    public String serializeDFS(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializeRecursive(root, sb);
        return sb.toString();
    }

    private TreeNode deserializeRecursive(Queue<TreeNode> queue) {
        if(queue.peek() == null) {
            queue.poll();
            return null;
        }

        TreeNode node = queue.poll();
        node.left = deserializeRecursive(queue);
        node.right = deserializeRecursive(queue);

        return node;
    }
    public TreeNode deserializeDFS(String data) {
        Queue<TreeNode> queue = new LinkedList<>();
        for(String s : data.split(","))
            queue.offer(s.equals("null") ? null : new TreeNode(Integer.valueOf(s)));

        TreeNode root = deserializeRecursive(queue);
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
