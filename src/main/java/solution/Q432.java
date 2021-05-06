package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Problem(
        title = "All O`one Data Structure",
        difficulty = QDifficulty.HARD,
        tag = QTag.DESIGN,
        url = "https://leetcode.com/problems/all-oone-data-structure/"
)
public class Q432 {
    private class Bucket {
        int id = 0;
        Bucket prev;
        Bucket next;
        Set<String> keys;

        Bucket(int id) {
            this.id = id;
            keys = new HashSet<>();
            prev = null;
            next = null;
        }
    }
    /** Initialize your data structure here. */
    private Bucket dummyHead;
    private Bucket dummyTail;
    private Map<String, Bucket> bucketMap;
    public Q432() {
        dummyHead = new Bucket(-1);
        dummyTail = new Bucket(-1);
        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;

        bucketMap = new HashMap<>();
    }

    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        Bucket currBucket = bucketMap.get(key);
        Bucket nextBucket = currBucket == null ? dummyHead.next : currBucket.next; // next bucket
        int nextId = currBucket == null ? 1 : currBucket.id + 1;  // next bucket id (count)

        if(nextId != nextBucket.id) {   // next bucket doesn't exist
            // create next bucket
            nextBucket = new Bucket(nextId);

            if(currBucket != null) {
                insert(currBucket, nextBucket);
            } else {
                insert(dummyHead, nextBucket);
            }
        }

        nextBucket.keys.add(key);

        if(currBucket != null) {
            currBucket.keys.remove(key);
            if(currBucket.keys.isEmpty()) {
                remove(currBucket);
            }
        }

        bucketMap.put(key, nextBucket);
    }

    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        Bucket currBucket = bucketMap.get(key);
        Bucket prevBucket = currBucket.prev;
        int prevId = currBucket.id - 1;

        if(prevId != 0) {
            if(prevId != prevBucket.id) {
                // create prev bucket
                prevBucket = new Bucket(prevId);
                insert(currBucket.prev, prevBucket);
            }

            prevBucket.keys.add(key);
            bucketMap.put(key, prevBucket);
        } else {
            bucketMap.remove(key);
        }

        currBucket.keys.remove(key);
        if(currBucket.keys.isEmpty()) {
            remove(currBucket);
        }
    }

    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        return bucketMap.size() == 0 ? "" : dummyTail.prev.keys.iterator().next();
    }

    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        return bucketMap.size() == 0 ? "" : dummyHead.next.keys.iterator().next();
    }

    private void insert(Bucket prev, Bucket curr) {
        Bucket next = prev.next;

        curr.prev = prev;
        curr.next = next;

        prev.next = curr;
        next.prev = curr;
    }

    private void remove(Bucket curr) {
        Bucket prev = curr.prev;
        Bucket next = curr.next;

        prev.next = next;
        next.prev = prev;
    }
}
