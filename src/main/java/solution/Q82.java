package solution;

import annotations.Problem;
import data_structure.ListNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Remove Duplicates from Sorted List II",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.LINKED_LIST,
        url = "https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/"
)
public class Q82 {
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null)
            return head;

        ListNode dummyHead = new ListNode(0, head);
        ListNode prev = dummyHead;
        ListNode curr = head;

        while(curr != null) {
            if(curr.next == null || curr.val != curr.next.val) {
                if(prev.next != curr) {
                    prev.next = curr.next;
                } else {
                    prev = curr;
                }
            }

            curr = curr.next;
        }

        return dummyHead.next;
    }
}
