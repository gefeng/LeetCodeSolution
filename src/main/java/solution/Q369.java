package solution;

import annotations.Problem;
import data_structure.ListNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Plus One Linked List",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.LINKED_LIST,
        url = "https://leetcode.com/problems/plus-one-linked-list/"
)
public class Q369 {
    /*
    * reverse -> plus one -> reverse is trivial
    * solve it in another way
    * */
    public ListNode plusOne(ListNode head) {
        if(head == null)
            return head;

        ListNode rightMost = head;
        ListNode curr = head;

        while(curr != null) {
            if(curr.val != 9)
                rightMost = curr;
            curr = curr.next;
        }

        int carry = (rightMost.val + 1) / 10;
        rightMost.val = (rightMost.val + 1) % 10;
        curr = rightMost.next;
        while(curr != null) {
            curr.val = 0;
            curr = curr.next;
        }

        if(carry == 1) {
            ListNode node = new ListNode(1);
            node.next = head;
            head = node;
        }
        return head;
    }
}
