package solution;

import annotations.Problem;
import data_structure.ListNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Sort List",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.LINKED_LIST,
        url = "https://leetcode.com/problems/sort-list/"
)
public class Q148 {
    public ListNode sortList(ListNode head) {
        return mergeSort(head);
    }

    private ListNode mergeSort(ListNode head) {
        if(head == null || head.next == null)
            return head;
        ListNode mid = getMid(head);
        ListNode lHead = mergeSort(head);
        ListNode rHead = mergeSort(mid);
        return merge(lHead, rHead);
    }

    private ListNode getMid(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        ListNode prev = null;
        while(fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        prev.next = null; // split list to two halves

        return slow;
    }

    private ListNode merge(ListNode h1, ListNode h2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = dummyHead;
        ListNode p1 = h1;
        ListNode p2 = h2;
        while(p1 != null || p2 != null) {
            if(p1 == null) {
                p.next = p2;
                p2 = p2.next;
            } else if(p2 == null) {
                p.next = p1;
                p1 = p1.next;
            } else {
                if(p1.val < p2.val) {
                    p.next = p1;
                    p1 = p1.next;
                } else {
                    p.next = p2;
                    p2 = p2.next;
                }
            }
            p = p.next;
        }

        return dummyHead.next;
    }
}
