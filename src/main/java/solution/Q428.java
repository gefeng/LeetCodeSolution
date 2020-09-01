package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Problem(
        title = "Serialize and Deserialize N-ary Tree",
        difficulty = QDifficulty.HARD,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/serialize-and-deserialize-n-ary-tree/"
)
public class Q428 {
    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    // Encodes a tree to a single string.
    public String serialize(Node root) {
        StringBuilder sb = new StringBuilder();
        if(root == null)
            return sb.toString();

        sb.append(root.val);
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                Node curr = queue.poll();
                sb.append(',').append("null");
                for (Node child : curr.children) {
                    sb.append(',').append(child.val);
                    queue.offer(child);
                }
            }
        }

        int nullIndex = sb.lastIndexOf(",null");
        while(nullIndex != -1 && nullIndex == sb.length() - 5) {
            sb.delete(nullIndex, sb.length());
            nullIndex = sb.lastIndexOf(",null");
        }

        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if(data.isEmpty())
            return null;

        String[] nodes = data.split(",");
        int nodeIndex = 0;
        Queue<Node> queue = new LinkedList<>();
        Node root = new Node(Integer.valueOf(nodes[nodeIndex++]), new ArrayList<>());
        queue.offer(root);
        while(!queue.isEmpty() && nodeIndex < nodes.length) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                Node curr = queue.poll();
                nodeIndex++;
                while(nodeIndex < nodes.length && !nodes[nodeIndex].equals("null")) {
                    Node child = new Node(Integer.valueOf(nodes[nodeIndex++]), new ArrayList<>());
                    curr.children.add(child);
                    queue.offer(child);
                }
            }
        }
        return root;
    }
}
