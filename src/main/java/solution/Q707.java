package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Design Linked List",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.LINKED_LIST,
        url = "https://leetcode.com/problems/design-linked-list/"
)
public class Q707 {
    class Node {
        int val;
        Node next;
        public Node(int val) { this.val = val; next = null; }
    }
    Node head;
    Node tail;
    int size;
    /** Initialize your data structure here. */
    public Q707() {
        head = null;
        tail = null;
        size = 0;
    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if(index < size && index >= 0) {
            Node curr = head;
            while(index > 0) {
                curr = curr.next;
                index--;
            }
            return curr.val;
        }
        return -1;
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        if(size == 0)
            addAtTail(val);
        else {
            Node newHead = new Node(val);
            newHead.next = head;
            head = newHead;
            size++;
        }
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        Node newTail = new Node(val);
        if(tail != null) {
            tail.next = newTail;
        } else {
            head = newTail;
        }
        tail = newTail;
        size++;
    }

    /** Add a node of value val before the index-th node in the linked list.
     * If index equals to the length of linked list, the node will be appended to the end of linked list.
     * If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if(index > size || index < 0)
            return;
        if(index == size)
            addAtTail(val);
        else {
            Node prev = null;
            Node curr = head;
            while(index > 0) {
                prev = curr;
                curr = curr.next;
                index--;
            }
            if(prev == null)
                addAtHead(val);
            else {
                prev.next = new Node(val);
                prev.next.next = curr;
                size++;
            }
        }
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if(index < size) {
            Node prev = null;
            Node curr = head;
            while(index > 0) {
                prev = curr;
                curr = curr.next;
                index--;
            }
            if(prev == null) {
                head = head.next;
            }
            else {
                prev.next = curr.next;
                if(curr == tail)
                    tail = prev;
            }
            size--;
        }
    }
}
