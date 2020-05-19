package solution;

import annotations.Problem;
import dataStructure.ListNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Convert Binary Number in a Linked List to Integer",
        difficulty = QDifficulty.EASY,
        tag = QTag.LINKED_LIST
)
public class Q1290 {
    public int getDecimalValue(ListNode head) {
        int retVal = 0;
        ListNode cNode = head;

        while(cNode != null) {
            retVal <<= 1;
            retVal |= cNode.val;
            cNode = cNode.next;
        }

        return retVal;
    }
}
