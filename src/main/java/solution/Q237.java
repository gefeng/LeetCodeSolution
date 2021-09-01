package solution;

import annotations.Problem;
import data_structure.ListNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Delete Node in a Linked List",
        difficulty = QDifficulty.EASY,
        tag = QTag.LINKED_LIST,
        url = "https://leetcode.com/problems/delete-node-in-a-linked-list/"
)
public class Q237 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public void deleteNode(ListNode node) {
        ListNode cur = node;
        while(cur.next.next != null) {
            cur.val = cur.next.val;
            cur = cur.next;
        }

        cur.val = cur.next.val;
        cur.next = null;
    }
}
