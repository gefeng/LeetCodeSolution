package solution;

import annotations.Problem;
import dataStructure.ListNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Reverse Linked List",
        difficulty = QDifficulty.EASY,
        tag = QTag.LINKED_LIST
)
public class Q206 {
    public ListNode reverseList(ListNode head) {
        ListNode curr = head;
        ListNode prev = null;
        ListNode next = null;

        while(curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        head = prev;
        return head;
    }

    public ListNode reverseListRecursive(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }

        ListNode p = reverseList(head.next);
        head.next.next = head;
        head.next = null;

        return p;
    }
}
