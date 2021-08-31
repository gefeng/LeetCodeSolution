package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.PriorityQueue;
import java.util.Queue;

@Problem(
        title = "Find the Kth Largest Integer in the Array",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HEAP,
        url = "https://leetcode.com/problems/find-the-kth-largest-integer-in-the-array/"
)
public class Q1985 {
    /**
     * Time:  O(N * logK)
     * Space: O(K)
     * */
    public String kthLargestNumber(String[] nums, int k) {
        Queue<String> minHeap = new PriorityQueue<>((a, b) -> {
            if(a.length() == b.length()) {
                return a.compareTo(b);
            }
            return Integer.compare(a.length(), b.length());
        });

        for(String num : nums) {
            minHeap.offer(num);

            if(minHeap.size() > k) {
                minHeap.poll();
            }
        }

        return minHeap.peek();
    }
}
