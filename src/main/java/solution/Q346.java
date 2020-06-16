package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.LinkedList;
import java.util.Queue;

@Problem(
        title = "Moving Average from Data Stream",
        difficulty = QDifficulty.EASY,
        tag = QTag.QUEUE,
        url = "https://leetcode.com/problems/moving-average-from-data-stream/"
)
public class Q346 {
    /**should use circular queue**/
    int[] queue;
    int size;
    int head;
    int count;
    long wSum;

    /** Initialize your data structure here. */
    public Q346(int size) {
        this.queue = new int[size];
        this.size = size;
        head = 0;
        count = 0;
        wSum = 0;
    }

    public double next(int val) {
        if(count < size)
            count++;
        wSum -= queue[head];
        wSum += val;
        queue[head] = val;
        head = (head + 1) % size;
        return (double)wSum / count;
    }
}
