package solution;

import annotations.Problem;
import data_structure.ListNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Swapping Nodes in a Linked List",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.LINKED_LIST,
        url = "https://leetcode.com/problems/swapping-nodes-in-a-linked-list/"
)
public class Q1721 {
    public ListNode swapNodes(ListNode head, int k) {
        int n = getSize(head);

        ListNode p1 = getKth(head, k);
        ListNode p2 = getKth(head, n - k + 1);

        swap(p1, p2);

        return head;
    }

    private int getSize(ListNode head) {
        ListNode curr = head;
        int n = 0;
        while(curr != null) {
            curr = curr.next;
            n++;
        }
        return n;
    }

    private ListNode getKth(ListNode head, int k) {
        ListNode curr = head;
        while(k != 1) {
            curr = curr.next;
            k--;
        }
        return curr;
    }

    private void swap(ListNode p1, ListNode p2) {
        int temp = p1.val;
        p1.val = p2.val;
        p2.val = temp;
    }
}
