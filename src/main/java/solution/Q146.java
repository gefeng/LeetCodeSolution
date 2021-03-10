package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.LinkedList;

@Problem(
        title = "LRU Cache",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DESIGN,
        url = "https://leetcode.com/problems/lru-cache/"
)
public class Q146 {
    private class Node {
        int key;
        int value;
        Node prev;
        Node next;
        Node() {
            key = 0;
            value = 0;
            prev = null;
            next = null;
        }
        Node(int key, int value) {
            this.key = key;
            this.value = value;
            prev = null;
            next = null;
        }
    }

    Node dummyHead;
    Node dummyTail;
    int capacity;
    HashMap<Integer, Node> cache;
    public Q146(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();

        dummyHead = new Node();
        dummyTail = new Node();
        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;
    }

    public int get(int key) {
        if(!cache.containsKey(key))
            return -1;

        moveToHead(cache.get(key));

        return cache.get(key).value;
    }

    public void put(int key, int value) {
        if(!cache.containsKey(key)) {
            Node node = new Node(key, value);
            cache.put(key, node);
            link(dummyHead, dummyHead.next, node);

            if(cache.size() > capacity) {
                Node tail = removeTail();
                cache.remove(tail.key);
            }
        } else {
            cache.get(key).value = value;
            moveToHead(cache.get(key));
        }
    }

    private void moveToHead(Node node) {
        unlink(node);
        link(dummyHead, dummyHead.next, node);
    }

    private Node removeTail() {
        Node tail = dummyTail.prev;
        unlink(tail);
        return tail;
    }

    private void link(Node prevNode, Node nextNode, Node node) {
        node.next = nextNode;
        node.prev = prevNode;
        prevNode.next = node;
        nextNode.prev = node;
    }

    private void unlink(Node node) {
        Node prevNode = node.prev;
        Node nextNode = node.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
    }
}
