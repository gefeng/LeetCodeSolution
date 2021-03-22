package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;

@Problem(
        title = "LFU Cache",
        difficulty = QDifficulty.HARD,
        tag = QTag.DESIGN,
        url = "https://leetcode.com/problems/lfu-cache/"
)
public class Q460 {
    /*
        HashMap<Integer, DLinkedList> countMap;
        HashMap<Integer, Node> cacheMap;
        int min = 0;
    */
    private class Node {
        int key; int val; int count; Node prev; Node next;
        Node(int key, int val) {
            this.key = key;
            this.val = val;
            count = 1;
            prev = null;
            next = null;
        }
    }

    private class DLinkedList {
        Node dummyHead;
        Node dummyTail;
        DLinkedList() {
            dummyHead = new Node(0, 0);
            dummyTail = new Node(0, 0);
            dummyHead.next = dummyTail;
            dummyTail.prev = dummyHead;
        }

        public void addLast(Node x) {
            Node prev = dummyTail.prev;
            Node next = dummyTail;
            prev.next = x;
            next.prev = x;
            x.prev = prev;
            x.next = next;
        }

        public void remove(Node x) {
            Node prev = x.prev;
            Node next = x.next;
            prev.next = next;
            next.prev = prev;
        }

        public Node removeFirst() {
            if(isEmpty())
                return null;

            Node x = dummyHead.next;
            remove(x);
            return x;
        }

        public boolean isEmpty() {
            return dummyHead.next == dummyTail;
        }
    }
    private HashMap<Integer, DLinkedList> countMap;
    private HashMap<Integer, Node> cacheMap;
    private int capacity;
    private int minCount;
    public Q460(int capacity) {
        countMap = new HashMap<>();
        cacheMap = new HashMap<>();
        this.capacity = capacity;
        this.minCount = 0;
    }

    public int get(int key) {
        if(!cacheMap.containsKey(key))
            return -1;

        update(key);

        return cacheMap.get(key).val;
    }

    public void put(int key, int value) {
        if(!cacheMap.containsKey(key))
            add(key, value);
        else
            update(key, value);
    }

    private void add(int key, int value) {
        if(capacity == 0)
            return;
        if(cacheMap.size() == capacity)
            removeLFU();

        Node x = new Node(key, value);
        cacheMap.put(key, x);
        minCount = 1;
        countMap.putIfAbsent(minCount, new DLinkedList());
        countMap.get(minCount).addLast(x);
    }

    private void update(int key) {
        Node x = cacheMap.get(key);

        DLinkedList curr = countMap.get(x.count);
        curr.remove(x);
        if(x.count == minCount && curr.isEmpty())
            minCount++;

        x.count++;
        countMap.putIfAbsent(x.count, new DLinkedList());
        DLinkedList next = countMap.get(x.count);
        next.addLast(x);
    }

    private void update(int key, int value) {
        Node x = cacheMap.get(key);
        x.val = value;
        update(key);
    }

    private void removeLFU() {
        DLinkedList dList = countMap.get(minCount);
        Node x = dList.removeFirst();
        cacheMap.remove(x.key);
    }
}
