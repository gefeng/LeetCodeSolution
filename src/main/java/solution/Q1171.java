package solution;

import annotations.Problem;
import data_structure.ListNode;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.Map;

@Problem(
        title = "Remove Zero Sum Consecutive Nodes from Linked List",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.LINKED_LIST,
        url = "https://leetcode.com/problems/remove-zero-sum-consecutive-nodes-from-linked-list/"
)
public class Q1171 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public ListNode removeZeroSumSublists(ListNode head) {
        Map<Integer, ListNode> map = new HashMap<>();
        ListNode dh = new ListNode(0, head);

        map.put(0, dh);
        int sum = 0;
        for(ListNode cur = head; cur != null; cur = cur.next) {
            sum += cur.val;
            map.put(sum, cur);
        }

        sum = 0;
        for(ListNode cur = dh; cur != null; cur = cur.next) {
            sum += cur.val;
            if(map.containsKey(sum)) {
                cur.next = map.get(sum).next;
            }
        }

        return dh.next;
    }
}
