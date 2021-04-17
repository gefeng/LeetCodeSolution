package solution;

import annotations.Problem;
import data_structure.ListNode;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Convert Sorted List to Binary Search Tree",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/"
)
public class Q109 {
    /*
    *  每次从list中间开始构建,保证左右subtree高度差为1
    * */
    public TreeNode sortedListToBST(ListNode head) {
        if(head == null)
            return null;
        if(head.next == null)
            return new TreeNode(head.val);

        ListNode prev = getMid(head);
        ListNode mid = prev.next;

        // create new tree node
        TreeNode root = new TreeNode(mid.val);

        // break list to 3 parts
        prev.next = null;

        root.left = sortedListToBST(head);
        root.right = sortedListToBST(mid.next);

        return root;
    }

    private ListNode getMid(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        ListNode prev = null;
        while(fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        return prev;
    }
}
