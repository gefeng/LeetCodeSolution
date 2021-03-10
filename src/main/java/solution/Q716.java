package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

@Problem(
        title = "Max Stack",
        difficulty = QDifficulty.EASY,
        tag = QTag.DESIGN,
        url = "https://leetcode.com/problems/max-stack/"
)
public class Q716 {
    // Implement the optimized solution using TreeMap
    /*
        doubly linked list
        [2,1,5,1,4,3,5,1,2]
        [2,2]-[1,2]-[5,5]-[1,5]-[4,5]-[3,5]-[5,5]-[1,5]-[2,5]

        tree map
        5, [[5,5],[5,5]
    */
    private class Node {
        int val;
        Node prev;
        Node next;
        public Node(int val) {
            this.val = val;
            prev = null;
            next = null;
        }
    }

    private class DLinkedList {
        Node dummyHead;
        Node dummyTail;
        public DLinkedList() {
            dummyHead = new Node(0);
            dummyTail = new Node(0);
            dummyHead.next = dummyTail;
            dummyTail.prev = dummyHead;
        }

        private void link(Node prev, Node next, Node x) {
            x.next = next;
            x.prev = prev;
            prev.next = x;
            next.prev = x;
        }

        private void unlink(Node x) {
            Node prev = x.prev;
            Node next = x.next;
            prev.next = next;
            next.prev = prev;
        }

        public void addLast(Node x) {
            link(dummyTail.prev, dummyTail, x);
        }

        public Node removeLast() {
            Node x = dummyTail.prev;
            unlink(x);
            return x;
        }

        public void remove(Node x) {
            unlink(x);
        }

        public Node getLast() {
            return dummyTail.prev;
        }

        public boolean isEmpty() {
            return dummyHead.next == dummyTail;
        }
    }

    DLinkedList nodes;
    TreeMap<Integer, List<Node>> map;
    public Q716() {
        nodes = new DLinkedList();
        map = new TreeMap<>();
    }

    public void push(int x) {
        // add last
        Node n = new Node(x);
        nodes.addLast(n);
        if(!map.containsKey(x))
            map.put(x, new ArrayList<>());
        map.get(x).add(n);
    }

    public int pop() {
        // remove last
        Node n = nodes.removeLast();
        List<Node> l = map.get(n.val);
        l.remove(n);
        if(l.size() == 0)
            map.remove(n.val);
        return n.val;
    }

    public int top() {
        return nodes.getLast().val;
    }

    public int peekMax() {
        return map.lastKey();
    }

    public int popMax() {
        // get max
        int max = peekMax();
        List<Node> l = map.get(max);
        Node n = l.remove(l.size() - 1);
        nodes.remove(n);
        if(l.isEmpty())
            map.remove(max);
        return max;
    }
}
