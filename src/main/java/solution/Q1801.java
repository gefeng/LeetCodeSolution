package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

@Problem(
        title = "Number of Orders in the Backlog",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HEAP,
        url = "https://leetcode.com/problems/number-of-orders-in-the-backlog/"
)
public class Q1801 {
    private static final int MOD = (int)1e9 + 7;
    public int getNumberOfBacklogOrders(int[][] orders) {
        Map<Integer, Long> buyCntMap = new HashMap<>();
        Map<Integer, Long> sellCntMap = new HashMap<>();
        Queue<Integer> buyHeap = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        Queue<Integer> sellHeap = new PriorityQueue<>();

        for(int[] order : orders) {
            int price = order[0];
            long amount = order[1];
            int type = order[2];
            if(type == 0) { // buy order
                while(!sellHeap.isEmpty() && sellHeap.peek() <= price && amount > 0) {
                    long left = sellCntMap.get(sellHeap.peek());
                    if(left <= amount) {
                        sellCntMap.remove(sellHeap.poll());
                    } else {
                        sellCntMap.put(sellHeap.peek(), left - amount);
                    }

                    amount = Math.max(0, amount - left);
                }
                if(amount > 0) {
                    if(buyCntMap.containsKey(price)) {
                        buyCntMap.put(price, buyCntMap.get(price) + amount);
                    } else {
                        buyCntMap.put(price, amount);
                        buyHeap.offer(price);
                    }
                }
            } else {  // sell order
                while(!buyHeap.isEmpty() && buyHeap.peek() >= price && amount > 0) {
                    long left = buyCntMap.get(buyHeap.peek());
                    if(left <= amount) {
                        buyCntMap.remove(buyHeap.poll());
                    } else {
                        buyCntMap.put(buyHeap.peek(), left - amount);
                    }

                    amount = Math.max(0, amount - left);
                }
                if(amount > 0) {
                    if(sellCntMap.containsKey(price)) {
                        sellCntMap.put(price, sellCntMap.get(price) + amount);
                    } else {
                        sellCntMap.put(price, amount);
                        sellHeap.offer(price);
                    }
                }
            }
        }

        long cnt = 0;
        for(int key : buyCntMap.keySet()) {
            cnt += buyCntMap.get(key);
        }
        for(int key : sellCntMap.keySet()) {
            cnt += sellCntMap.get(key);
        }
        return (int)(cnt % MOD);
    }
}
