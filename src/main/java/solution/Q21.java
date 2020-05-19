package solution;

import annotations.Problem;
import data_structure.ListNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Merge Two Sorted Lists",
        difficulty = QDifficulty.EASY,
        tag = QTag.LINKED_LIST,
        url = "https://leetcode.com/problems/merge-two-sorted-lists/"
)
public class Q21 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode curr = null;
        ListNode currL1 = l1;
        ListNode currL2 = l2;

        if(l1 == null && l2 == null) {
            return head;
        }

        // create head
        if(l1 == null) {
            head = new ListNode(l2.val);
            currL2 = l2.next;
        } else if(l2 == null) {
            head = new ListNode(l1.val);
            currL1 = l1.next;
        } else {
            if (l1.val < l2.val) {
                head = new ListNode(l1.val);
                currL1 = l1.next;
            } else {
                head = new ListNode(l2.val);
                currL2 = l2.next;
            }
        }
        curr = head;

        while(currL1 != null && currL2 != null) {
            if(currL1.val < currL2.val) {
                curr.next = new ListNode(currL1.val);
                currL1 = currL1.next;
            } else {
                curr.next = new ListNode(currL2.val);
                currL2 = currL2.next;
            }
            curr = curr.next;
        }

        while(currL1 != null) {
            curr.next = new ListNode(currL1.val);
            currL1 = currL1.next;
            curr = curr.next;
        }
        while(currL2 != null) {
            curr.next = new ListNode(currL2.val);
            currL2 = currL2.next;
            curr = curr.next;
        }

        return head;
    }

    public ListNode mergeTwoListsRecursive(ListNode l1, ListNode l2) {
        if(l1 == null)
            return l2;
        if(l2 == null)
            return l1;
        if(l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }
}
