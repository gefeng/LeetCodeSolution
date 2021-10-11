package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.PriorityQueue;

@Problem(
        title = "Construct Target Array With Multiple Sums",
        difficulty = QDifficulty.HARD,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/construct-target-array-with-multiple-sums/"
)
public class Q1354 {
    /**
     * Time:  O(N * logN)
     * Space: O(N * logN)
     * */
    public boolean isPossible(int[] target) {
        if(target.length == 1) {
            return target[0] == 1;
        }
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        int sum = 0;

        for(int x : target) {
            sum += x;
            maxHeap.offer(x);
        }

        while(maxHeap.peek() != 1) {
            int max = maxHeap.poll();
            int repeat = (max - 1) / (sum - max); // improve cases like [1, 1000000]
            if(repeat == 0) {
                return false;
            }
            int prev = max - (sum - max) * repeat;
            maxHeap.offer(prev);
            sum -= (max - prev);
        }

        return true;
    }
}
