package solution;

import annotations.Problem;
import data_structure.Bucket;
import data_structure.ListBucket;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

@Problem(
        title = "Design HashSet",
        difficulty = QDifficulty.EASY,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/design-hashset/"
)
public class Q705 {
    /**
     * All values will be in the range of [0, 1000000]
     * The number of operations will be in the range of [1, 10000]
     **/
    private static final int KEY_RANGE = 769;
    private static final float LOAD_FACTOR = 0.75f;
    HashSet<Integer> set = new HashSet<>();
    private Bucket[] buckets;
    public Q705() {
        buckets = new Bucket[KEY_RANGE];
        for(int i = 0; i < KEY_RANGE; i++) {
            buckets[i] = new ListBucket();
        }
    }

    public void add(int key) {
        buckets[hash(key)].add(key);
    }

    public void remove(int key) {
        buckets[hash(key)].remove(key);
    }

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        return buckets[hash(key)].contains(key);
    }

    private int hash(int key) {
        return key % KEY_RANGE;
    }
}
