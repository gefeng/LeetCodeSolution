package solution;

import annotations.Problem;
import data_structure.ListNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Swap Nodes in Pairs",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/swap-nodes-in-pairs/"
)
public class Q24 {
    /*这题递归的解法很巧，iterative有点蛋疼，要思考一下*/
    public ListNode swapPairs(ListNode head) {
        ListNode dummyHead = new ListNode(0, head);
        ListNode curr = dummyHead;
        while(curr.next != null && curr.next.next != null) {
            ListNode temp = curr.next;
            curr.next = curr.next.next;
            temp.next = temp.next.next;
            curr.next.next = temp;
            curr = temp;
        }
        return dummyHead.next;
    }

    public ListNode swapPairsRecursive(ListNode head) {
        if(head == null || head.next == null)
            return head;

        ListNode firstNode = head;
        ListNode secondNode = head.next;

        firstNode.next = swapPairsRecursive(secondNode.next);
        secondNode.next = firstNode;

        return secondNode;
    }
}
