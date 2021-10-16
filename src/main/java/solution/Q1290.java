package solution;

import annotations.Problem;
import data_structure.ListNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Convert Binary Number in a Linked List to Integer",
        difficulty = QDifficulty.EASY,
        tag = QTag.LINKED_LIST,
        url = "https://leetcode.com/problems/convert-binary-number-in-a-linked-list-to-integer/"
)
public class Q1290 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
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
