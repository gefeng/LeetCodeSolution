package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;

@Problem(
        title = "Copy List with Random Pointer",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.LINKED_LIST,
        url = "https://leetcode.com/problems/copy-list-with-random-pointer/"
)
public class Q138 {
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
    public Node copyRandomList(Node head) {
        HashMap<Node, Node> map = new HashMap<>();
        Node dummyHead = new Node(0);
        Node p1 = head;
        Node p2 = dummyHead;

        while(p1 != null) {
            Node copy = map.getOrDefault(p1, new Node(p1.val));
            map.put(p1, copy);
            p2.next = copy;

            if(p1.random != null) {
                Node rand = map.getOrDefault(p1.random, new Node(p1.random.val));
                map.put(p1.random, rand);
                p2.next.random = rand;
            }

            p1 = p1.next;
            p2 = p2.next;
        }

        return dummyHead.next;
    }

    /*This approach requires no extra space (the map)*/
    public Node copyRandomListConstantSpace(Node head) {
        if(head == null)
            return null;

        // create copy and attach to original list
        Node curr = head;
        while(curr != null) {
            Node copy = new Node(curr.val);
            copy.next = curr.next;
            curr.next = copy;
            curr = curr.next.next;
        }

        // set random pointer for copies
        curr = head;
        while(curr != null) {
            if(curr.random != null)
                curr.next.random = curr.random.next;
            curr = curr.next.next;
        }

        Node dummyHead = new Node(0);
        Node copy = dummyHead;
        curr = head;
        while(curr != null) {
            copy.next = curr.next;
            copy = copy.next;
            curr.next = curr.next.next;
            curr = curr.next;
        }

        return dummyHead.next;
    }
}
