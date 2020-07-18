package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Insert Delete GetRandom O(1)",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/insert-delete-getrandom-o1/"
)
public class Q380 {
    HashMap<Integer, Integer> map;
    List<Integer> keys;
    Random rand;
    /** Initialize your data structure here. */
    public Q380() {
        map = new HashMap<>();
        keys = new ArrayList<>();
        rand = new Random();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(map.containsKey(val))
            return false;

        keys.add(val);
        map.put(val, keys.size() - 1);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(!map.containsKey(val))
            return false;

        int index = map.get(val);
        int len = keys.size() - 1;
        int lastKey = keys.get(len);
        keys.set(index, lastKey);
        map.put(lastKey, index);

        map.remove(val);
        keys.remove(len);

        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        return keys.get(rand.nextInt(keys.size()));
    }
}
