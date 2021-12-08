package solution;

import annotations.Problem;
import data_structure.ListNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Middle of the Linked List",
        difficulty = QDifficulty.EASY,
        tag = QTag.LINKED_LIST,
        url = "https://leetcode.com/problems/middle-of-the-linked-list/"
)
public class Q876 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }
}
