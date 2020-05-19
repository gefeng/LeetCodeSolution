package solution;

import annotations.Problem;
import dataStructure.ListNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Remove Linked List Elements",
        difficulty = QDifficulty.EASY,
        tag = QTag.LINKED_LIST
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
