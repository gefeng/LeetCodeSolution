package solution;

import annotations.Problem;
import data_structure.ListNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Linked List Random Node",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.RESERVOIR_SAMPLING,
        url = "https://leetcode.com/problems/linked-list-random-node/"
)
public class Q382 {
    /**
     * Reservoir Sampling, the chance to replace current number with
     * a number in reservoir is 1/i where i is # processed numbers.
     * Let's say i == 4 and we have a, b, c and d.
     * There is a 1/4 chance to put d in reservoir.
     * The chance of a, b, c left in reservoir is 3/4 and for each is 1/4
     *
     * Time:  O(N)
     * Space: O(1)
     * */
    private ListNode head;
    /** @param head The linked list's head.
    Note that the head is guaranteed to be not null, so it contains at least one node. */
    public Q382(ListNode head) {
        this.head = head;
    }

    /** Returns a random node's value. */
    public int getRandom() {
        int k = 0;
        int cnt = 1;
        ListNode cur = head;

        while(cur!= null) {
            if(Math.random() < 1.0 / cnt) {
                k = cur.val;
            }
            cnt++;
            cur = cur.next;
        }

        return k;
    }
}
