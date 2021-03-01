package solution;

import annotations.Problem;
import data_structure.ListNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Reverse Nodes in k-Group",
        difficulty = QDifficulty.HARD,
        tag = QTag.LINKED_LIST,
        url = "https://leetcode.com/problems/reverse-nodes-in-k-group/"
)
public class Q25 {
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || k == 1)
            return head;

        ListNode curr = head;
        int size = 0;
        while(curr != null) {
            size++;
            curr = curr.next;
        }

        ListNode dummyHead = new ListNode(0, head);
        curr = dummyHead;
        while(size >= k) {
            ListNode subHead = reverse(curr.next, k);
            ListNode subTail = curr.next;
            curr.next = subHead;
            curr = subTail;
            size -= k;
        }

        return dummyHead.next;
    }

    private ListNode reverse(ListNode head, int k) {
        ListNode curr = head;
        ListNode prev = null;
        int i = 0;
        while(i < k) {
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
            i++;
        }

        head.next = curr;
        return prev;
    }
}
