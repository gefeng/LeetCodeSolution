package solution;

import annotations.Problem;
import data_structure.ListNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Remove Linked List Elements",
        difficulty = QDifficulty.EASY,
        tag = QTag.LINKED_LIST,
        url = "https://leetcode.com/problems/remove-linked-list-elements/"
)
public class Q203 {
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode cNode = dummyHead;

        while(cNode.next != null) {
            if(cNode.next.val == val) {
                cNode.next = cNode.next.next;
            } else
                cNode = cNode.next;
        }

        return dummyHead.next;
    }
}
