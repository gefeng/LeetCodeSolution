package solution;

import annotations.Problem;
import data_structure.ListNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Reverse Nodes in Even Length Groups",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.LINKED_LIST,
        url = "https://leetcode.com/problems/reverse-nodes-in-even-length-groups/"
)
public class Q2074 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public ListNode reverseEvenLengthGroups(ListNode head) {
        ListNode cur = head;
        ListNode pre = null;

        for(int sz = 1; cur != null; sz += 1) {
            int len = 0;

            ListNode sCur = cur;
            ListNode sPre = pre;
            while(sCur != null && len != sz) {
                sPre = sCur;
                sCur = sCur.next;
                len += 1;
            }

            if(len % 2 == 0) {
                pre.next = reverse(cur, sPre);
                cur.next = sCur;

                pre = cur;
                cur = cur.next;
            } else {
                pre = sPre;
                cur = sCur;
            }
        }

        return head;
    }

    private ListNode reverse(ListNode st, ListNode ed) {
        ListNode pre = null;
        ListNode cur = st;

        while(pre != ed) {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }

        return pre;
    }
}
