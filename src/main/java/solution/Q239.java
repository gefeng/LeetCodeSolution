package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.LinkedList;

@Problem(
        title = "Sliding Window Maximum",
        difficulty = QDifficulty.HARD,
        tag = QTag.SLIDING_WINDOW,
        url = "https://leetcode.com/problems/sliding-window-maximum/"
)
public class Q239 {
    /*
        [1,3,-1,-3,5,3,6,7] k = 3
        [1,3,-1]

        1. maintain a deque
        2. each element in deque should be in range [i - k + 1, i]
        3. the first element in deque should be the largest

        O(n) + O(k)
    */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        LinkedList<Integer> sWin = new LinkedList<>();

        for(int i = 0; i < n; i++) {
            while(!sWin.isEmpty() && nums[sWin.peekLast()] < nums[i])
                sWin.removeLast();

            sWin.offer(i);

            if(sWin.peekFirst() < i - k + 1)
                sWin.removeFirst();

            if(i >= k - 1)
                ans[i - k + 1] = nums[sWin.peekFirst()];
        }

        return ans;
    }
}
