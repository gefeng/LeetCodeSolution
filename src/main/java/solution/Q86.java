package solution;

import annotations.Problem;
import data_structure.ListNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Partition List",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.LINKED_LIST,
        url = "https://leetcode.com/problems/partition-list/"
)
public class Q86 {
    public ListNode partition(ListNode head, int x) {
        ListNode dummyHead = new ListNode(0);

        ListNode p1 = head;
        ListNode p2 = dummyHead;
        ListNode tail = dummyHead;

        while(p1 != null) {
            if(p1.val >= x) {
                ListNode temp = p1.next;

                p2.next = p1;
                p1.next = null;

                p1 = temp;
                p2 = p2.next;
            } else {
                ListNode temp1 = tail.next;
                ListNode temp2 = p1.next;

                tail.next = p1;
                p1.next = temp1;

                p1 = temp2;
                tail = tail.next;

                if(p2.next != null) {
                    p2 = p2.next;
                }
            }
        }

        return dummyHead.next;
    }
}
