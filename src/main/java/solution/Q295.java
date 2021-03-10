package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.PriorityQueue;

@Problem(
        title = "Find median From Data Stream",
        difficulty = QDifficulty.HARD,
        tag = QTag.DESIGN,
        url = "https://leetcode.com/problems/find-median-from-data-stream/"
)
public class Q295 {
    PriorityQueue<Integer> leftQueue;
    PriorityQueue<Integer> rightQueue;
    public Q295() {
        leftQueue = new PriorityQueue<>((a, b) -> (b - a));
        rightQueue = new PriorityQueue<>();
    }

    // O(logn)
    public void addNum(int num) {
        if(leftQueue.isEmpty())
            leftQueue.offer(num);
        else {
            if(num <= leftQueue.peek())
                leftQueue.offer(num);
            else
                rightQueue.offer(num);
        }
        balanceSize();
    }

    private void balanceSize() {
        int sizeL = leftQueue.size();
        int sizeR = rightQueue.size();
        if(Math.abs(sizeL - sizeR) > 1) {
            if(sizeL > sizeR)
                rightQueue.offer(leftQueue.poll());
            else
                leftQueue.offer(rightQueue.poll());
        }
    }

    // O(1)
    public double findMedian() {
        int sizeL = leftQueue.size();
        int sizeR = rightQueue.size();
        if(sizeL == sizeR)
            return (double)(leftQueue.peek() + rightQueue.peek()) / 2;
        else if(sizeL > sizeR)
            return leftQueue.peek();
        else
            return rightQueue.peek();
    }
}
