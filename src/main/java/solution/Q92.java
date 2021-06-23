package solution;

import annotations.Problem;
import data_structure.ListNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Reverse Linked List II",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.LINKED_LIST,
        url = "https://leetcode.com/problems/reverse-linked-list-ii/"
)
public class Q92 {
    /*
    * Consider to use dummy head for linked list problem in which the head could be modified.
    * */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(0, head);
        ListNode curr = dummy;
        ListNode prev = null;
        ListNode l = dummy;
        int i = 0;

        while(i != left) {
            l = curr;
            curr = curr.next;
            i++;
        }

        while(i <= right) {
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
            i++;
        }

        l.next.next = curr;
        l.next = prev;

        return dummy.next;
    }
}
