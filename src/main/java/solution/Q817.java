package solution;

import annotations.Problem;
import data_structure.ListNode;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashSet;
import java.util.Set;

@Problem(
        title = "Linked List Components",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.LINKED_LIST,
        url = "https://leetcode.com/problems/linked-list-components/"
)
public class Q817 {
    /**
     * Time:  O(N + M)
     * Space: O(M)
     * */
    public int numComponents(ListNode head, int[] nums) {
        int ans = 0;
        Set<Integer> set = new HashSet<>();

        for(int x : nums) set.add(x);

        ListNode cur = head;
        while(cur != null) {
            if(set.contains(cur.val)) {
                while(cur != null && set.contains(cur.val)) {
                    set.remove(cur.val);
                    cur = cur.next;
                }
                ans++;
            }

            if(cur != null) {
                cur = cur.next;
            }
        }

        return ans;
    }
}
