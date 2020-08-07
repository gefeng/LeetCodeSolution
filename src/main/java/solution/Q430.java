package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Flatten a Multilevel Doubly Linked List",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.LINKED_LIST,
        url = "https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/"
)
public class Q430 {
    class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
    }
    public Node flatten(Node head) {
        if(head == null)
            return null;

        Node childHead = flatten(head.child);
        Node childTail = childHead;
        Node temp = head.next;
        if(childHead != null) {
            while(childTail.next != null)
                childTail = childTail.next;

            childHead.prev = head;
            childTail.next = temp;
            head.next = childHead;

            if(temp != null)
                temp.prev = childTail;

            head.child = null;
        }

        flatten(temp);

        return head;
    }
}
