package solution;

import annotations.Problem;
import data_structure.ListNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Remove Duplicates from Sorted List",
        difficulty = QDifficulty.EASY,
        tag = QTag.LINKED_LIST
)
public class Q83 {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode curr = head;
        while(curr != null) {
            if(curr.next == null)
                break;
            if(curr.val == curr.next.val) {
                curr.next = curr.next.next;
            } else {
                curr = curr.next;
            }
        }
        return head;
    }
}
