package solution;

import annotations.Problem;
import data_structure.ListNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Remove Zero Sum Consecutive Nodes from Linked List",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.LINKED_LIST,
        url = "https://leetcode.com/problems/remove-zero-sum-consecutive-nodes-from-linked-list/"
)
public class Q1171 {
    /**
     * Time:  O(N ^ 2)
     * Space: O(1)
     * */
    public ListNode removeZeroSumSublists(ListNode head) {
        ListNode dh = new ListNode(0, head);

        ListNode p1 = dh;

        while(p1 != null) {
            ListNode p2 = p1.next;
            int sum = 0;
            boolean delete = false;
            while(p2 != null) {
                sum += p2.val;

                if(sum == 0) {
                    p1.next = p2.next;
                    delete = true;
                    break;
                }

                p2 = p2.next;
            }

            if(!delete) {
                p1 = p1.next;
            }

        }

        return dh.next;
    }
}
