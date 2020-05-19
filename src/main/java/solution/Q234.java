package solution;

import annotations.Problem;
import data_structure.ListNode;
import enums.QDifficulty;
import enums.QTag;

import java.util.Stack;

@Problem(
        title = "Palindrome Linked List",
        difficulty = QDifficulty.EASY,
        tag = QTag.LINKED_LIST
)
public class Q234 {
    public boolean isPalindrome(ListNode head) {
        Stack<ListNode> stack = new Stack<ListNode>();
        ListNode cNode = head;
        while(cNode != null) {
            stack.push(cNode);
            cNode = cNode.next;
        }

        cNode = head;
        while(!stack.empty()) {
            if(stack.pop().val != cNode.val)
                return false;
            else
                cNode = cNode.next;
        }
        return true;
    }
}
