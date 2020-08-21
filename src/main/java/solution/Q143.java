package solution;

import annotations.Problem;
import data_structure.ListNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Reorder List",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.LINKED_LIST,
        url = "https://leetcode.com/problems/reorder-list/"
)
public class Q143 {
    public void reorderList(ListNode head) {
        if(head == null)
            return;
        // find mid
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // reverse second half
        ListNode prev = null;
        ListNode curr = slow;
        while(curr != null) {
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }

        // merge two linked list
        ListNode p1 = head;
        ListNode p2 = prev;
        while(p2.next != null) {
            ListNode temp = p1.next;
            p1.next = p2;
            p1 = temp;

            temp = p2.next;
            p2.next = p1;
            p2 = temp;
        }
    }
}
