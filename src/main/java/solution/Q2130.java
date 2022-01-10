package solution;

import annotations.Problem;
import data_structure.ListNode;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Maximum Twin Sum of a Linked List",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.LINKED_LIST,
        url = "https://leetcode.com/problems/maximum-twin-sum-of-a-linked-list/"
)
public class Q2130 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int pairSum(ListNode head) {
        int ans = 0;
        ListNode fast = head;
        ListNode slow = head;
        ListNode pre = null;
        while(fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        pre.next = reverse(slow);

        ListNode p1 = head;
        ListNode p2 = pre.next;
        while(p2 != null) {
            ans = Math.max(ans, p1.val + p2.val);
            p1 = p1.next;
            p2 = p2.next;
        }

        return ans;
    }

    private ListNode reverse(ListNode head) {
        ListNode cur = head;
        ListNode pre = null;
        while(cur != null) {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }

        return pre;
    }
}
