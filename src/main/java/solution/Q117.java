package solution;

import annotations.Problem;
import data_structure.Node;
import enums.QDifficulty;
import enums.QTag;

import java.util.LinkedList;
import java.util.Queue;

@Problem(
        title = "Populating Next Right Pointers in Each Node II",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/"
)
public class Q117 {
    public Node connectBFS(Node root) {
        if(root == null)
            return root;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                Node currNode = queue.poll();
                if(i < size - 1)
                    currNode.next = queue.peek();
                if(currNode.left != null)
                    queue.offer(currNode.left);
                if(currNode.right != null)
                    queue.offer(currNode.right);
            }
        }
        return root;
    }

    /*注意先处理左子节点，再处理右子节点*/
    public Node connectIterative(Node root) {
        if(root == null)
            return root;

        Node leftMost = root;
        while(leftMost != null) {
            Node currRoot = leftMost;
            Node prev = null;
            while(currRoot != null) {
                // left child node
                if(prev != null) {
                    prev.next = currRoot.left;
                    prev = prev.next == null ? prev : prev.next;
                } else {
                    prev = currRoot.left;
                    leftMost = prev;
                }

                // right child node
                if(prev != null) {
                    prev.next = currRoot.right;
                    prev = prev.next == null ? prev : prev.next;
                } else {
                    prev = currRoot.right;
                    leftMost = prev;
                }
                currRoot = currRoot.next;
            }
        }
        return root;
    }
}
