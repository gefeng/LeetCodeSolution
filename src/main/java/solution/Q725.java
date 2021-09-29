package solution;

import annotations.Problem;
import data_structure.ListNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Split Linked List in Parts",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.LINKED_LIST,
        url = "https://leetcode.com/problems/split-linked-list-in-parts/"
)
public class Q725 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public ListNode[] splitListToParts(ListNode head, int k) {
        int n = 0;
        ListNode[] ans = new ListNode[k];

        ListNode curr = head;
        while(curr != null) {
            curr = curr.next;
            n++;
        }

        curr = head;
        int rem = n % k;
        int idx = 0;
        for(int i = 0; i < rem; i++) {
            int sz = n / k + 1;
            ListNode h = curr;
            ListNode p = null;
            while(sz != 0 && curr != null) {
                p = curr;
                curr = curr.next;
                sz--;
            }

            ans[idx++] = h;
            if(p != null) {
                p.next = null;
            }
        }

        for(int i = 0; i < k - rem; i++) {
            int sz = n / k;
            ListNode h = curr;
            ListNode p = null;
            while(sz != 0 && curr != null) {
                p = curr;
                curr = curr.next;
                sz--;
            }
            ans[idx++] = h;
            if(p != null) {
                p.next = null;
            }
        }

        return ans;
    }
}
