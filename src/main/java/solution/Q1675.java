package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.PriorityQueue;
import java.util.Queue;

@Problem(
        title = "Minimize Deviation in Array",
        difficulty = QDifficulty.HARD,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/minimize-deviation-in-array/"
)
public class Q1675 {
    /*
    * Intuition:
    *   1. we can double odd number once
    *   2. we can decrease even number multiple times until it becomes odd
    * Thus we can double all the odd numbers once (we can decrease it to original later on if it becomes max)
    * and push all the numbers to max heap. Meanwhile track the minimum number so far.
    * */
    public int minimumDeviation(int[] nums) {
        int minDevi = Integer.MAX_VALUE;
        int min = Integer.MAX_VALUE;
        Queue<Integer> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b, a));

        for(int num : nums) {
            if((num & 1) == 1) {
                maxHeap.offer(num * 2);
                min = Math.min(min, num * 2);
            } else {
                maxHeap.offer(num);
                min = Math.min(min, num);
            }
        }

        while((maxHeap.peek() & 1) == 0) {
            minDevi = Math.min(minDevi, maxHeap.peek() - min);

            min = Math.min(min, maxHeap.peek() / 2);

            maxHeap.offer(maxHeap.poll() / 2);
        }

        return Math.min(minDevi, maxHeap.peek() - min);
    }
}
