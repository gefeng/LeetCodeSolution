package solution;

import annotations.Problem;
import data_structure.ListNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Linked List Cycle",
        difficulty = QDifficulty.EASY,
        tag = QTag.LINKED_LIST,
        url = "https://leetcode.com/problems/linked-list-cycle/"
)
public class Q141 {
    public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null)
            return false;

        ListNode slow = head;
        ListNode fast = head.next;

        while(slow != fast) {
            if(fast == null || fast.next == null)
                return false;

            slow = slow.next;
            fast = fast.next.next;
        }

        return true;
    }
}
