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
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode leftNode = null;
        ListNode rightNode = null;
        ListNode leftPrevNode = null;
        ListNode rightNextNode = null;
        ListNode prev = null;
        ListNode curr = head;
        int n = 0;
        while(curr != null) {
            n++;
            if(left == n) {
                leftNode = curr;
                leftPrevNode = prev;
            }
            if(right == n) {
                rightNode = curr;
                rightNextNode = curr.next;
            }

            prev = curr;
            curr = curr.next;
        }

        prev = null;
        curr = leftNode;
        rightNode.next = null;
        while(curr != null) {
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }

        if(leftPrevNode != null)
            leftPrevNode.next = rightNode;
        leftNode.next = rightNextNode;

        return leftPrevNode == null ? rightNode : head;
    }
}
