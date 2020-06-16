package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;

@Problem(
        title = "Design Circular Queue",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.QUEUE,
        url = "https://leetcode.com/problems/design-circular-queue/"
)
public class Q622 {
    private int[] cQueue;
    private int size;
    private int head;
    private int tail;

    /** Initialize your data structure here. Set the size of the queue to be k. */
    public Q622(int k) {
        if(k <= 0)
            throw new IllegalArgumentException("Illegal size!");

        cQueue = new int[k];
        size = k;
        head = -1;
        tail = -1;
    }

    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if(isFull())
            return false;
        if(isEmpty()) {
            head = 0;
            tail = 0;
        } else
            tail = (tail + 1) % size;
        cQueue[tail] = value;
        return true;
    }

    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if(isEmpty())
            return false;

        if(head == tail) {
            head = -1;
            tail = -1;
        } else
            head = (head + 1) % size;
        return true;
    }

    /** Get the front item from the queue. */
    public int Front() {
        return isEmpty() ? -1 : cQueue[head];
    }

    /** Get the last item from the queue. */
    public int Rear() {
        return isEmpty() ? -1 : cQueue[tail];
    }

    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return (head == -1 && tail == -1);
    }

    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return head == tail + 1 || (head == 0 && tail == size - 1);
    }
}
