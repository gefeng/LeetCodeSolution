package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Circular Array Loop",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TWO_POINTERS,
        url = "https://leetcode.com/problems/circular-array-loop/"
)
public class Q457 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public boolean circularArrayLoop(int[] nums) {
        int n = nums.length;

        for(int i = 0; i < n; i++) {
            if(nums[i] == n || nums[i] == 0) {
                continue;
            }
            int fast = i;
            int slow = i;

            do {
                fast = getNext(n, fast, nums[fast]);
                fast = getNext(n, fast, nums[fast]);
                slow = getNext(n, slow, nums[slow]);
            } while(fast != slow);

            fast = i;
            while(slow != fast) {
                slow = getNext(n, slow, nums[slow]);
                fast = getNext(n, fast, nums[fast]);
            }

            int init = slow;
            int len = 0;
            int dir = nums[init] > 0 ? 1 : -1;
            boolean oneDir = true;
            do {
                int pre = slow;
                slow = getNext(n, slow, nums[slow]);

                if(nums[slow] * dir < 0) {
                    oneDir = false;
                }
                nums[pre] = 0;
                len++;
            } while(slow != init);

            if(len > 1 && oneDir) {
                return true;
            }
        }
        return false;
    }

    private int getNext(int n, int i, int next) {
        return next > 0 ? (i + next) % n : (i + (next % n) + n) % n;
    }
}
