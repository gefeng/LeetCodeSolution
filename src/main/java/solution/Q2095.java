package solution;

import annotations.Problem;
import data_structure.ListNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Delete the Middle Node of a Linked List",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.LINKED_LIST,
        url = "https://leetcode.com/problems/delete-the-middle-node-of-a-linked-list/"
)
public class Q2095 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public ListNode deleteMiddle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        ListNode dummy = new ListNode(0, head);

        ListNode pre = dummy;
        while(fast != null && fast.next != null) {
            fast = fast.next.next;

            pre = slow;
            slow = slow.next;
        }

        pre.next = slow.next;

        return dummy.next;
    }
}
