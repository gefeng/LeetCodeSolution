package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Insert into a Sorted Circular Linked List",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.LINKED_LIST,
        url = "https://leetcode.com/problems/insert-into-a-sorted-circular-linked-list/"
)
public class Q708 {
    class Node {
        public int val;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _next) {
            val = _val;
            next = _next;
        }
    }
    public Node insert(Node head, int insertVal) {
        if(head == null) {
            Node n = new Node(insertVal);
            n.next = n;
            return n;
        }

        Node curr = head;
        Node max = null;
        do {
            if(insertVal >= curr.val && insertVal <= curr.next.val) {
                Node n = new Node(insertVal, curr.next);
                curr.next = n;
                return head;
            }
            if(curr.val > curr.next.val)
                max = curr;

            curr = curr.next;
        } while(curr != head);

        if(max == null) { // all nodes have the same value;
            Node n = new Node(insertVal, head.next);
            head.next = n;
        }
        else { // value is either less than or greater then all values
            Node n = new Node(insertVal, max.next);
            max.next = n;
        }
        return head;
    }
}
