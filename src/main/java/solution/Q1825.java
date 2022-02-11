package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Finding MK Average",
        difficulty = QDifficulty.HARD,
        tag = QTag.HEAP,
        url = "https://leetcode.com/problems/finding-mk-average/"
)
public class Q1825 {
    private int m;
    private int k;
    private long sum;
    Queue<Integer> q;
    Queue<Integer> lpq; // max heap
    Queue<Integer> rpq; // min heap
    TreeMap<Integer, Integer> mid;
    public Q1825(int m, int k) {
        this.m = m;
        this.k = k;
        this.sum = 0;

        q = new ArrayDeque<>();
        lpq = new PriorityQueue<>(Comparator.reverseOrder());
        rpq = new PriorityQueue<>();
        mid = new TreeMap<>();
    }

    /**
     * Time:  O(logK + logM)
     * */
    public void addElement(int num) {
        if(q.size() != m) {
            q.offer(num);
            mid.put(num, mid.getOrDefault(num, 0) + 1);
            sum += num;
            if(q.size() == m) init();
        } else {
            int odd = q.poll();
            q.offer(num);

            if(mid.containsKey(odd)) {
                removeFromMid(odd);
                sum -= odd;
            } else if(odd <= lpq.peek()) {
                lpq.remove(odd);
            } else {
                rpq.remove(odd);
            }

            if(!lpq.isEmpty() && num < lpq.peek()) {
                lpq.offer(num);
            } else if(!rpq.isEmpty() && num > rpq.peek()) {
                rpq.offer(num);
            } else {
                mid.put(num, mid.getOrDefault(num, 0) + 1);
                sum += num;
            }

            balance();
        }
    }

    /**
     * Time:  O(1)
     * */
    public int calculateMKAverage() {
        return q.size() == m ? (int)(sum / (m - 2 * k)) : -1;
    }

    private void init() {
        while(lpq.size() != k) {
            int smallest = mid.firstKey();
            lpq.offer(smallest);

            removeFromMid(smallest);
            sum -= smallest;
        }

        while(rpq.size() != k) {
            int largest = mid.lastKey();
            rpq.offer(largest);

            removeFromMid(largest);
            sum -= largest;
        }
    }

    private void balance() {
        if(lpq.size() > k) {
            int x = lpq.poll();
            mid.put(x, mid.getOrDefault(x, 0) + 1);
            sum += x;
        }
        if(rpq.size() > k) {
            int x = rpq.poll();
            mid.put(x, mid.getOrDefault(x, 0) + 1);
            sum += x;
        }

        if(lpq.size() < k) {
            int smallest = mid.firstKey();
            lpq.offer(smallest);
            removeFromMid(smallest);
            sum -= smallest;
        }
        if(rpq.size() < k) {
            int largest = mid.lastKey();
            rpq.offer(largest);
            removeFromMid(largest);
            sum -= largest;
        }
    }

    private void removeFromMid(int key) {
        int f = mid.get(key);
        if(f == 1) mid.remove(key);
        else mid.put(key, f - 1);
    }
}
