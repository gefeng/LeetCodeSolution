package solution.weekly281;

import annotations.Problem;
import data_structure.ListNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Merge Nodes in Between Zeros",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.LINKED_LIST,
        url = "https://leetcode.com/contest/weekly-contest-281/problems/merge-nodes-in-between-zeros/"
)
public class Q2181 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public ListNode mergeNodes(ListNode head) {
        ListNode dh = new ListNode(0, head);
        ListNode cur = head;
        ListNode pre = dh;
        while(cur != null && cur.next != null) {
            ListNode st = cur.next;
            ListNode p = st;
            int sum = 0;
            while(p.val != 0) {
                sum += p.val;
                p = p.next;
            }

            ListNode node = new ListNode(sum);
            node.next = p;
            pre.next = node;

            cur = node.next;
            pre = node;
        }
        pre.next = null;
        return dh.next;
    }
}
