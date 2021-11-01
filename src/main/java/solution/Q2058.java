package solution;

import annotations.Problem;
import data_structure.ListNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Find the Minimum and Maximum Number of Nodes Between Critical Points",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.LINKED_LIST,
        url = "https://leetcode.com/problems/find-the-minimum-and-maximum-number-of-nodes-between-critical-points/"
)
public class Q2058 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int[] ans = new int[2];

        int min = Integer.MAX_VALUE;
        int max = 0;
        int first = -1;
        int last = -1;
        int cnt = 0;
        ListNode pre = null;
        ListNode cur = head;
        while(cur.next != null) {
            ListNode next = cur.next;

            if((pre != null && cur.val > pre.val && cur.val > next.val) ||
                    (pre != null && cur.val < pre.val && cur.val < next.val)) {
                if(first == -1) {
                    first = cnt;
                } else {
                    max = cnt - first;
                    min = Math.min(cnt - last, min);
                }
                last = cnt;
            }

            cnt++;
            pre = cur;
            cur = cur.next;
        }

        if(max != 0 && min != Integer.MAX_VALUE) {
            return new int[] {min, max};
        }
        return new int[] {-1, -1};
    }
}
