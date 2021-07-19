package solution;

import annotations.Problem;
import data_structure.ListNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Merge In Between Linked List",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.LINKED_LIST,
        url = "https://leetcode.com/problems/merge-in-between-linked-lists/"
)
public class Q1669 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode p1 = null;
        ListNode p2 = null;
        ListNode curr = list1;

        int k = 0;
        while(k != a - 1) {
            curr = curr.next;
            k++;
        }
        p1 = curr;

        while(k != b + 1) {
            curr = curr.next;
            k++;
        }
        p2 = curr;

        p1.next = list2;

        curr = list2;
        while(curr.next != null) {
            curr = curr.next;
        }
        curr.next = p2;

        return list1;
    }
}
