package solution;

import annotations.Problem;
import data_structure.ListNode;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

@Problem(
        title = "Next Greater Node In Linked List",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.LINKED_LIST,
        url = "https://leetcode.com/problems/next-greater-node-in-linked-list/"
)
public class Q1019 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int[] nextLargerNodes(ListNode head) {
        List<Integer> l = new ArrayList<>();

        ListNode cur = head;
        while(cur != null) {
            l.add(cur.val);
            cur = cur.next;
        }

        int n = l.size();
        int[] ans = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        for(int i = n - 1; i >= 0; i--) {
            while(!stack.isEmpty() && stack.peek() <= l.get(i)) {
                stack.pop();
            }

            ans[i] = stack.isEmpty() ? 0 : stack.peek();

            stack.push(l.get(i));
        }

        return ans;
    }
}
