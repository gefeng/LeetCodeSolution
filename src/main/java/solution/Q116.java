package solution;

import annotations.Problem;
import data_structure.Node;
import enums.QDifficulty;
import enums.QTag;

import java.util.LinkedList;
import java.util.Queue;

@Problem(
        title = "Populating Next Right Pointers in Each Node",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/populating-next-right-pointers-in-each-node/"
)
public class Q116 {
    /*这题解法很多，可以做到time O(n) + space O(1)*/
    public Node connect(Node root) {
        if(root == null)
            return root;

        if(root.left != null) {
            root.left.next = root.right;
            if(root.next != null)
                root.right.next = root.next.left;
        }

        connect(root.left);
        connect(root.right);

        return root;
    }

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

                if(currNode.left != null) {
                    queue.offer(currNode.left);
                    queue.offer(currNode.right);
                }
            }
        }
        return root;
    }

    public Node connectInterative(Node root) {
        if(root == null)
            return root;

        Node leftMost = root;
        while(leftMost.left != null) {
            Node head = leftMost;
            while(head != null) {
                head.left.next = head.right;
                if(head.next != null)
                    head.right.next = head.next.left;
                head = head.next;
            }
            leftMost = leftMost.left;
        }

        return root;
    }
}
