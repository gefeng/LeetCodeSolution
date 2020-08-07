package solution;

import annotations.Problem;
import data_structure.ListNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Rotate List",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.LINKED_LIST,
        url = "https://leetcode.com/problems/rotate-list/"
)
public class Q61 {
    /**
     * Reverse first n - k nodes, then reverse the rest of the k nodes
     * Reverse the whole list
     */
    public ListNode rotateRightReverse(ListNode head, int k) {
        if(head == null || head.next == null)
            return head;

        ListNode curr = head;
        int size = 0;

        while(curr != null) {
            curr = curr.next;
            size++;
        }

        k = k % size;

        ListNode prev = null;
        curr = head;
        for(int i = 0; i < size - k; i++) {
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }

        ListNode tail = head;
        head = prev;
        prev = null;

        for(int i = 0; i < k; i++) {
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }

        tail.next = prev;

        prev = null;
        curr = head;
        while(curr != null) {
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }

        return prev;
    }

    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next == null)
            return head;

        ListNode curr = head;
        int size = 1;
        while(curr.next != null) {
            curr = curr.next;
            size++;
        }
        curr.next = head;
        k = k % size;
        ListNode tail = null;
        for(int i = 0; i < size - k; i++) {
            tail = head;
            head = head.next;
        }

        tail.next = null;
        return head;
    }
}
