package solution;

import annotations.Problem;
import data_structure.ListNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Add Two Numbers II",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.LINKED_LIST,
        url = "https://leetcode.com/problems/add-two-numbers-ii/"
)
public class Q445 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        int len1 = 0;
        int len2 = 0;
        ListNode p1 = l1;
        ListNode p2 = l2;
        while(p1 != null) {
            len1++;
            p1 = p1.next;
        }
        while(p2 != null) {
            len2++;
            p2 = p2.next;
        }

        p1 = l1;
        p2 = l2;
        while(p1 != null && p2 != null) {
            ListNode sumNode;
            if(len1 > len2) {
                sumNode = new ListNode(p1.val);
                p1 = p1.next;
                len1--;
            } else if(len1 < len2) {
                sumNode = new ListNode(p2.val);
                p2 = p2.next;
                len2--;
            } else {
                sumNode = new ListNode(p1.val + p2.val);
                p1 = p1.next;
                p2 = p2.next;
            }

            ListNode temp = dummyHead.next;
            dummyHead.next = sumNode;
            sumNode.next = temp;
        }

        ListNode p = dummyHead.next;
        int carry = 0;
        while(p != null) {
            p.val += carry;
            carry = p.val / 10;
            p.val = p.val % 10;
            p = p.next;
        }

        p = dummyHead.next;
        ListNode prev = null;
        while(p != null) {
            ListNode temp = p.next;
            p.next = prev;
            prev = p;
            p = temp;
        }

        ListNode head = prev;
        if(carry == 1)
            head = new ListNode(1, prev);

        return head;
    }
}
